import React, { useContext, useEffect, useState } from "react";
import AppContext from "../../context/AppContext";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {URL_API} from "../../services/URL_API";
import {addProfileStarted, addProfileToDB, setOpenForm} from "../../context/Actions";
import Box from "@mui/material/Box";
import {Alert, Modal} from "@mui/material";

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '1px solid #000',
    boxShadow: 24,
    pt: 2,
    px: 4,
    pb: 3,
};

function ProfileCreationForm() {

    const {state, dispatch} = useContext(AppContext);
    const {addProfiles, applicationCollections, openForm} = state;
    const {error, data} = addProfiles;

    const collectionLinks = applicationCollections.data

    const initialState = {
        description: ''
    }
    const [profile, setProfile] = useState(initialState)

    useEffect(() => {
        return () => {
            dispatch(addProfileStarted());
            dispatch(setOpenForm(false))
        }
    }, [])

    const createNewProfile = () => {
        let url = collectionLinks._links['profiles'].href;
        const req = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(profile)
        };
        addProfileToDB(url, req, dispatch);
    }

    let submission
    if (error !== null) {
        submission = (<div>
            <Alert severity="warning">Something went wrong...</Alert>
        </div>);
    } else {
        if (!Array.isArray(data)) {
            if (openForm === true) {
                dispatch(setOpenForm(false))
            }
        }
    }


    return (
        <Modal
            open={openForm}
            onClose={() => dispatch(setOpenForm(false))}
            aria-labelledby="parent-modal-title"
            aria-describedby="parent-modal-description"
        >
            <Box
                component="form"
                sx={{...style, width: 'flex'}}
                noValidate
                autoComplete="off"
            >

                <h3 id="parent-modal-title">Create New Profile </h3>
                <center>
                    <br></br>
                    <TextField
                        id="profile-description"
                        label="New Profile Description"
                        onChange={(e) => setProfile({...profile, description: e.target.value})}
                    />

                    <p></p>

                    <Button onClick={() => createNewProfile()}> Create</Button>

                    <p></p>
                </center>
                {submission}
            </Box>
        </Modal>
    );
}

export default ProfileCreationForm;
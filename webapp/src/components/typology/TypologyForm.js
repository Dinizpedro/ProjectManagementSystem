import React, { useContext, useEffect, useState } from "react";
import AppContext from "../../context/AppContext";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { URL_API } from "../../services/URL_API";
import Box from "@mui/material/Box";
import {addProfileStarted, addTypologyStarted, addTypologyToDB, setOpenForm} from "../../context/Actions";
import {Modal} from "@mui/material";


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

function TypologyForm() {

    const { state, dispatch } = useContext(AppContext)
    const { addTypology, openForm, applicationCollections } = state;
    const { error, data } = addTypology;
    const collectionLinks = applicationCollections.data


    const initialState = {
        description: '',
    }
    const [typology, setTypology] = useState(initialState)

    useEffect(() => {
        return () => {
            dispatch(addTypologyStarted());
            dispatch(setOpenForm(false))
        }
    }, [])

    const createNewTypology = () => {
        let url = collectionLinks._links['typologies'].href;
        addTypologyToDB(url,dispatch, typology)
    }

    let submission = <div></div>

    if (error !== null) {
        submission = (<div>
            <center>
                <h4>Something went wrong</h4>
            </center>
        </div>);
    } else {
        if(!Array.isArray(data)) {
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
                <h3 id="parent-modal-title">Create New Typology </h3>
                <center>
                <TextField
                    id="typology-description"
                    label="New typology description"
                    onChange={(e) => setTypology({ ...typology, description: e.target.value })}
                />

                <p></p>
                <Button variant="contained" onClick={() => createNewTypology()}> Submit</Button>
                <p></p>
                </center>
                {submission}
            </Box>
        </Modal>
    );
}

export default TypologyForm;
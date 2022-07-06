import * as React from "react";
import {useContext, useState} from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import AppContext from "../../context/AppContext";
import {URL_API} from "../../services/URL_API";
import {addUserStoryToDB} from "../../context/Actions";
import {Snackbar} from "@material-ui/core";
import {Alert} from "@mui/material";

const UserStory = () => {


    const {state, dispatch} = useContext(AppContext);

    const {addUserStory} = state;

    const {error, data} = addUserStory;

    const initialState = {
        description: ''
    }

    const [userStory, setUserStory] = useState(initialState);


    const getProjectCode = () => {
        const url = (window.location.href)
        //return (url.split("/").splice(-2)[0]);
        return (url.split("/").pop());
    }

    const createUserStory = () => {
        let url = `${URL_API}/userStories/projectCode/` + getProjectCode()
        addUserStoryToDB(url, dispatch, userStory)
    }

    {
        return (
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': {m: 2, width: '50ch'},
                }}
                noValidate
                autoComplete="off">
                <TextField
                    required
                    id="description"
                    label="Insert User Story Description"
                    onChange={(e) => setUserStory({...userStory, description: e.target.value})}
                />

                <Button variant="contained" style={{top: 25}} onClick={createUserStory}> Create User
                    Story </Button>

            </Box>
        );
    }

};

export default UserStory
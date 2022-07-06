import React, { useContext, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from "@mui/material/Button";
import AppContext from "../../context/AppContext";
import { Alert } from "@mui/material";
import Snackbar from "@mui/material/Snackbar";
import { URL_API } from "../../services/URL_API";
import { updateUserToDB } from "../../context/Actions";

const UpdateUserTable = () => {

    const { state, dispatch } = useContext(AppContext);
    const { selectedUser, updatedUser } = state;
    const currentUserData = selectedUser.data;



    const fieldsErrorsInitialState = {
        nameTextFieldError: false,
        nameHelperText: "",
        functionTextFieldError: false,
        functionHelperText: ""
    };

    const initialState = {
        email: "",
        userName: "",
        userFunction: "",
    }

    const [userInForm, setUserInForm] = useState(initialState);
    const [userRender, setUserRender] = useState(true)
    const [fieldsErrors, setFieldsErrors] = useState(fieldsErrorsInitialState);


    let updateMessage = <div></div>
    const [open, setOpen] = React.useState(false);
    if (updatedUser.error !== null && !updatedUser.loading) {
        updateMessage = (
            <div>
                <Snackbar
                    open={open}
                    autoHideDuration={1500}
                    onClose={() => setOpen(false)}>
                    <Alert variant="filled" severity="warning">Something went wrong...</Alert>
                </Snackbar>
            </div>);
    } else if (!Array.isArray(updatedUser.data)) {
        updateMessage = (
            <Snackbar
                open={open}
                autoHideDuration={1500}
                onClose={() => setOpen(false)}>
                <Alert variant="filled" severity="success">{updatedUser.data.userName} account was successfully updated!</Alert>
            </Snackbar>
        );
    }

    function submitUpdate() {
        let URL = `${URL_API}/users`;
        updateUserToDB(URL, dispatch, userInForm)
        setOpen(true)
        console.log(updatedUser)
    }

    const handleNameChange = (newName) => {
        setUserInForm({ ...userInForm, userName: newName });
        newName.length <= 30
            && newName.length > 5 ?
            setFieldsErrors({
                ...fieldsErrors,
                nameTextFieldError: false,
                nameHelperText: "",
            }) :
            setFieldsErrors({
                ...fieldsErrors,
                nameTextFieldError: true,
                nameHelperText: "The Username must be between 5 and 30 characters long",
            });

    }

    const handleFunctionChange = (newFunction) => {
        setUserInForm({ ...userInForm, userFunction: newFunction });
        newFunction.length <= 15
            && newFunction.length > 0 ?
            setFieldsErrors({
                ...fieldsErrors,
                functionTextFieldError: false,
                functionHelperText: "",
            }) :
            setFieldsErrors({
                ...fieldsErrors,
                functionTextFieldError: true,
                functionHelperText: "The Function must be between 0 and 15 characters long",
            });
    }

    if (userRender) {
        setUserRender(false)
        setUserInForm({
            email: currentUserData.email,
            userName: currentUserData.userName,
            userFunction: currentUserData.function
        })
    }
    return (
        <div>
            {updateMessage}
            <center>
                <div>
                    <Box
                        component="form"
                        sx={{
                            '& .MuiTextField-root': { m: 2, width: '50ch' },
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <TextField
                            id="username"
                            label="Username"
                            error={fieldsErrors.nameTextFieldError}
                            helperText={fieldsErrors.nameHelperText}
                            value={userInForm.userName}
                            onChange={(e) => handleNameChange(e.target.value)}
                        />
                        <TextField
                            id="function"
                            label="Function"
                            helperText={fieldsErrors.functionHelperText}
                            error={fieldsErrors.functionTextFieldError}
                            value={userInForm.userFunction}
                            onChange={(e) => handleFunctionChange(e.target.value)}
                        />
                    </Box>
                </div>
            </center>
            <p />
            <center>
                <Button
                    disabled={fieldsErrors.functionTextFieldError || fieldsErrors.nameTextFieldError}
                    variant="contained"
                    onClick={() => submitUpdate()}>
                    Update account info
                </Button>
            </center >
        </div>
    );

}


export default UpdateUserTable;
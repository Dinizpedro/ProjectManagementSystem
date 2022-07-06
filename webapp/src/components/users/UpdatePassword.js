import React, {useContext, useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import Button from "@mui/material/Button";
import {URL_API} from "../../services/URL_API";
import {changeUserPassword, changeUserPasswordStarted, fetchUser} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import {Alert} from "@mui/material";
import InputPassword from '../password/InputPassword';
import Snackbar from "@mui/material/Snackbar";

const UpdatePassword = ({userEmail}) => {
    const {state, dispatch} = useContext(AppContext);
    const {changePassword} = state;
    const {selectedUser} = state

    const initialState = {
        email: userEmail,
        actualPassword: '',
        newPassword: ''
    }
    const [userToChangePassword, setUserToChangePassword] = useState(initialState);


    function changePasswordFunction() {
        setOpen(true)
        dispatch(changeUserPasswordStarted())
        let url = selectedUser.data._links['Change Password'].href;
        const changePasswordJSON = {
            email: userToChangePassword.email,
            oldPassword: userToChangePassword.actualPassword,
            newPassword: userToChangePassword.newPassword
        }
        const request = {
            method: "PATCH",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(changePasswordJSON)
        }
        changeUserPassword(url, request, dispatch);
    }

    useEffect(() => {
        dispatch(changeUserPasswordStarted())
    }, [])


    let submission = <div></div>
    const [open, setOpen] = React.useState(false);
    if (changePassword.error !== null) {
        submission = (
            <div>
                <Snackbar
                    open={open}
                    autoHideDuration={1500}
                    onClose={() => setOpen(false)}>
                    <Alert variant="filled" severity="warning">Something went wrong...</Alert>
                </Snackbar>
            </div>);
    } else if (!Array.isArray(changePassword.data)) {
        if (changePassword.data.email.length > 0) {
            submission = (
                <Snackbar
                    open={open}
                    autoHideDuration={1500}
                    onClose={() => setOpen(false)}>
                    <Alert variant="filled" severity="success">Password changed successfully</Alert>
                </Snackbar>
            );
        }
    }


    const changeActualAndOldPassword = (eventValue, valueField) => {

        if (valueField === "actualPassword") {

            setUserToChangePassword({
                ...userToChangePassword,
                actualPassword: eventValue
            })
        } else {
            setUserToChangePassword({
                ...userToChangePassword,
                newPassword: eventValue
            })
        }
    };

    return (
        <div>
            <center>
                <Box component="form"
                     sx={{
                         '& .MuiTextField-root': {m: 2, width: '50ch'},
                     }}
                     noValidate
                     autoComplete="off">


                    <Box style={{
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        marginBottom: '20px'
                    }}>
                        <InputPassword parentToChildFunc={changeActualAndOldPassword}
                                       valueField={"actualPassword"}
                                       labelPassword={"Actual Password"}
                        />
                        <InputPassword parentToChildFunc={changeActualAndOldPassword}
                                       valueField={"newPassword"}
                                       labelPassword={"New Password"}
                        />
                    </Box>
                </Box>
            </center>
            <Button variant="contained" onClick={() => changePasswordFunction()}>
                Change Password
            </Button>

            <p></p>
            {submission}
        </div>
    );

}

export default UpdatePassword;
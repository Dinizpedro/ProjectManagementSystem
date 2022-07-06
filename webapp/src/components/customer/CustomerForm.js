import React, {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { URL_API } from "../../services/URL_API";
import {addCustomerStarted, addCustomerToDB, addProfileStarted, setOpenForm} from "../../context/Actions";
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

function CustomerForm() {

    const { state, dispatch } = useContext(AppContext);
    const { addCustomer, openForm } = state;
    const { error, data } = addCustomer;

    const initialState = {
        customerName: '',
    }

    const [customer, setCustomer] = useState(initialState)

    useEffect(() => {
        return () => {
            dispatch(addCustomerStarted());
            dispatch(setOpenForm(false))
        }
    }, [])

    const createNewCustomer = () => {
        let url = `${URL_API}/api/customers`;
        addCustomerToDB(url, dispatch, customer)
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

                <h3 id="parent-modal-title">Create New Customer </h3>
                <center>
                    <br></br>
                    <TextField
                        id="customer-name"
                        label="New customer name"
                        onChange={(e) => setCustomer({ ...customer, customerName: e.target.value })}
                    />

                    <p></p>

                    <Button variant="contained" onClick={() => createNewCustomer()}> Submit</Button>

                    <p></p>
                </center>
                {submission}
            </Box>
        </Modal>
    );
}

export default CustomerForm;
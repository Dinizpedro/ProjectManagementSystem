import React, {useContext, useEffect, useState} from "react";
import CustomersTable from "../components/customer/CustomersTable"
import CustomerForm from "../components/customer/CustomerForm"
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";
import AppContext from "../context/AppContext";
import {setOpenForm} from "../context/Actions";
import ProfileCreationForm from "../components/profiles/ProfileCreationForm";

function Customers() {

    const navigate = useNavigate();

    const {state, dispatch} = useContext(AppContext)
    const {openForm} = state

    useEffect(() => {
        dispatch(setOpenForm(false))
    }, [])

    let showFormState;
    if (openForm === true) {
        showFormState = <CustomerForm/>
    }

    return (
        <div>

            <Button onClick={() => {
                if (openForm === false) {
                    dispatch(setOpenForm(true));
                }
            }} variant="contained">
                Create New Customer
            </Button>

            {showFormState}

            <br/>
            <br/>

            <CustomersTable/>

            <Button sx={{m: 1}} variant="outlined" onClick={() => navigate('/')}> Back </Button>

        </div>
    )
}

export default Customers;
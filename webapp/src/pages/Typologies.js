import React, {useContext, useEffect, useState} from "react";
import Button from "@mui/material/Button";
import TypologyTable from "../components/typology/TypologyTable"
import TypologyForm from "../components/typology/TypologyForm"
import {setOpenForm} from "../context/Actions";
import AppContext from "../context/AppContext";
import {useNavigate} from "react-router-dom";

function Typologies() {
    const navigate = useNavigate();
    const {state,dispatch} = useContext(AppContext)
    const {openForm} = state

    useEffect(() => {
        dispatch(setOpenForm(false))
    },  [])

    let showFormState;
    if (openForm === true) {
        showFormState = <TypologyForm/>
    }

    return (
        <div>

            <Button onClick={() => {
                if (openForm === false) {
                    dispatch(setOpenForm(true));
                }
            }} variant="contained">
                Create New Typology
            </Button>

            <br></br>
            <br></br>


            {showFormState}

            <TypologyTable/>

            <Button sx={{ m: 1 }} variant="outlined" onClick={() => navigate('/')}> Back </Button>

        </div>
    );
}

export default Typologies;
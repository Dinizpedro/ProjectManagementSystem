import React, {useContext, useEffect} from "react";
import Button from "@mui/material/Button";
import ProfileCreationForm from "../components/profiles/ProfileCreationForm";
import ProfileTable from "../components/profiles/ProfileTable";
import {setOpenForm} from "../context/Actions";
import AppContext from "../context/AppContext";
import {useNavigate} from "react-router-dom";

function Profiles() {
    const navigate = useNavigate();
    const {state,dispatch} = useContext(AppContext)
    const {openForm} = state

    useEffect(() => {
        dispatch(setOpenForm(false))
    },  [])

    let showFormState;
    if (openForm === true) {
        showFormState = <ProfileCreationForm/>
    }

    return (
        <div>

            <Button onClick={() => {
                if (openForm === false) {
                    dispatch(setOpenForm(true));
                }
            }} variant="contained">
                Create New Profile
            </Button>

            <br></br>
            <br></br>


            {showFormState}

            <ProfileTable/>

            <Button sx={{ m: 1 }} variant="outlined" onClick={() => navigate('/')}> Back </Button>


        </div>
    );
}

export default Profiles;

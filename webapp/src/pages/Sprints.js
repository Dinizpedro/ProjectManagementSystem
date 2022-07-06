import React, {useContext, useEffect} from "react"
import SprintTable from "../components/sprints/SprintTable";
import {Link, useNavigate} from "react-router-dom";
import Button from "@mui/material/Button";
import AppContext from "../context/AppContext";
import {Typography} from "@mui/material";
import {setOpenForm} from "../context/Actions";
import ProfileCreationForm from "../components/profiles/ProfileCreationForm";
import SprintForm from "../components/sprints/SprintForm";

export const Sprints = () => {
    const navigate = useNavigate();
    const {state, dispatch} = useContext(AppContext);
    const {selectedProject} = state
    const {openForm} = state

    useEffect(() => {
        dispatch(setOpenForm(false))
    },  [])

    let showFormState;
    if (openForm === true) {
        showFormState = <SprintForm/>
    }

    const projectCode = selectedProject.data.projectCode

    return (
        <center>
            <div style={{height: '60%', width: '80%'}}>
                <Typography variant="h5">Project {projectCode} Sprints</Typography>

                <br/>

                <Button onClick={() => {
                    if (openForm === false) {
                        dispatch(setOpenForm(true));
                    }
                }} variant="contained">
                    Create Sprint
                </Button>

                {showFormState}

                <br/>
                <br/>

                <SprintTable/>
                <br/>
                <Button sx={{m: 1}} variant="outlined"
                        onClick={() => navigate('/projects/' + `${projectCode}`)}> Back </Button>
            </div>
        </center>
    )
}

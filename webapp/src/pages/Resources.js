import React, {useContext} from "react"
import ResourceTable from "../components/resources/ResourceTable"
import {Link, useNavigate} from "react-router-dom";
import Button from "@mui/material/Button";
import AppContext from "../context/AppContext";


export default function Resources ()  {

    const navigate = useNavigate();
    const {state, dispatch} = useContext(AppContext);
    const {selectedProject} = state
    const projectCode = selectedProject.data.projectCode


    return (
        <center>
            <div style={{ height: '60%', width: '80%' }}>

                <Link style={{ textDecoration: 'none'}} to="newResource" >
                    <Button variant="contained">Create Resource</Button>
                </Link>

                <br/>
                <br/>

                <ResourceTable />



                <Button sx={{m: 1}} variant="outlined"
                        onClick={() => navigate('/projects/' + `${projectCode}`)}> Back </Button>
            </div>
        </center>

    )
}
import React from "react"
import ProjectsTable from "../components/projects/ProjectsTable"
import {Link, useNavigate} from "react-router-dom";
import Button from "@mui/material/Button";

export const Projects = () => {
    const navigate = useNavigate();

    return (
        <center>
            <div style={{ height: '60%', width: '80%' }}>
                <Link style={{ textDecoration: 'none' }} to="/projects/newProject">
                    <Button variant="contained">Create Project</Button>
                </Link>
                <br/>
                <br/>
                <ProjectsTable />
                <br/>
                <Button sx={{ m: 1 }} variant="outlined" onClick={() => navigate('/')}> Back </Button>
            </div>
        </center>

    )
}
import React, {useContext, useState} from "react";
import ProductBacklog from "../components/userStories/ProductBacklog";

import UserStory from "../components/userStories/UserStory";
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";
import AppContext from "../context/AppContext";
import {Typography} from "@mui/material";

function UserStories() {
    const navigate = useNavigate();
    const {state, dispatch} = useContext(AppContext);
    const {selectedProject} = state

    const projectCode = selectedProject.data.projectCode

    return (
        <div>
            <Typography variant="h5">Project {projectCode} Product Backlog</Typography>

            <UserStory/>

            <ProductBacklog/>

            <Button sx={{m: 1}} variant="outlined"
                    onClick={() => navigate('/projects/' + `${projectCode}`)}> Back </Button>
        </div>
    )


}

export default UserStories;
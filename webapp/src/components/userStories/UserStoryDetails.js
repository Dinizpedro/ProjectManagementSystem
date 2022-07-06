import {useContext, useEffect, useState} from "react";
import MoveUSToSprint from '../userStories/MoveUSToSprint'
import ChangePriority from '../userStories/ChangePriority'

import {
    fetchAllSprintLinks,
    fetchSprintsDataStarted,
    setSelectedUserStory
} from "../../context/Actions";
import * as React from "react";
import AppContext from "../../context/AppContext";
import TextField from "@mui/material/TextField";
import {useNavigate} from "react-router-dom";
import Box from "@mui/material/Box";

import {Typography} from "@mui/material";
import Button from "@mui/material/Button";
import Divider from "@mui/material/Divider";

function UserStoryDetails() {
    const navigate = useNavigate()
    const {state, dispatch} = useContext(AppContext);
    const {selectedUserStory, sprintLinks, selectedProject} = state;
    const {data} = selectedUserStory;
    const projectData = selectedProject.data

    useEffect(() => {
        dispatch(fetchSprintsDataStarted())

        let url = (projectData._links['sprints'].href)
        const request = {method: 'GET'};
        fetchAllSprintLinks(url, request, dispatch)

        return () => {
            dispatch(setSelectedUserStory([]))
        }
    }, [])

    let effortField = <div></div>
    if (data.effort != null) {
        effortField = (<TextField
            id="user-story-effort"
            label={"Effort"}
            value={data.effort}
            disabled={true}
            InputLabelProps={{
                shrink: true,
            }}
        />)
    }

    let parentUSCodeField;
    if (data.parentUserStoryCode != null) {
        parentUSCodeField = (<TextField
            id="user-story-parent-code"
            label={"Parent User Story"}
            value={data.parentUserStoryCode}
            disabled={true}
            InputLabelProps={{
                shrink: true,
            }}
        />)
    }


    return (
        <div>
            <center>
                <Typography variant="h5">User Story {data.code}</Typography>
                <br/>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root': {m: 2, width: '50ch'},
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        id="user-story-code"
                        label={"User Story Code"}
                        value={data.code}
                        disabled={true}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                    <TextField
                        id="user-story-description"
                        label={"Description"}
                        value={data.description}
                        disabled={true}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                    {effortField}
                    <TextField
                        id="user-story-priority"
                        label={"Priority"}
                        value={data.priority}
                        disabled={true}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                    {parentUSCodeField}
                    <TextField
                        id="user-story-status"
                        label={"Status"}
                        value={data.status}
                        disabled={true}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Box>
                <br/>

                <div style={{height: '60%', width: '80%'}}>
                    <Divider textAlign="left">Move User Story to Sprint</Divider>
                </div>

                <MoveUSToSprint/>
                <br/>

                <div style={{height: '60%', width: '80%'}}>
                <Divider textAlign="left">Change User Story priority</Divider>
                </div>

                <ChangePriority/>

                <br/>
                <br/>

                <Button sx={{m: 1}} variant="outlined"
                        onClick={() => navigate('/productBacklog/' + `${projectData.projectCode}`)}> Back </Button>
            </center>
        </div>
    );
}

export default UserStoryDetails;
import { useContext, useEffect, useState } from "react";
import Button from "@mui/material/Button";
import FormControl from '@mui/material/FormControl';
import {
    fetchAllSprintLinks,
    fetchSprintsDataStarted,
    moveUserStory,
    setSelectedUserStory
} from "../../context/Actions";
import * as React from "react";
import AppContext from "../../context/AppContext";
import { InputLabel, MenuItem, Select } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { Alert } from "@mui/material";
import Snackbar from "@mui/material/Snackbar";

const MoveUSToSprint = () => {

    let navigate = useNavigate();

    const { state, dispatch } = useContext(AppContext);
    const { selectedUserStory, sprintLinks, selectedProject, moveUSToSprint } = state;
    const { data } = selectedUserStory;
    const projectData = selectedProject.data

    const links = sprintLinks.data;
    const [sprint, setSprint] = useState(0);
    const [open, setOpen] = React.useState(false);

    useEffect(() => {
        dispatch(fetchSprintsDataStarted())

        let url = (projectData._links['sprints'].href)
        const request = { method: 'GET' };
        fetchAllSprintLinks(url, request, dispatch)

        return () => {
            dispatch(setSelectedUserStory([]))
        }
    }, [])


    let moveForm = <div></div>

    function moveToSprintConfirm() {
        let url = data._links['moveToSprint'].href;

        const request = {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ sprintID: `${sprint}` })
        };
        moveUserStory(url, request, dispatch);
        setOpen(true)
    }

    let updateMessage = <div></div>
    if (moveUSToSprint.error !== null) {
        updateMessage = (
            <div>
                <Snackbar
                    open={open}
                    autoHideDuration={3000}
                    onClose={() => setOpen(false)}>
                    <Alert variant="filled" severity="warning">Something went wrong...</Alert>
                </Snackbar>
            </div>);
    } else if (Object.keys(moveUSToSprint.data).length > 0) {
        updateMessage = (
            <Snackbar
                open={open}
                autoHideDuration={3000}
                onClose={() => {
                    setOpen(false)
                    navigate('/productBacklog/' + selectedProject.data.projectCode)
                }}>
                <Alert variant="filled" severity="success">User Story {moveUSToSprint.data.code} moved to
                    sprint {moveUSToSprint.data.sprintID}</Alert>
            </Snackbar>
        );
    }

    let SPRINT_LINKS_NUMBER = Object.keys(links).length;
    if ('moveToSprint' in data._links) {
        if (SPRINT_LINKS_NUMBER > 0) {
            moveForm = (<div>
                <FormControl sx={{ m: 1, minWidth: 120 }}>
                    <InputLabel id="demo-simple-select-standard-label"> Sprint </InputLabel>
                    <Select
                        label="Sprint"
                        onChange={(e) => setSprint(e.target.value)}
                        style={{ width: '25ch' }}>
                        {
                            Object.keys(links._links).map((sprintNumber) => (
                                <MenuItem value={sprintNumber}>{sprintNumber}</MenuItem>
                            ))
                        }
                    </Select>
                </FormControl>
                <br></br>
                <Button variant="contained" onClick={() => moveToSprintConfirm()}> Confirm </Button>
            </div>)
        } else {
            const linkToProductBacklog = () => {
                const productBacklogURL = '/productBacklog/' + projectData.projectCode
                navigate(productBacklogURL, "_self")
            }
            const linkToListSprints = () => {
                const sprintsURL = '/sprints/projectCode/' + projectData.projectCode
                navigate(sprintsURL, "_self")
            }
            moveForm = (
                <div>
                    <h3>No Sprints were found ...</h3>
                    <Button variant="contained" onClick={linkToListSprints}> Project Sprints </Button>
                </div>)
        }
    }
    return (
        <div>
            {updateMessage}
            <center>
                {moveForm}
            </center>
            
        </div>
    )
}
export default MoveUSToSprint;


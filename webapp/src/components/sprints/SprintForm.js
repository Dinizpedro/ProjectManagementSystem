import React, {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {URL_API} from "../../services/URL_API";
import {addProfileStarted, addSprintToDB, createSprintStarted, setOpenForm} from "../../context/Actions";
import Box from "@mui/material/Box";
import {Alert, Modal} from "@mui/material";
import Snackbar from "@mui/material/Snackbar";
import {useNavigate} from "react-router-dom";
import {Typography} from "@mui/material";
import {format, parseISO} from "date-fns";

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

function SprintForm() {

    const {state, dispatch} = useContext(AppContext);

    const {addSprints, openForm} = state;

    const {error, data} = addSprints;

    const {selectedProject} = state;
    const projectData = selectedProject.data;


    const initialState = {
        description: '',
        startDate: '',
        endDate: '',
    }

    useEffect(() => {
        return () => {
            dispatch(createSprintStarted());
            dispatch(setOpenForm(false))
        }
    }, [])

    const [sprint, setSprint] = useState(initialState)

    const createNewSprint = () => {
        let url = projectData._links['sprints'].href;
        addSprintToDB(url, dispatch, sprint);
    }

    function getToday() {
        let d = new Date(),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
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

    return (<div>

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
                    <center>
                        <Typography variant="h5">Create Sprint</Typography>
                        <br/>
                        <br/>
                        <TextField
                            sx={{width: '50ch'}}
                            id="description"
                            label="Description"
                            onChange={(e) => setSprint({...sprint, description: e.target.value})}
                        />
                        <br/>
                        <br/>

                        <TextField
                            sx={{width: '50ch'}}
                            type="date"
                            id="project-start-Date"
                            label="Start Date"
                            value={null}
                            InputProps={{inputProps: {min: getToday()}}}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={
                                (e) => {
                                    setSprint({...sprint, startDate: format(parseISO(e.target.value), "dd/MM/yyyy")});
                                }}
                        />
                        <br/>
                        <br/>
                        <TextField
                            sx={{width: '50ch'}}
                            type="date"
                            id="project-end-Date"
                            label="End Date"
                            value={null}
                            InputProps={{inputProps: {min: getToday()}}}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={
                                (e) => {
                                    setSprint({...sprint, endDate: format(parseISO(e.target.value), "dd/MM/yyyy")});
                                }}
                        />
                        <br/>
                        <br/>
                        <p><Button variant="contained" onClick={() => createNewSprint()}>Create</Button></p>
                    </center>

                    {submission}

                </Box>
            </Modal>

        </div>
    );
}

export default SprintForm;

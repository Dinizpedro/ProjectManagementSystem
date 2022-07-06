import * as React from "react";
import {useNavigate} from "react-router-dom";
import {useContext, useState} from "react";
import AppContext from "../../context/AppContext";
import FormControl from '@mui/material/FormControl';
import {
    updateProjectToDB
} from "../../context/Actions";
import {URL_API} from "../../services/URL_API";
import Snackbar from "@mui/material/Snackbar";
import {Alert, InputLabel, MenuItem, Select} from "@mui/material";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import {Link} from "react-router-dom";
import Button from "@mui/material/Button";
import {Typography} from "@mui/material";
import {getToday} from "./ProjectCreationTable"
import {format, parseISO} from "date-fns";
import Grid from "@mui/material/Grid";


const ProjectUpdateTable = () => {
    let navigate = useNavigate();

    const {state, dispatch} = useContext(AppContext);
    const {selectedProject, addProjects} = state;

    const projectData = selectedProject.data;
    const updateProjectData = addProjects.data;
    const updateProjectError = addProjects.error;

    const initialState = {
        projectCode: '',
        projectName: '',
        projectDescription: '',
        projectBusinessSector: '',
        projectNumberOfPlannedSprints: '',
        projectSprintDuration: '',
        projectBudget: '',
        startDate: '',
        endDate: '',
        typologyDescription: '',
        customerName: '',
        userEmail: '',
        costPerHour: '',
        percentageOfAllocation: '',
        status: ''
    }

    const [projectInput, setProjectInput] = useState(initialState)
    const [projectRender, setProjectRender] = useState(true)


    const linkToProductBacklog = () => {
        const productBacklogURL = '/productBacklog/' + projectData.projectCode
        navigate(productBacklogURL, "_self")
    }

    const linkToResource = () => {
        const resourceURL = '/resources/projectCode/' + projectData.projectCode
        navigate(resourceURL, "_self")
    }

    const linkToListSprints = () => {
        const sprintsURL = '/sprints/projectCode/' + projectData.projectCode
        navigate(sprintsURL, "_self")
        console.log(projectData.projectCode)
    }

    const linkToListActivities = () => {
        const activitiesURL = '/projects/' + projectData.projectCode + '/activities'
        navigate(activitiesURL, "_self")
    }

    let updateMessage = <div></div>
    const [open, setOpen] = React.useState(false);
    if (updateProjectError !== null) {
        updateMessage = (
            <div>
                <Snackbar
                    open={open}
                    autoHideDuration={1500}
                    onClose={() => setOpen(false)}>
                    <Alert variant="filled" severity="warning">Something went wrong...</Alert>
                </Snackbar>
            </div>);
    } else if (!Array.isArray(updateProjectData)) {
        updateMessage = (
            <Snackbar
                open={open}
                autoHideDuration={1500}
                onClose={() => setOpen(false)}>

                <Alert variant="filled" severity="success">Project {updateProjectData.projectCode} updated</Alert>
            </Snackbar>
        );
    }

    // Start date control - Today

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

    function getDate(dateString) {
        let year = dateString.split('/').pop().split('/')[0]
        let month = dateString.split('/')[1]
        let day = dateString.split('/')[0]

        return [year, month, day].join('-');
    }

    function submitUpdate() {
        let URL = `${URL_API}/api/projects/` + projectData.projectCode;
        updateProjectToDB(URL, dispatch, projectInput)
        setOpen(true)
    }

    // --------------------- BUTTONS ------------------------------

    let submitUpdateButton = <div></div>
    if (projectData._links['self'] != null) {
        if (projectData._links['self'].type === "PUT") {
            submitUpdateButton =
                <Button sx={{m: 1}} variant="contained" onClick={() => submitUpdate()}> Update </Button>
        }
    }

    let productBacklogButton = <div></div>
    if (projectData._links['productBacklog'] != null) {
        console.log(projectData)
        productBacklogButton =
            <Button sx={{m: 1}} variant="contained" onClick={linkToProductBacklog}> Product Backlog </Button>
    }

    let associateResourceButton = <div></div>
    if (projectData._links['resources'] != null ||
        projectData._links['addTeamMember'] != null ||
        projectData._links['addProductOwner'] != null ||
        projectData._links['addScrumMaster'] != null) {
        associateResourceButton =
            <Button sx={{m: 1}} variant="contained" onClick={linkToResource}> Associate Resource </Button>
    }

    let sprintsButton = <div></div>
    if (projectData._links['sprints'] != null) {
        sprintsButton = <Button sx={{m: 1}} variant="contained" onClick={linkToListSprints}> Sprints </Button>
    }

    let activitiesButton = <div></div>
    if (projectData._links['activities'] != null) {
        activitiesButton = <Button sx={{m: 1}} variant="contained" onClick={linkToListActivities}> Activities </Button>
    }

    if (!Array.isArray(projectData)) {

        if (projectRender === true) {
            setProjectRender(false)
            setProjectInput(projectData)
        }

        return (
            <center>
                <div style={{width: '80%'}}>
                    {updateMessage}
                    <Box
                        component="form"
                        sx={{
                            '& .MuiTextField-root': {m: 2, width: '50ch'},
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <Typography variant="h5">Project {projectInput.projectCode}</Typography>
                        <br/>

                        <TextField
                            id="project-code"
                            label={"Project code"}
                            value={projectInput.projectCode}
                            disabled={true}
                            onChange={(e) => setProjectInput({...projectInput, projectCode: e.target.value})}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            id="project-name"
                            label="Project Name"
                            value={projectInput.projectName}
                            onChange={(e) => setProjectInput({...projectInput, projectName: e.target.value})}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            id="project-description"
                            label="Project Description"
                            value={projectInput.projectDescription}
                            onChange={(e) => setProjectInput({
                                ...projectInput,
                                projectDescription: e.target.value
                            })}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            id="project-business-sector"
                            label="Business Sector"
                            value={projectInput.projectBusinessSector}
                            onChange={(e) => setProjectInput({
                                ...projectInput,
                                projectBusinessSector: e.target.value
                            })}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            type="date"
                            id="project-start-Date"
                            label="Start Date"
                            value={getDate(projectInput.startDate)}
                            InputProps={{inputProps: {min: getToday()}}}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={
                                (e) => {
                                    setProjectInput({
                                        ...projectInput,
                                        startDate: format(parseISO(e.target.value), "dd/MM/yyyy")
                                    });
                                }}
                        />
                        <TextField
                            type="date"
                            id="project-end-Date"
                            label="End Date"
                            value={getDate(projectInput.endDate)}
                            InputProps={{inputProps: {min: getToday()}}}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(e) => {
                                setProjectInput({
                                    ...projectInput,
                                    endDate: format(parseISO(e.target.value), "dd/MM/yyyy")
                                });
                            }}
                        />
                        <TextField
                            type='number'
                            id="project-budget"
                            label="Budget"
                            value={projectInput.projectBudget}
                            onChange={(e) => setProjectInput({...projectInput, projectBudget: e.target.value})}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            type='number'
                            id="project-number-of-planned-sprints"
                            label="Number of Planned Sprints"
                            value={projectInput.projectNumberOfPlannedSprints}
                            onChange={(e) => setProjectInput({
                                ...projectInput,
                                projectNumberOfPlannedSprints: e.target.value
                            })}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            type='number'
                            id="project-sprint-duration"
                            label="Project Sprint Duration"
                            value={projectInput.projectSprintDuration}
                            onChange={(e) => setProjectInput({
                                ...projectInput,
                                projectSprintDuration: e.target.value
                            })}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            id="customer"
                            label="Customer"
                            value={projectInput.customerName}
                            onChange={(e) => setProjectInput({...projectInput, customerName: e.target.value})}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            id="typology"
                            label="Typology"
                            value={projectInput.typologyDescription}
                            onChange={(e) => setProjectInput({
                                ...projectInput,
                                typologyDescription: e.target.value
                            })}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <p/>
                        <FormControl sx={{m: 1, minWidth: 120}}>

                            <InputLabel id="demo-simple-select-standard-label">Status</InputLabel>
                            <Select
                                value={projectInput.status}
                                label="Status"
                                onChange={(e) => setProjectInput({...projectInput, status: e.target.value})}
                            >
                                <MenuItem value={"Planned"}>Planned</MenuItem>
                                <MenuItem value={"Inception"}>Inception</MenuItem>
                                <MenuItem value={"Elaboration"}>Elaboration</MenuItem>
                                <MenuItem value={"Construction"}>Construction</MenuItem>
                                <MenuItem value={"Transition"}>Transition</MenuItem>
                                <MenuItem value={"Warranty"}>Warranty</MenuItem>
                                <MenuItem value={"Closed"}>Closed</MenuItem>
                            </Select>
                        </FormControl>


                        <p></p>
                        <div>
                            {submitUpdateButton}

                            {productBacklogButton}

                            {associateResourceButton}

                            {sprintsButton}

                            {activitiesButton}
                        </div>
                        <br/>
                        <Link to="/projects">
                            <Button variant="outlined">Cancel</Button>
                        </Link>
                    </Box>
                </div>
            </center>
        );
    }
}

export default ProjectUpdateTable

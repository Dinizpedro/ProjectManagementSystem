import React, {useContext, useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import "react-datepicker/dist/react-datepicker.css";
import Button from "@mui/material/Button";
import {Link, useNavigate} from "react-router-dom";
import AppContext from "../../context/AppContext";
import {URL_API} from "../../services/URL_API";
import {
    addProjectStarted,
    addProjectToDB,
    fetchCustomers,
    fetchTypologiesLinks,
    fetchUsersLinks
} from "../../context/Actions";
import {Alert, InputLabel, Typography} from "@mui/material";
import Snackbar from '@mui/material/Snackbar';
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import {format, parseISO} from "date-fns";
import Divider from "@mui/material/Divider";
import Grid from "@mui/material/Grid";

function ProjectCreationTable() {

    const navigate = useNavigate();

    const {state, dispatch} = useContext(AppContext);

    const {applicationCollections} = state;
    const collectionLinks = applicationCollections.data;

    // Perform typologies fetching -> Typologies dropdown
    const {typologiesLinks} = state;
    const fetchTypologiesData = typologiesLinks.data;
    useEffect(() => {
        let url = collectionLinks._links['typologies'].href;
        console.log(url)
        const request = {method: 'GET'};
        fetchTypologiesLinks(url, request, dispatch);
    }, []);


    // Perform customers fetching -> Customers dropdown
    const {customers} = state;
    const fetchCustomersData = customers.data;
    useEffect(() => {
        let url = collectionLinks._links['customers'].href;
        console.log(url)
        const request = {method: 'GET'};
        fetchCustomers(url, request, dispatch);
    }, []);


    // Perform users fetching -> Users dropdown
    const {usersLinks} = state;
    const fetchUsersData = usersLinks.data;
    useEffect(() => {
        let url = collectionLinks._links['usersAll'].href;
        console.log(url)
        const request = {method: 'GET'};
        fetchUsersLinks(url, request, dispatch);
    }, []);

    // Create project
    const {addProjects} = state;
    const createProjectData = addProjects.data;
    const createProjectError = addProjects.error;

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
        percentageOfAllocation: ''
    }

    const [project, setProject] = useState(initialState)

    useEffect(() => {
        dispatch(addProjectStarted())
    }, [])

    let creationMessage = <div></div>

    const [open, setOpen] = React.useState(false);

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


    if (createProjectError !== null) {
        creationMessage = (<div>
            <Snackbar
                open={open}
                autoHideDuration={1500}
                onClose={() => setOpen(false)}>
                <Alert variant="filled" severity="warning">Something went wrong...</Alert>
            </Snackbar>
        </div>);
    } else if (!Array.isArray(createProjectData)) {
        creationMessage = (<Snackbar
            open={open}
            autoHideDuration={1500}
            onClose={() => {
                setOpen(false);
                navigate('/projects/')
            }}>

            <Alert variant="filled" severity="success">Project {createProjectData.projectCode} created</Alert>
        </Snackbar>);
    }

    function createNewProject() {
        let url = `${URL_API}/api/projects`;
        addProjectToDB(url, dispatch, project);
        setOpen(true)
    }

    if (!Array.isArray(fetchUsersData) && !Array.isArray(fetchTypologiesData) && fetchCustomersData.length > 0) {

        return (
            <center>
                <div style={{width: '80%'}}>
                    {creationMessage}
                    <Box
                        component="form"
                        sx={{
                            '& .MuiTextField-root': {m: 2, width: '50ch'}
                        }}
                        noValidate
                        autoComplete="off"

                    >
                        <Typography variant="h5">Create new project</Typography>
                        <br/>

                        <Grid container
                              direction="row"
                              alignItems="center"
                              justifyContent="center">
                            <Grid item xs>
                                <TextField
                                    id="project-code"
                                    label={"Project code"}
                                    value={project.projectCode}
                                    onChange={(e) => setProject({...project, projectCode: e.target.value})}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </Grid>
                            <Grid item xs>
                                <TextField
                                    id="project-name"
                                    label="Project Name"
                                    value={project.projectName}
                                    onChange={(e) => setProject({...project, projectName: e.target.value})}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </Grid>
                            <Grid item xs>
                                <TextField
                                    id="project-description"
                                    label="Project Description"
                                    value={project.projectDescription}
                                    onChange={(e) => setProject({...project, projectDescription: e.target.value})}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </Grid>
                        </Grid>


                        <Grid container
                              direction="row"
                              alignItems="center"
                              justifyContent="center">
                            <Grid item xs>
                                <TextField
                                    id="project-business-sector"
                                    label="Business Sector"
                                    value={project.projectBusinessSector}
                                    onChange={(e) => setProject({...project, projectBusinessSector: e.target.value})}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />

                            </Grid>

                            <Grid item xs>
                                <TextField
                                    type='number'
                                    id="project-budget"
                                    label="Budget"
                                    value={project.projectBudget}
                                    onChange={(e) => setProject({...project, projectBudget: e.target.value})}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </Grid>

                            <Grid item xs>
                                <FormControl style={{width: '50ch'}}>
                                    <InputLabel id="demo-simple-select-standard-label">Project Typology </InputLabel>
                                    <Select
                                        labelId="project-typologies-label"
                                        id="customers-label"
                                        value={project.typologyDescription}
                                        onChange={(e) => setProject({...project, typologyDescription: e.target.value})}
                                        label="Project Typology">
                                        {Object.keys(fetchTypologiesData._links).map((dataItem) => (
                                            <MenuItem value={dataItem}>{dataItem}</MenuItem>))}
                                    </Select>
                                </FormControl>
                            </Grid>
                        </Grid>


                        <Grid container
                              direction="row"
                              alignItems="center"
                              justifyContent="center">

                            <Grid item xs>
                                <TextField
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
                                            setProject({
                                                ...project,
                                                startDate: format(parseISO(e.target.value), "dd/MM/yyyy")
                                            });
                                        }}
                                />
                            </Grid>

                            <Grid item xs>
                                <TextField
                                    type="date"
                                    id="project-end-Date"
                                    label="End Date"
                                    value={null}
                                    InputProps={{inputProps: {min: getToday()}}}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    onChange={(e) => {
                                        setProject({
                                            ...project,
                                            endDate: format(parseISO(e.target.value), "dd/MM/yyyy")
                                        });
                                    }}
                                />
                            </Grid>

                            <Grid item xs>
                                <TextField
                                    type='number'
                                    id="project-number-of-planned-sprints"
                                    label="Number of Planned Sprints"
                                    value={project.projectNumberOfPlannedSprints}
                                    onChange={(e) => setProject({
                                        ...project,
                                        projectNumberOfPlannedSprints: e.target.value
                                    })}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />

                            </Grid>

                        </Grid>

                        <div style={{width: '65%'}}>
                            <Grid container
                                  direction="row"
                                  alignItems="center"
                                  justifyContent="center">

                                <Grid item xs>
                                    <TextField
                                        type='number'
                                        id="project-sprint-duration"
                                        label="Project Sprint Duration"
                                        value={project.projectSprintDuration}
                                        onChange={(e) => setProject({
                                            ...project,
                                            projectSprintDuration: e.target.value
                                        })}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                    />

                                </Grid>

                                <Grid item xs>
                                    <FormControl style={{width: '50ch', alignItems: 'left'}}>
                                        <InputLabel id="demo-simple-select-standard-label">Customer </InputLabel>
                                        <Select
                                            labelId="project-customers-label"
                                            id="customers-label"
                                            value={project.customerName}
                                            onChange={(e) => setProject({...project, customerName: e.target.value})}
                                            label="Customer">
                                            {fetchCustomersData.map((dataItem) => (
                                                <MenuItem
                                                    value={dataItem.customerName}>{dataItem.customerName}</MenuItem>))}
                                        </Select>
                                    </FormControl>

                                </Grid>
                            </Grid>
                        </div>


                        <br></br>

                        <center>
                            <div style={{height: '60%', width: '90%'}}>
                                <Divider textAlign="left" variant={"middle"}> Project Manager</Divider>
                            </div>
                        </center>
                        <br/>


                        <Grid container
                              direction="row"
                              alignItems="center"
                              justifyContent="center">

                            <Grid item xs>
                                <FormControl style={{width: '50ch'}}>
                                    <InputLabel id="demo-simple-select-standard-label">Project Manager
                                        Email</InputLabel>
                                    <Select
                                        value={project.userEmail}
                                        onChange={(e) => setProject({...project, userEmail: e.target.value})}
                                        label="Project Manager Email">
                                        {Object.keys(fetchUsersData._links).map((dataItem) => (
                                            <MenuItem value={dataItem}>{dataItem}</MenuItem>))}
                                    </Select>
                                </FormControl>
                            </Grid>

                            <Grid item xs>
                                <TextField
                                    type='number'
                                    id="costPerHour"
                                    label="Cost Per Hour"
                                    value={project.costPerHour}
                                    onChange={(e) => setProject({...project, costPerHour: e.target.value})}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </Grid>

                            <Grid item xs>
                                <TextField
                                    type='number'
                                    id="percentageOfAllocation"
                                    label="Percentage Of Allocation"
                                    value={project.percentageOfAllocation}
                                    onChange={(e) => setProject({...project, percentageOfAllocation: e.target.value})}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </Grid>

                        </Grid>


                        <p></p>

                        <div>
                            <Button variant="contained" onClick={() => createNewProject()}> Create New Project </Button>
                        </div>

                        <p></p>

                        <Link to="/projects">
                            <Button variant="outlined">Cancel</Button>
                        </Link>


                    </Box>
                </div>
            </center>);
    }

}

export default ProjectCreationTable;

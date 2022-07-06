import * as React from "react";
import {useContext, useEffect, useState} from "react";
import {Alert, MenuItem, Select} from "@mui/material";
import TextField from "@mui/material/TextField";
import AppContext from "../../context/AppContext";
import {URL_API} from "../../services/URL_API";
import {addResourceToDB, createResourceStarted, fetchUsersLinks} from "../../context/Actions";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import FormHelperText from "@mui/material/FormHelperText";
import SendIcon from '@mui/icons-material/Send';
import Snackbar from "@mui/material/Snackbar";
import {format, parseISO} from "date-fns";
import {useNavigate} from "react-router-dom";


export default function ResourceForm() {

    const {state, dispatch} = useContext(AppContext);

    const {resources, selectedProject} = state;
    const {error, data} = resources;
    const eachResourcesData = resources.data;

    const navigate = useNavigate();

    const [role, setRole] = React.useState('');

    const getProjectCode = () => {
        return (selectedProject.data.projectCode);
    }

    const initialState = {
        userIdDto: '',
        projectCodeDto: getProjectCode(),
        startDateDto: '',
        endDateDto: '',
        costPerHourDto: '',
        percentageOfAllocationDto: '',
    }
    const [resource, setResource] = useState(initialState)


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

    const createNewResource = () => {

        let url;

        if (role === "Team Member") {
            url = selectedProject.data._links['addTeamMember'].href
        } else if (role === "Product Owner") {
            url = selectedProject.data._links['addProductOwner'].href
        } else if (role === "Scrum Master") {
            url = selectedProject.data._links['addScrumMaster'].href
        }

        addResourceToDB(url, dispatch, resource);
        setOpen2(true)

    }

    const {applicationCollections} = state;
    const collectionLinks = applicationCollections.data;

    // Perform users fetching -> Users dropdown
    const {usersLinks} = state;
    const fetchUsersData = usersLinks.data;
    useEffect(() => {
        dispatch(createResourceStarted());
        let url = collectionLinks._links['usersAll'].href;
        const request = {method: 'GET'};
        fetchUsersLinks(url, request, dispatch);
    }, []);


    const handleChange = (role) => {
        setRole(role.target.value);
    };

    let creationMessage = <div></div>

    const [open2, setOpen2] = React.useState(false);

    if (error !== null) {
        creationMessage = (<div>
            <Snackbar
                open={open2}
                autoHideDuration={1500}
                onClose={() => setOpen2(false)}>
                <Alert variant="filled" severity="error" color="error">Resource association failed</Alert>
            </Snackbar>
        </div>);
    } else if (!Array.isArray(data)) {
        if ("resourceID" in data) {

            creationMessage = (<Snackbar
                open={open2}
                autoHideDuration={1500}
                onClose={() => {
                    setOpen2(false);
                    navigate('/resources/projectCode/' + data.projectCode)
                }}>

                <Alert variant="filled" severity="success">Resource {data.userID} associated</Alert>
            </Snackbar>);
        }
    }

    let emailSelect
    if (!Array.isArray(fetchUsersData)) {
        emailSelect = (
            <p><FormControl style={{width: '100ch'}}>
                {creationMessage}
                <InputLabel id="demo-simple-select-standard-label">Select User Email </InputLabel>
                <Select
                    labelId="resource-user-label"
                    id="user-label"
                    value={eachResourcesData.userIdDto}
                    onChange={(e) => setResource({...resource, userIdDto: e.target.value})}
                    label="Project Manager">
                    {Object.keys(fetchUsersData._links).map((linkEmail) => (
                        <MenuItem value={linkEmail}>{linkEmail}</MenuItem>))}
                </Select>
            </FormControl></p>
        )
    }


    return (<div>
        <div>
            <center>
                <div>
                    <h2 className="MuiTypography-root MuiTypography-h4 css-q4657b-MuiTypography-root">Associate
                        Resource
                    </h2>
                    <h4 className=".MuiTypography-h5">Define your team roles
                    </h4>

                    {emailSelect}

                    <p><FormControl style={{width: '100ch'}}>
                        <TextField
                            type="date"
                            id="project-start-Date"
                            label="Start Date"
                            value={null}
                            InputProps={{inputProps: {min: getToday()}}}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(e) => {
                                setResource({
                                    ...resource, startDateDto: format(parseISO(e.target.value), "dd/MM/yyyy")
                                });
                            }}
                        /></FormControl></p>
                    <p><FormControl style={{width: '100ch'}}>

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
                                setResource({...resource, endDateDto: format(parseISO(e.target.value), "dd/MM/yyyy")});
                            }}
                        />

                    </FormControl></p>
                    <p><FormControl style={{width: '100ch'}}>

                        <TextField
                            required
                            labelID="end-date-resources-label"
                            type='number'
                            id="cost-per-hour"
                            label="Cost Per Hour"
                            onChange={(e) => setResource({...resource, costPerHourDto: e.target.value})}
                        /></FormControl></p>
                    <p><FormControl style={{width: '100ch'}}>
                        <TextField
                            labelID="end-date-resources-label"
                            type='number'
                            required
                            id="percentage-of-allocation"
                            label="Allocation (%)"
                            onChange={(e) => setResource({
                                ...resource, percentageOfAllocationDto: e.target.value
                            })}
                        /></FormControl></p>


                    <FormControl style={{width: '100ch'}}>
                        <InputLabel id="role">Select Role</InputLabel>
                        <Select
                            required
                            labelId="role-id"
                            id="role"
                            label="Role"
                            onChange={handleChange}
                            labelID="end-date-resources-label"


                        >
                            <MenuItem value={"Team Member"}>Team Member</MenuItem>
                            <MenuItem value={"Product Owner"}>Product Owner</MenuItem>
                            <MenuItem value={"Scrum Master"}>Scrum Master</MenuItem>

                        </Select>
                        <FormHelperText>Role In Project</FormHelperText>
                    </FormControl>

                    <Box sx={{
                        mx: 'auto',
                        width: 200,
                        p: 1,
                        m: 1,
                        textAlign: 'center',
                        fontSize: '0.875rem',
                        fontWeight: '700',
                    }}><Button variant="contained" padding="left"
                               onClick={() => createNewResource()}> ASSOCIATE</Button></Box>
                    <p></p>

                    <Button sx={{m: 1}} variant="outlined"
                            onClick={() => navigate('/resources/projectCode/' + selectedProject.data.projectCode)}> Back </Button>
                    {creationMessage}

                </div>
            </center>
        </div>
    </div>)

}


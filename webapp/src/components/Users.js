import * as React from 'react';
import {useContext, useEffect, useState} from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Tooltip from '@mui/material/Tooltip';
import IconButton from '@mui/material/IconButton';
import SearchIcon from '@mui/icons-material/Search';
import RefreshIcon from '@mui/icons-material/Refresh';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import DataTable from './DataTable';
import AppContext from "../context/AppContext";
import {URL_API} from "../services/URL_API";
import {
    fetchAllProjectLinks,
    fetchAllProjectLinksStarted,
    fetchProjectsData, fetchProjectsDataStarted,
    fetchUsers,
    fetchUsersLinks,
    fetchUsersLinksStarted,
    fetchUsersStarted, setSelectedProject, setSelectedUser
} from "../context/Actions";
import {useNavigate} from "react-router-dom";

export default function Users() {
    const navigate = useNavigate();
    const {state, dispatch, usersHeaders} = useContext(AppContext);

    const {usersDataList, usersLinks, applicationCollections} = state;

    const collectionLinks = applicationCollections.data;

    const links = usersLinks.data;
    const usersTableData = usersDataList.data;

    const [search, setSearch] = React.useState('');

    const [radioButton, setradioButton] = React.useState('');
    const [fetchUsersRunning, setFetchUsersRunning] = useState(true);

    useEffect(() => {
        dispatch(fetchUsersLinksStarted())
        dispatch(fetchUsersStarted())
        return () => {
            dispatch(fetchUsersLinksStarted())
            dispatch(fetchUsersStarted())
        }

    }, [])


    const searchHandleChange = (event) => {
        setSearch(event.target.value);
    };

    const radioHandleChange = (event) => {
        setradioButton(event.target.value);
    }

    const [mess, setMess] = React.useState('');

    const searchUsers = () => {

        setFetchUsersRunning(true)
        dispatch(fetchUsersLinksStarted())
        dispatch(fetchUsersStarted())


        if (radioButton === 'email') {
            URL = collectionLinks._links['usersEmail'].href + search
        } else {
            URL = collectionLinks._links['usersProfile'].href + search
        }

        const request = {method: 'GET'};

        fetchUsersLinks(URL, request, dispatch)

        if (radioButton === '') {
            setMess('Please choose a type of search.')
        } else if (usersTableData.length === 0) {
            setMess("User not found.")
        }

    }


    if (!Array.isArray(links)) {
        if (("_links" in links) && radioButton !== '') {
            if (fetchUsersRunning === true) {
                setFetchUsersRunning(false)
                Object.keys(links._links).map((userLink) => {
                    fetchUsers(links._links[userLink].href, {method: 'GET'}, dispatch)
                })
            }
        }
    }


    let table = <div></div>
    if (usersTableData.length > 0) {

        table = (<DataTable
                tableData={usersTableData}
                headers={usersHeaders}
                id={"email"}
                url={'/users'}
                onClickNavigate={true}
                onClickFunction={setSelectedUser}
                sortingField={"email"}
            />
        )
    } else {
        table = (
            <h3>
                {mess}
            </h3>
        )
    }

    return (
        <div>
            <Paper sx={{maxWidth: 936, margin: 'auto', overflow: 'hidden'}}>
                <AppBar
                    position="static"
                    color="default"
                    elevation={0}
                    sx={{borderBottom: '1px solid rgba(0, 0, 0, 0.12)'}}
                >
                    <Toolbar>
                        <Grid container spacing={2} alignItems="center">
                            <Grid item>
                                <SearchIcon color="inherit" sx={{display: 'block'}}/>
                            </Grid>
                            <Grid item xs>
                                <TextField
                                    fullWidth
                                    placeholder="Search by email address or user profile"
                                    InputProps={{
                                        disableUnderline: true,
                                        sx: {fontSize: 'default'},
                                    }}
                                    variant="standard"
                                    onChange={searchHandleChange}
                                />
                            </Grid>
                            <Grid item>
                                <Button variant="contained" sx={{mr: 1}}
                                        onClick={searchUsers}>
                                    Search user
                                </Button>
                            </Grid>
                        </Grid>
                    </Toolbar>
                </AppBar>


                <RadioGroup
                    style={{marginLeft: '.8rem'}}
                    row
                    aria-labelledby="demo-row-radio-buttons-group-label"
                    name="row-radio-buttons-group"
                >
                    <FormControlLabel value="email"
                                      onChange={radioHandleChange}
                                      control={<Radio/>} label="email"/>
                    <FormControlLabel value="profile"
                                      onChange={radioHandleChange}
                                      control={<Radio/>} label="profiles"/>
                </RadioGroup>
                {/*<Typography sx={{ my: 5, mx: 2 }} color="text.secondary" align="center">*/}
                {/*  No users for this project yet*/}
                {/*</Typography>*/}
                <div
                    style={{
                        marginLeft: '.8rem',
                        marginRight: '.8rem'
                    }}>

                    {table}
                </div>

            </Paper>

            <br/>
            <br/>
            <Button sx={{ m: 1 }} variant="outlined" onClick={() => navigate('/')}> Back </Button>

        </div>

    );
}

import React, {useContext} from "react";
import AppContext from "../context/AppContext";
import UpdateUserTable from "../components/users/UpdateUserTable";
import UpdatePassword from "../components/users/UpdatePassword";
import AddProfile from "../components/users/AddProfile";
import {CircularProgress} from "@mui/material";
import RemoveProfile from "../components/users/RemoveProfile.js";
import AllocatedProjects from "../components/users/AllocatedProjects";
import {Typography} from "@mui/material";
import Divider from "@mui/material/Divider";
import {styled} from '@mui/material/styles';
import {useNavigate} from "react-router-dom";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";

const Root = styled('div')(({theme}) => ({
    width: '100%',
    ...theme.typography.body2,
    '& > :not(style) + :not(style)': {
        marginTop: theme.spacing(2),
    },
}));

const button = {
    justifyContent: 'center',
    alignItems: 'center',
}

function UserUpdate() {
    const navigate = useNavigate();
    const {state} = useContext(AppContext);
    const {selectedUser} = state;
    const {loading, error, data} = selectedUser;


    if (loading === true) {
        return (
            <div>
                <center>
                    <h3>Loading .... <CircularProgress/></h3>
                </center>
            </div>);
    } else {
        if (error !== null) {
            return (<div>
                <center>
                    <h3>Server connection failed</h3>
                </center>
            </div>);
        } else {
            if (data.email.length > 0) {
                return (
                    <center>
                        <div style={{height: '60%', width: '80%'}}>
                            <Typography variant="h5">{data.userName} Account</Typography>

                            <br/>
                            <Root>
                                <Divider textAlign="left">Account information</Divider>
                                <UpdateUserTable/>
                                <br/>

                                <Divider textAlign="left">Update password</Divider>
                                <UpdatePassword userEmail={data.email}/>
                                <br/>

                                <Divider textAlign="left">Allocated projects</Divider>
                                <AllocatedProjects/>
                                <br/>


                                <Divider textAlign="left">User profiles</Divider>
                                <center>

                                    <div className="row">
                                        <div className="left-panel">
                                            <RemoveProfile> </RemoveProfile>
                                        </div>
                                        <div className="middle-panel">
                                            <Divider orientation="vertical" variant="middle" flexItem/>
                                        </div>
                                        <div className="right-panel">
                                            <AddProfile> </AddProfile>
                                        </div>
                                    </div>

                                </center>
                            </Root>

                            <br/>
                        </div>

                        <div>
                            <Button sx={{m: 1}} variant="outlined" onClick={() => navigate('/users')}> Cancel </Button>
                        </div>
                    </center>
            )
            ;
            } else
                {
                    return (<div>
                        <h3>No such email in store</h3>
                    </div>);

                }

            }

            }

            }

            export default UserUpdate;
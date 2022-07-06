import * as React from 'react';
import {useContext, useEffect} from "react";
import AppContext from "../../context/AppContext";
import {fetchActivities} from "../../context/Actions";
import DataTable from "../DataTable";
import {useNavigate} from "react-router-dom";
import Button from "@mui/material/Button";
import {Typography} from "@mui/material";

const Activities = () => {
    const {state, dispatch, statusOfActivitiesHeaders} = useContext(AppContext);
    const {activities, selectedProject} = state;
    const {loading, error} = activities;
    const fetchActivitiesData = activities.data;

    const navigate = useNavigate();
    const projectCode = selectedProject.data.projectCode

    const projectData = selectedProject.data;

    useEffect(() => {
        let url = (projectData._links['activities'].href)
        const request = {method: 'GET'};
        fetchActivities(url, request, dispatch);
    }, [])

    let button = (<Button sx={{m: 1}} variant="outlined"
                          onClick={() => navigate('/projects/' + `${projectCode}`)}> Back </Button>)


    if (loading === true) {
        return (
            <div>
                <center>
                    <h1>Loading ....</h1>
                    {button}
                </center>
            </div>
        );
    } else {
        if (error !== null) {
            return (<div>
                <center>
                    <h3>
                        Error ....
                    </h3>
                    {button}
                </center>
            </div>)
        } else {
            if (fetchActivitiesData.length > 0) {
                return (
                    <div>
                        <center>
                            <Typography variant="h5">Project {projectCode} Activities</Typography>
                            <br/>
                            <div style={{height: '60%', width: '80%'}}>
                                <DataTable tableData={fetchActivitiesData}
                                           headers={statusOfActivitiesHeaders}
                                           id={"activityCode"}
                                           url={"/activities/" + projectData.projectCode}
                                           sortingField={"activityCode"}/>
                            </div>
                            {button}
                        </center>
                    </div>
                );
            } else {
                return (
                    <center>
                        <div>
                            <h3> No data ....</h3>
                            {button}
                        </div>
                    </center>
                );
            }
        }
    }
};

export default Activities;



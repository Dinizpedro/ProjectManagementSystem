import React, { useContext, useEffect, useState } from "react";
import AppContext from "../../context/AppContext";
import DataTable from "../DataTable";
import { fetchAllProjectLinks, fetchAllProjectLinksStarted, fetchProjectsData, fetchProjectsDataStarted, setSelectedProject } from '../../context/Actions';
import { Typography } from "@mui/material";

const ProjectsTable = () => {

    const { state, dispatch, projectHeaders } = useContext(AppContext);

    const {applicationCollections} = state;
    const collectionLinks = applicationCollections.data;

    const {projectLinks} = state;
    const links = projectLinks.data;

    const {projectDataList} = state;
    const projectData = projectDataList.data;


    useEffect(() => {
        dispatch(fetchProjectsDataStarted())
        dispatch(fetchAllProjectLinksStarted())

        let url = collectionLinks._links['projects'].href;
        const request = { method: 'GET' };
        fetchAllProjectLinks(url, request, dispatch);

        return () => {
            dispatch(fetchProjectsDataStarted())
            dispatch(fetchAllProjectLinksStarted())
        }

    }, [])

    const [fetchProjectsRunning, setFetchProjectsRunning] = useState(true);

    if (!Array.isArray(links)) {
        if (fetchProjectsRunning === true) {
            setFetchProjectsRunning(false)
            Object.keys(links._links).map((projectLink) => {
                fetchProjectsData(links._links[projectLink].href, { method: 'GET' }, dispatch)
            })
        }

        if (projectData.length > 0) {
            return (
                <div>
                <DataTable
                    tableData={projectData}
                    headers={projectHeaders}
                    id={"projectCode"}
                    url={'/projects'}
                    onClickFunction={setSelectedProject}
                    onClickNavigate={true}
                    sortingField={"projectCode"}
                />
                </div>
            );
        }
    } else {
        return (

            <div>
                <Typography variant="h5" color="inherit" >Projects</Typography>
                <br/>
                <h3>No data ...</h3>
            </div>);
    }
}

export default ProjectsTable;

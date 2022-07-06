import React, { useContext, useEffect, useState } from "react";
import AppContext from "../../context/AppContext";
import DataTable from "../DataTable";
import { fetchAllSprintLinks, fetchAllSprintLinksStarted, fetchSprintsData, fetchSprintsDataStarted, setSelectedSprint } from "../../context/Actions";
import { Typography } from "@mui/material";

let pageLimit = 10;

const SprintTable = () => {

    const { state, dispatch, sprintHeaders } = useContext(AppContext);

    const { sprintLinks } = state;
    const links = sprintLinks.data;

    const { sprintDataList } = state;
    const sprintData = sprintDataList.data;

    const { selectedProject } = state;
    const projectData = selectedProject.data;

    const { addSprints } = state
    const addSprintLoading = addSprints.loading

    const [fetchSprintsRunning, setFetchSprintsRunning] = useState(true);

    const componentWillUnmount = () => {
        return () => {
            dispatch(fetchAllSprintLinksStarted())
            dispatch(fetchSprintsDataStarted())
        }
    }

    useEffect(() => {
        dispatch(fetchAllSprintLinksStarted())
        dispatch(fetchSprintsDataStarted())

        setFetchSprintsRunning(true)

        let url = (projectData._links['sprints'].href)
        const request = { method: 'GET' };
        fetchAllSprintLinks(url, request, dispatch);

        return componentWillUnmount();

    }, [addSprintLoading])

    if (!Array.isArray(links)) {
        if (fetchSprintsRunning === true) {
            setFetchSprintsRunning(false)
            Object.keys(links._links).slice(0, pageLimit).map((sprintLink) => {
                fetchSprintsData(links._links[sprintLink].href, { method: 'GET' }, dispatch)
            })
        }

        if (sprintData.length > 0) {
            return (
                <div>
                    <DataTable
                        tableData={sprintData}
                        headers={sprintHeaders}
                        id={"sprintNumber"}
                        url={'/projects'}
                        onClickFunction={setSelectedSprint}
                        onClickNavigate={false}
                        sortingField={"sprintNumber"}
                    />
                </div>
            );
        }
    } else {
        return (
            <div>
                <h3>No Sprints were found ...</h3>
            </div>);
    }
}

export default SprintTable;

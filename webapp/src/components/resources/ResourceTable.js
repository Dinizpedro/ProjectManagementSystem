import * as React from "react";
import {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import DataTable from "../DataTable";
import {
    fetchAllResourceLinks,
    fetchAllResourceLinksStarted,
    fetchResourcesData,
    fetchResourcesDataStarted,
} from "../../context/Actions";
import {Typography} from "@mui/material";
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";
let pageLimit = 10;

const ResourceTable = () => {
    const { state, dispatch, resourceHeaders } = useContext(AppContext);

    const { resourceLinks } = state;
    const links = resourceLinks.data;

    const {resourceDataList} = state;
    const resourceData = resourceDataList.data;

    const { selectedProject } = state;
    const projectData = selectedProject.data;

    const [fetchResourcesRunning, setFetchResourcesRunning] = useState(true);

    const componentWillUnmount = () => {
        return () => {
            dispatch(fetchResourcesDataStarted())
            dispatch(fetchAllResourceLinksStarted())
        }
    }

    useEffect(() => {
        dispatch(fetchResourcesDataStarted())
        dispatch(fetchAllResourceLinksStarted())

        let url = (projectData._links['resources'].href)
        const request = { method: 'GET' };
        fetchAllResourceLinks(url, request, dispatch);

        return componentWillUnmount();

    }, [dispatch])


    if (!Array.isArray(links)) {
        if (fetchResourcesRunning === true) {
            setFetchResourcesRunning(false)
            Object.keys(links._links).slice(0, pageLimit).map((resourceLink) => {
                fetchResourcesData(links._links[resourceLink].href, { method: 'GET' }, dispatch)
            })
        }
    if (resourceData.length > 0) {
        return (
            <div>
                <center>
                    <div style={{ height: '60%', width: '80%' }}>
                        <DataTable
                            tableData={resourceData}
                            headers={resourceHeaders}
                            id={"resourceID"}
                            url={'/projects'}
                            onClickNavigate={false}
                            sortingField={"resourceID"}
                        />
                        <p></p>
                    </div>
                </center>
            </div>);
    } else {
        return (<div>
            <Typography variant="h5" color="inherit" >Resources</Typography>
            <h3>No Resources were found ...</h3>
        </div>);

    }
}
}
export default ResourceTable;

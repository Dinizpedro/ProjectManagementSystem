import {useContext, useEffect, useState} from 'react';
import AppContext from "../../context/AppContext";
import {
    fetchUserStoriesDataList, fetchUserStoriesDataListStarted,
    fetchUserStoriesLinks, fetchUserStoriesLinksStarted,
    setSelectedUserStory
} from "../../context/Actions";
import DataTable from "../DataTable";
import { Typography } from "@mui/material";

const ProductBacklog = () => {
    const { state, dispatch, userStoryHeaders } = useContext(AppContext);
    const { userStoriesLinks, userStoriesDataList, addUserStory, selectedProject } = state;
    const { loading, error, data } = userStoriesLinks;

    const projectData = selectedProject.data;

    const [fetchUSRunning, setFetchUSRunning] = useState(true);

    let variable = addUserStory.data
    useEffect(() => {
        dispatch(fetchUserStoriesDataListStarted())
        dispatch(fetchUserStoriesLinksStarted())
        setFetchUSRunning(true)

        let url = (projectData._links['productBacklog'].href);
        const request = { method: 'GET' };
        fetchUserStoriesLinks(url, request, dispatch);

        return () => {
            dispatch(fetchUserStoriesDataListStarted())
            dispatch(fetchUserStoriesLinksStarted())
        }

    },[variable] )


    if (loading === true) {
        return (
            <div>
                <center>
                    <h1>Loading ....</h1>
                </center>
            </div>);
    } else {
        if (error !== null) {
            return (<div>
                <center>
                    <h3>Error ....</h3>
                </center>
            </div>);
        } else {
            if (("_links" in data)) {

                if (fetchUSRunning === true) {
                    setFetchUSRunning(false)
                    Object.keys(data._links).map((usLink) => {
                        fetchUserStoriesDataList(data._links[usLink].href, { method: 'GET' }, dispatch)
                    })
                }

                return (
                    <center>
                        <div style={{ height: '60%', width: '80%' }}>
                            <DataTable
                                tableData={userStoriesDataList.data}
                                headers={userStoryHeaders}
                                id={"code"}
                                url={"/productBacklog/" + projectData.projectCode}
                                onClickNavigate={true}
                                onClickFunction={setSelectedUserStory}
                                sortingField={"priority"}
                            />
                        </div>
                    </center>
                );
            } else {
                return (
                    <div>
                        <br />
                        <h3>No UserStories found ...</h3>
                    </div>
                );
            }
        }
    }
};

export default ProductBacklog;

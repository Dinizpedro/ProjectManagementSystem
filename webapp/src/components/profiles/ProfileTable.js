import React, {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import {
    fetchProfileDataList,
    fetchProfileDataListStarted,
    fetchProfileLinks,
    fetchProfileLinksStarted
} from "../../context/Actions";
import {CircularProgress} from "@mui/material";
import DataTable from "../DataTable";

function ProfileTable() {
    const {state, dispatch, profileHeaders} = useContext(AppContext);

    // Fetch Profile Variables
    const {profileLinks, profileDataList,addProfiles, applicationCollections} = state;
    const profileLinksLoading = profileLinks.loading;
    const profileLinksError = profileLinks.error;
    const profileLinksData = profileLinks.data;

    const profileDataListData = profileDataList.data;

    const collectionLinks = applicationCollections.data

    // Control variable
    const addProfileLoading = addProfiles.loading;

    const [fetchProfilesRunning, setFetchProfilesRunning] = useState(false);

    useEffect(() => {
        dispatch(fetchProfileLinksStarted())
        dispatch(fetchProfileDataListStarted())

        setFetchProfilesRunning(true)
        let url = collectionLinks._links['profiles'].href;
        const request = {method: 'GET'};
        fetchProfileLinks(url, request, dispatch);

        return () => {
            dispatch(fetchProfileLinksStarted())
            dispatch(fetchProfileDataListStarted())
        }

    }, [addProfileLoading])


    if (profileLinksLoading === true) {
        return (
            <div>
                <center>
                    <h3>Loading .... <CircularProgress/></h3>
                </center>
            </div>);
    } else {
        if (profileLinksError !== null) {
            return (<div>
                <center>
                    <h3>Server connection failed</h3>
                </center>
            </div>);
        } else {
            if (!Array.isArray(profileLinksData)) {
                if (fetchProfilesRunning === true) {
                    setFetchProfilesRunning(false)
                    Object.keys(profileLinksData._links).map((profileLink) => {
                        fetchProfileDataList(profileLinksData._links[profileLink].href, {method: 'GET'}, dispatch)
                    })
                }

                if (profileDataListData.length > 0) {
                    return (
                        <div>
                            <center>
                                <div style={{height: '40%', width: '40%'}}>
                                    <DataTable
                                        tableData={profileDataListData}
                                        headers={profileHeaders}
                                        id={"profileDescription"}
                                        onClickNavigate={false}
                                        sortingField={"profileDescription"}
                                    >
                                    </DataTable>
                                </div>
                                <p></p>
                            </center>
                        </div>
                    );
                }
            } else {
                return (<div>
                    <h3>No profiles in store</h3>
                </div>);
            }
        }
    }
}

export default ProfileTable;

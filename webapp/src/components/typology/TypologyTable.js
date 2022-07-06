import React, {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import {
    fetchTypologyDataList,
    fetchTypologyDataListStarted,
    fetchTypologiesLinks,
    fetchTypologiesLinksStarted,
}from '../../context/Actions';
import {CircularProgress} from "@mui/material";
import DataTable from "../DataTable";

function TypologyTable () {

    const {state, dispatch, typologyHeader} = useContext(AppContext);


    // Fetch Variables

    const {typologiesLinks, typologyDataList, addTypology, applicationCollections} = state;
    const typologiesLinksLoading = typologiesLinks.loading;
    const typologiesLinksError = typologiesLinks.error;
    const typologiesLinksData = typologiesLinks.data;

    const typologyDataListData = typologyDataList.data;

    const collectionLinks = applicationCollections.data

    // Control variable
    const addTypologyLoading = addTypology.loading;

    const [fetchTypologiesRunning, setFetchTypologiesRunning] = useState(false);

    useEffect(() => {
        dispatch(fetchTypologiesLinksStarted())
        dispatch(fetchTypologyDataListStarted())

        setFetchTypologiesRunning(true)
        let url = collectionLinks._links['typologies'].href;
        const request = {method: 'GET'};
        fetchTypologiesLinks(url, request, dispatch);
        return () => {
            dispatch(fetchTypologiesLinksStarted())
            dispatch(fetchTypologyDataListStarted())
        }

    }, [addTypologyLoading])

    if (typologiesLinksLoading === true) {
        return (
            <div>
                <center>
                    <h3>Loading ...<CircularProgress/></h3>
                </center>
            </div>);
    } else {
        if (typologiesLinksError !== null) {
            return (<div>
                <center>
                    <h3>Server connection failed ...</h3>
                </center>
            </div>);
        } else {
            if (!Array.isArray(typologiesLinksData)) {

                if (fetchTypologiesRunning === true) {
                    setFetchTypologiesRunning(false)
                    Object.keys(typologiesLinksData._links).map((typologyLink) => {
                        fetchTypologyDataList(typologiesLinksData._links[typologyLink].href, {method: 'GET'}, dispatch)
                    })
                }


                if (typologyDataListData.length > 0) {
                    return (
                        <div>
                            <center>
                                <div style={{height: '40%', width: '40%'}}>
                                    <DataTable
                                        tableData={typologyDataListData}
                                        headers={typologyHeader}
                                        id={"description"}
                                        onClickNavigate={false}
                                        sortingField={"description"}
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
                    <h3>No Typologies</h3>
                </div>);
            }
        }
    }
}

export default TypologyTable;

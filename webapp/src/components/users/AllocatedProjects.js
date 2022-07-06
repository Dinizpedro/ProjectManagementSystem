import DataTable from "../DataTable";
import AppContext from "../../context/AppContext";
import React, {useContext, useEffect} from "react";
import {fetchAllocatedProjects, fetchAllocatedProjectsStarted} from "../../context/Actions";
import {CircularProgress} from "@mui/material";

function AllocatedProjects() {

    const {state, dispatch, allocatedProjectsHeaders} = useContext(AppContext);
    const {allocatedProjects, selectedUser} = state;
    const {loading, error, data} = allocatedProjects;

    useEffect(() => {
        dispatch(fetchAllocatedProjectsStarted())
        let url = selectedUser.data._links["Get Allocated Projects"].href;
        const request = {method: 'GET'};
        fetchAllocatedProjects(url, request, dispatch);

        return () => {
            dispatch(fetchAllocatedProjectsStarted())
        }
    }, [])

    if (loading === true) {
        return (
            <div>
                <center>
                    <h3>Loading .... <CircularProgress/></h3>
                </center>
            </div>
        )
    } else {
        if (error !== null) {
            return (
                <div>
                    <center>
                        <h3>
                            User is not allocated to any project...
                        </h3>
                    </center>
                </div>
            )
        } else {
            if (data.length > 0) {
                return (
                    <div>
                        <h1 className="MuiTypography-root MuiTypography-h5 css-q4657b-MuiTypography-root">
                            Allocated Projects
                        </h1>

                        <br></br>

                        <center>
                            <div style={{height: '60%', width: '80%'}}>
                                <DataTable
                                    tableData={data}
                                    headers={allocatedProjectsHeaders}
                                    id={"projectName"}
                                    onClickNavigate={false}
                                    sortingField={"projectName"}
                                >
                                </DataTable>
                            </div>
                        </center>

                    </div>
                )
            } else {
                if (data.length === 0) {
                    return (
                        <div>
                            <center>
                                <h3>
                                    User is not allocated to any project...
                                </h3>
                            </center>
                        </div>
                    )
                }
            }
        }
    }
}

export default AllocatedProjects;

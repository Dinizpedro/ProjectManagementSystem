import React, {useContext, useEffect} from "react";
import AppContext from "../../context/AppContext";
import {URL_API} from '../../services/URL_API';
import {fetchCustomers} from '../../context/Actions';
import DataTable from "../DataTable";
import {CircularProgress} from "@mui/material";

const AppCustomers = () => {

    const {state, dispatch, customerHeaders} = useContext(AppContext);

    const {customers, applicationCollections} = state;

    const collectionLinks = applicationCollections.data;

    const fetchCustomersLoading = customers.loading;
    const fetchCustomersError = customers.error;
    const fetchCustomersData = customers.data;


    let addCustomerStateSpy = fetchCustomersData

    useEffect(() => {
        let url = collectionLinks._links['customers'].href;
        const request = {method: 'GET'};
        fetchCustomers(url, request, dispatch);
    }, [addCustomerStateSpy])

    if (fetchCustomersLoading === true) {
        return (
            <div>
                <center>
                    <h3>Loading .... <CircularProgress/></h3>
                </center>
            </div>);
    } else {
        if (fetchCustomersError !== null) {
            return (<div>
                <center>
                    <h3>Server connection failed</h3>
                </center>
            </div>);
        } else {
            if (fetchCustomersData.length > 0) {
                return (
                    <center>
                        <div style={{height: '60%', width: '40%'}}>
                            <DataTable tableData={fetchCustomersData}
                                       headers={customerHeaders}
                                       id={"customerName"}
                                       onClickNavigate={false}
                                       sortingField={"customerName"}
                            />
                            <p></p>
                        </div>
                    </center>
                );
            } else {
                return (<div>
                    <h3>
                        No data ....
                    </h3>
                </div>);
            }
        }
    }
};

export default AppCustomers;

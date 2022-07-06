import Button from "@mui/material/Button";
import * as React from "react";
import {useContext, useState} from "react";
import {setStatusStarted, setStatusToDB, setStatusFailure} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import {Input} from "@material-ui/core";

const ChangeStatus = () => {

    const {state, dispatch} = useContext(AppContext);
    const {selectedUserStory} = state;
    const { data } = selectedUserStory;

    const [changeForm, setChangeForm] = useState(false);
    const [status, setStatus] = useState(0);

    let changeStatusButton;
    if (!changeForm) {
        changeStatusButton = <Button variant="contained" onClick={() => changeForm ?
            setChangeForm(false) :
            setChangeForm(true)}>
            Change Status</Button>
    }

    function setStatusConfirm(){
        let url = data._links['updateStatus'].href
        setStatusToDB(url,dispatch,status)
    }

    let changeStatusBox;
    if (changeForm) {
        changeStatusBox = (
            <div>
                <center>
                    <Input type="string" id="changeStatus" variant="outlined"
                           onChange={(e) => setStatus(e.target.value)}
                    />
                    <br/>
                    <p/>
                    <Button variant="contained" onClick={() => setStatusConfirm()}> Confirm </Button>
                </center>
            </div>
        )
    }

    return (
        <div>
            <center>
                {changeStatusButton}
                <br/>
                <br/>
                {changeStatusBox}
            </center>
        </div>
    )
}

export default ChangeStatus;
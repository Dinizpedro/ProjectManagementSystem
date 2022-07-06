import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import * as React from "react";
import {useContext, useState} from "react";
import {setPriorityStarted, setPriorityToDB} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import {Input} from "@material-ui/core";
import FormLabel from "@mui/material/FormLabel";
import Snackbar from "@mui/material/Snackbar";
import {Alert} from "@mui/material";
import {useNavigate} from "react-router-dom";

const ChangePriority = () => {

    const navigate = useNavigate();

    const {state, dispatch} = useContext(AppContext);
    const {selectedUserStory, selectedProject} = state;
    const {data} = selectedUserStory;

    const [priority, changePriority] = useState(0);

    const {setPriority} = state;


    const [open, setOpen] = React.useState(false);
    let creationMessage;

    if (setPriority.error !== null) {

        creationMessage = (<div>
            <Snackbar
                open={open}
                autoHideDuration={1500}
                onClose={() => {
                    setOpen(false);
                }}>
                <Alert variant="filled" severity="warning">Something went wrong...</Alert>
            </Snackbar>
        </div>);
    }
    else{

        creationMessage = (<div>
            <Snackbar
                open={open}
                autoHideDuration={1500}
                onClose={() => {
                    setOpen(false);
                    navigate('/productBacklog/' + selectedProject.data.projectCode)
                }}>

                <Alert variant="filled" severity="success">Priority Changed</Alert>
            </Snackbar>
        </div>);

    }

    function setPriorityConfirm() {
        let url = data._links['updatePriority'].href
        dispatch(setPriorityStarted())
        setPriorityToDB(url, dispatch, priority)
        setOpen(true);
    }


    let changePriorityBox = (
        <div>
            <center>
                <Input type="number" id="changePriority" variant="outlined" placeholder={"Current priority: " + selectedUserStory.data.priority}
                       onChange={(e) => changePriority(e.target.value)}
                />
                <br/>
                <p/>
                <Button variant="contained" onClick={() => setPriorityConfirm()}> Confirm </Button>
            </center>
        </div>
    )


    return (
        <div>
            {creationMessage}
            <center>
                <br/>
                {changePriorityBox}
            </center>
        </div>
    )
}

export default ChangePriority;
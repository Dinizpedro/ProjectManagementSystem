import * as React from 'react';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import {URL_API} from "../../services/URL_API";
import Button from "@mui/material/Button";
import {fetchUser} from "../../context/Actions";

export default function RemoveProfile() {

    let message = <div>as </div>

    const {state, dispatch} = useContext(AppContext);

    const {addProfileToUser, selectedUser} = state;

    const {loading, data, error} = selectedUser;

    const [profile, setProfile] = React.useState([])

    const [mess, setMess] = React.useState('');

    // Update table if a new Profile was added or current profile removed
    const [userProfileList, setUserProfileList] = useState(data.userProfileList)
    const addProfileData = addProfileToUser.data

    useEffect(() => {
        let URL = data._links['self'].href;
        const request = {method: 'GET'};
        fetchUser(URL, request, dispatch)
    }, [userProfileList,data,addProfileData])

    useEffect(() => {
        setMess('')
    }, [addProfileData])

    const removeProfile = () => {

        let URL = data._links['Remove Profile'].href;

        if (data.userProfileList.length <= 1) {
            setMess("Fail: User needs at least one profile.");
        } else if (data.userProfileList.length > 1) {

            fetch(URL, {method: "DELETE",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(profile)})
            setMess('Profile successfully removed.');
            setUserProfileList(profile)
        }

    }

    const handleChangeProfile = (event) => {
        setProfile(event.target.value);
    };


    return (<div>
            <FormControl>
                <FormLabel id="demo-radio-buttons-group-label">User's profile list:</FormLabel>
                <RadioGroup
                    aria-labelledby="demo-radio-buttons-group-label"
                    defaultValue="female"
                    name="radio-buttons-group"
                >
                    {
                        data.userProfileList.map((dataItem) => (
                            <FormControlLabel onChange={handleChangeProfile} value={dataItem} control={<Radio/>}
                                              label={dataItem}/>
                        ))

                    }

                </RadioGroup>

                {<Button variant="contained" onClick={removeProfile}>
                    Remove Profile
                </Button>}

            </FormControl>

            <p></p>

            {mess}
        </div>
    );
}
import * as React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Button from "@mui/material/Button";
import {URL_API} from "../../services/URL_API";
import {useContext, useEffect} from "react";
import AppContext from "../../context/AppContext";
import {
    addProfileToDB,
    addProfilesToUser,
    fetchProfileLinks,
    addProfileToUserFailure,
    fetchUsersLinksStarted, addProfileToUserStarted
} from "../../context/Actions";
import FormLabel from "@mui/material/FormLabel";

export default function AddProfile() {
    const {state, dispatch} = useContext(AppContext);
    const {profileLinks, addProfileToUser, selectedUser, applicationCollections} = state;
    const {loading, data, error} = profileLinks

    const addProfileError = addProfileToUser.error
    const addProfileData = addProfileToUser.data
    const [mess, setMess] = React.useState('');

    const collectionLinks = applicationCollections.data

    useEffect(() => {
        let url = collectionLinks._links['profiles'].href;
        const request = {method: 'GET'};
        fetchProfileLinks(url, request, dispatch);
    }, []);

    let message = <div></div>

    const [profile, setProfile] = React.useState('');

    const handleChange = (event) => {
        setProfile(event.target.value);
    };

    const addProfile = () => {

        let URL = selectedUser.data._links['Add Profile'].href;

        /*let addProfileURL = `${URL_API}/profiles/` + profile + '/updateuser/' + Email.Email;*/

        const request = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(profile)

        }
        addProfilesToUser(URL, request, dispatch)


        if (selectedUser.data.userProfileList.includes(profile)) {
            setMess('Fail: User already has that profile.')
        } else {
            setMess('')
        }

    }


    let dataItems
    if (!Array.isArray(data)) {
        dataItems = Object.keys(data._links)

        return (
            <div>
                <FormControl>
                    <FormLabel id="demo-radio-buttons-group-label">Add profile: </FormLabel>
                    <FormControl variant="standard" sx={{m: 1, minWidth: 120}}>


                        <InputLabel id="demo-simple-select-standard-label">Choose a new profile</InputLabel>
                        <Select
                            labelId="demo-simple-select-standard-label"
                            id="demo-simple-select-standard"
                            value={profile}
                            onChange={handleChange}
                            label="Profile"
                        >
                            {
                                dataItems.map((dataItem) => (
                                    <MenuItem value={dataItem}>{dataItem}</MenuItem>
                                ))
                            }
                        </Select>
                    </FormControl>


                    <Button variant="contained" onClick={addProfile}>
                        Add Profile
                    </Button>
                    {mess}
                </FormControl>
            </div>
        )
    }

}

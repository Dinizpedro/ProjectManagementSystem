import React, {useContext, useEffect} from "react";
import {
    AppBar,
    Toolbar,
    CssBaseline,
    Typography,
    makeStyles,
} from "@material-ui/core";

import {Link} from "react-router-dom";
import AppProvider from "../context/AppProvider";
import AppContext from "../context/AppContext";
import {URL_API} from "../services/URL_API";
import {fetchAppCollectionLinks, fetchProfiles} from "../context/Actions";
import Button from "@mui/material/Button";

const useStyles = makeStyles((theme) => ({
  navlinks: {
    marginLeft: theme.spacing(10),
    display: "flex",
  },
  logo: {
    flexGrow: "1",
    cursor: "pointer",
    fontSize: "20px",
  },
  link: {
    textDecoration: "none",
    color: "white",
    fontSize: "20px",
    marginLeft: theme.spacing(13),
    "&:hover": {
      color: "#ebebf9",
      borderBottom: "1px solid white",
    },
  },
}));

function Navbar() {

    const {state, dispatch} = useContext(AppContext);
    const {applicationCollections} = state
    const {loading, error, data} = applicationCollections

    useEffect(() => {
        let url = `${URL_API}`;
        const request = {method: 'OPTIONS'};
        fetchAppCollectionLinks(url, request, dispatch);
    }, [])


    const classes = useStyles();

    let projectsLink
    let usersLink
    let customersLink
    let profilesLink
    let typologiesLink


    if (!Array.isArray(data)) {

        if (data._links['projects'] != null) {
            projectsLink = (<Link to="/projects" className={classes.link}>
                Projects
            </Link>)
        }

        if (data._links['users'] != null) {
            usersLink = (<Link to="/users" className={classes.link}>
                Users
            </Link>)
        }

        if (data._links['customers'] != null) {
            customersLink = (<Link to="/customers" className={classes.link}>
                Customers
            </Link>)
        }

        if (data._links['profiles'] != null) {
            profilesLink = (<Link to="/profiles" className={classes.link}>
                Profiles
            </Link>)
        }

        if (data._links['typologies'] != null) {
            typologiesLink = (<Link to="/typologies" className={classes.link}>
                Typologies
            </Link>)
        }
    }

    return (
        <AppBar position="static">
            <CssBaseline/>
            <Toolbar>
                <Typography className={classes.logo}>
                    Javalicious
                </Typography>
                <div className={classes.navlinks}>
                    <Link to="/" className={classes.link}>
                        Home
                    </Link>
                    {projectsLink}
                    {usersLink}
                    {customersLink}
                    {profilesLink}
                    {typologiesLink}
                </div>
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;

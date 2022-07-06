import React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Navbar from "../components/Navbar";
import Users from "../components/Users";
import UserStory from "../components/userStories/UserStory";
import Profiles from "../pages/Profiles";
import UserStories from "../pages/UserStories";
import ResourceForm from "../components/resources/ResourceForm";

import Customers from "../pages/CustomersPage";
import UserStoryDetails from "../components/userStories/UserStoryDetails";
import TypologyTable from "../pages/Typologies";
import UserUpdate from "../pages/UserUpdate";
import Home from "../pages/Home";
import {Projects} from "../pages/Projects";
import ProjectUpdateTable from "../components/projects/ProjectUpdateTable";
import ProjectCreationTable from "../components/projects/ProjectCreationTable";
import Activities from "../components/projects/Activities";
import {Sprints} from "../pages/Sprints";
import Resources from "../pages/Resources";
import NotFound from "../components/NotFound";

export default function MainRoute() {

    return (
        <Router>
            <div>
                <Navbar/>
                <br/>
                <br/>
                <Routes>
                    <Route path='' element={<Home/>}></Route>
                    <Route path='projects' element={<Projects/>}></Route>

                    <Route path='users' element={<Users/>}></Route>
                    <Route path='projects/:projectCode' element={<ProjectUpdateTable/>}></Route>

                    <Route path='projects/:projectCode/userstory' element={<UserStory/>}></Route>
                    <Route path='productBacklog/:projectCode/:code' element={<UserStoryDetails/>}></Route>

                    <Route path='projects/newProject' element={<ProjectCreationTable/>}></Route>
                    <Route path='customers' element={<Customers/>}></Route>
                    <Route path='profiles' element={<Profiles/>}></Route>
                    <Route path='productBacklog/:projectCode' element={<UserStories/>}></Route>
                    <Route path='typologies' element={<TypologyTable/>}></Route>

                    <Route path='users/:email' element={<div><UserUpdate/></div>}></Route>

                    <Route path='resources/projectCode/:projectCode' element={<Resources/>}></Route>
                    <Route path='resources/projectCode/:projectCode/newResource' element={<ResourceForm/>}></Route>

                    <Route path='sprints/projectCode/:projectCode' element={<div><Sprints/></div>}></Route>
                    <Route path='projects/:projectCode/activities' element={<Activities/>}></Route>
                    <Route path="*" element={<NotFound/>}> </Route>
                </Routes>
            </div>
        </Router>
    )
}

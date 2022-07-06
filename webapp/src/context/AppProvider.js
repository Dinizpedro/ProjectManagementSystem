import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import {Provider} from './AppContext';
import reducer from './Reducer';

const initialState = {

    projectLinks: {
        loading: false,
        error: null,
        data: []
    },
    projectDataList: {
        loading: false,
        error: null,
        data: []
    },
    selectedProject: {
        loading: false,
        error: null,
        data: []
    },
    addProjects: {
        loading: true,
        error: null,
        data: []
    },
    updatedUser: {
        loading: true,
        error: null,
        data: []
    },

    resourceLinks: {
        loading: false,
        error: null,
        data: []
    },
    resourceDataList: {
        loading: false,
        error: null,
        data: []
    },
    selectedResource: {
        loading: false,
        error: null,
        data: []
    },

    sprintLinks: {
        loading: false,
        error: null,
        data: []
    },
    sprintDataList: {
        loading: false,
        error: null,
        data: []
    },
    selectedSprint: {
        loading: false,
        error: null,
        data: []
    },
    addSprints: {
        loading: true,
        error: null,
        data: []
    },

    addUserStory: {
        loading: false,
        error: null,
        data: []
    },
    customers: {
        loading: true,
        error: null,
        data: []
    },
    addCustomer: {
        loading: true,
        error: null,
        data: []
    },
    profileLinks: {
        loading: false,
        error: null,
        data: []
    },
    profileDataList: {
        loading: false,
        error: null,
        data: []
    },
    addProfiles: {
        loading: true,
        error: null,
        data: []
    },
    removeProfile: {
        loading: true,
        error: null,
        data: []
    },
    userStoriesLinks: {
        loading: true,
        error: null,
        data: []
    },
    userStoriesDataList: {
        loading: true,
        error: null,
        data: []
    },
    selectedUserStory: {
        loading: false,
        error: null,
        data: []
    },
    resources: {
        loading: true,
        error: null,
        data: []
    },
    addResources: {
        loading: true,
        error: null,
        data: []
    },
    changePassword: {
        loading: true,
        error: null,
        data: []
    },
    typologiesLinks: {
        loading: false,
        error: null,
        data: []
    },
    typologyDataList: {
        loading: false,
        error: null,
        data: []
    },
    addTypology: {
        loading: true,
        error: null,
        data: []
    },

    allocatedProjects: {
        loading: true,
        error: null,
        data: []
    },
    usersDataList: {
        loading: false,
        error: null,
        data: []
    },
    usersLinks: {
        loading: false,
        error: null,
        data: []
    },
    selectedUser: {
        loading: true,
        error: null,
        data: []
    },
    addProfileToUser: {
        loading: true,
        error: null,
        data: []
    },
    activities: {
        loading: false,
        error: null,
        data: []
    },
    moveUSToSprint: {
        loading: false,
        error: null,
        data: []
    },
    userProfiles: {
        loading: false,
        error: null,
        data: []
    },
    applicationCollections:{
        loading: false,
        error: null,
        data: []
    },
    setPriority: {
        loading: true,
        error: null,
        data: []
    },
    openForm: true
};

const projectHeaders = [
    {field: 'projectCode', headerName: 'Project Code', minWidth: 100},
    {field: 'projectName', headerName: 'Name', minWidth: 100, flex: 1},
    {field: 'projectDescription', headerName: 'Description', minWidth: 100, flex: 1},
    {field: 'projectBusinessSector', headerName: 'Business Sector', minWidth: 100, flex: 1},
    {field: 'projectNumberOfPlannedSprints', headerName: 'Planned Sprints', minWidth: 100},
    {field: 'projectSprintDuration', headerName: 'Sprint Duration', minWidth: 100},
    {field: 'projectBudget', headerName: 'Budget', minWidth: 100},
    {field: 'startDate', headerName: 'Start Date', minWidth: 100},
    {field: 'endDate', headerName: 'End Date', minWidth: 100},
    {field: 'typologyDescription', headerName: 'Typology', minWidth: 100, flex: 1},
    {field: 'customerName', headerName: 'Customer', minWidth: 100, flex: 1},
    {field: 'status', headerName: 'Status', minWidth: 100, flex: 1},
];

const customerHeaders = [
    {field: 'customerName', headerName: 'Customer Name', minWidth: 400},
];

const profileHeaders = [
    {field: 'profileDescription', headerName: 'Profile Description', minWidth: 400, flex: 1}
]

const userStoryHeaders = [
    {field: 'code', headerName: 'Code', minWidth: 100},
    {field: 'effort', headerName: 'Estimated Effort', minWidth: 100},
    {field: 'priority', headerName: 'Priority', minWidth: 100},
    {field: 'description', headerName: 'Description', minWidth: 100, flex: 1},
    {field: 'status', headerName: 'Status', minWidth: 100},
    {field: 'parentUserStoryCode', headerName: 'Parent', minWidth: 100},
];

const resourceHeaders = [
    {field: 'userID', headerName: 'Email', minWidth: 100, flex: 1},
    {field: 'costPerHour', headerName: 'Cost Per Hour', minWidth: 100, flex: 1},
    {field: 'percentageOfAllocation', headerName: 'Percentage Of Allocation', minWidth: 100, flex:1},
    {field: 'role', headerName: 'Role', minWidth: 100, flex: 1},
];

const sprintHeaders = [
    {field: 'sprintNumber', headerName: 'Sprint Number', minWidth: 100},
    {field: 'startDate', headerName: 'Start Date', minWidth: 100,flex:0.5},
    {field: 'endDate', headerName: 'End Date', minWidth: 100,flex:0.5},
    {field: 'description', headerName: 'Sprint Description', minWidth: 100, flex:2},
    {field: 'status', headerName: 'Sprint Status', minWidth: 100,flex:1},
];

const usersHeaders = [
    {field: 'userName', headerName: 'Name', minWidth: 100, flex: 1},
    {field: 'email', headerName: 'Email', minWidth: 100, flex: 1},
    {field: 'userProfileList', headerName: 'Profile List', minWidth: 100},
    {field: 'activation', headerName: 'Activation', minWidth: 100},
    {field: 'function', headerName: 'Function', minWidth: 100, flex: 1},
];

const typologyHeader = [
    {field: 'description', headerName: 'Typology Description', minWidth: 100, flex: 1},
];

const allocatedProjectsHeaders = [
    {field: 'projectName', headerName: 'Project Name', minWidth: 100, flex: 1},
    {field: 'role', headerName: 'Role', minWidth: 100, flex: 1}

]

const statusOfActivitiesHeaders = [
    {field: 'typeOfActivity', headerName: 'Type Of Activity', minWidth: 100, flex: 1},
    {field: 'activityCode', headerName: 'Activity Code', minWidth: 100, flex: 1},
    {field: 'activityStatus', headerName: 'Activity Status', minWidth: 100, flex: 1}

]

const AppProvider = (props) => {

    const [state, dispatch] = useReducer(reducer, initialState);

    return (
        <Provider value={{
            state,
            dispatch,
            projectHeaders,
            customerHeaders,
            profileHeaders,
            typologyHeader,
            userStoryHeaders,
            resourceHeaders,
            sprintHeaders,
            allocatedProjectsHeaders,
            usersHeaders,
            statusOfActivitiesHeaders,
        }}>
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {children: PropTypes.node};

export default AppProvider;


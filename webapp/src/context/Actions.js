import { makeHTTPRequest } from "../services/Service";


// ------------------ APP COLLECTIONS ---------------------------

export const FETCH_APP_COLLECTIONS_STARTED = 'FETCH_APP_COLLECTIONS_STARTED';
export const FETCH_APP_COLLECTIONS_SUCCESS = 'FETCH_APP_COLLECTIONS_SUCCESS';
export const FETCH_APP_COLLECTIONS_FAILURE = 'FETCH_APP_COLLECTIONS_FAILURE';

export function fetchAppCollectionLinks(url, request, dispatch) {
    const success = (res) => dispatch(fetchAppCollectionLinksSucess(res));
    const failure = (err) => dispatch(fetchAppCollectionLinksFailure(err.message));

    makeHTTPRequest(url, request, success, failure);
}


export function fetchAppCollectionLinksStarted() {
    return {
        type: FETCH_APP_COLLECTIONS_STARTED,
    }
}

export function fetchAppCollectionLinksSucess(links) {
    return {
        type: FETCH_APP_COLLECTIONS_SUCCESS,
        payload: {
            data: links
        }

    }
}

export function fetchAppCollectionLinksFailure(message) {
    return {
        type: FETCH_APP_COLLECTIONS_FAILURE,
        payload: {
            error: message
        }
    }
}


// -------------------------- RESOURCES --------------------------

export const CREATE_RESOURCES_STARTED = 'CREATE_RESOURCES_STARTED';
export const CREATE_RESOURCES_SUCESS = 'CREATE_RESOURCES_SUCESS';
export const CREATE_RESOURCES_FAILURE = 'CREATE_RESOURCES_FAILURE';

export function createResourceStarted() {
    return {
        type: CREATE_RESOURCES_STARTED
    }
}

export function createResourceSucess(res) {
    return {
        type: CREATE_RESOURCES_SUCESS,
        payload: {
            data: res
        }
    }
}

export function createResourceFailure(message) {
    return {
        type: CREATE_RESOURCES_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addResourceToDB(url, dispatch, resource) {
    dispatch(createResourceStarted())

    const req = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(resource)
    };

    const success = (res) => dispatch(createResourceSucess(res));
    const failure = (err) => dispatch(createResourceFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}

// GET ALL RESOURCES
export const FETCH_RESOURCES_STARTED = 'FETCH_RESOURCES_STARTED';
export const FETCH_RESOURCES_SUCCESS = 'FETCH_RESOURCES_SUCCESS';
export const FETCH_RESOURCES_FAILURE = 'FETCH_RESOURCES_FAILURE';

export function fetchResources(url, request, dispatch) {

    const success = (res) => dispatch(fetchResourcesSucess(res));

    const failure = (err) => dispatch(fetchResourcesFailure(err.message));

    makeHTTPRequest(url, request, success, failure);
}


export function fetchResourcesStarted() {
    return {
        type: FETCH_RESOURCES_STARTED,
    }
}

export function fetchResourcesSucess(projects) {
    return {
        type: FETCH_RESOURCES_SUCCESS,
        payload: {
            data:
                [...projects]
        }

    }
}

export function fetchResourcesFailure(message) {
    return {
        type: FETCH_RESOURCES_FAILURE,
        payload: {
            error: message
        }
    }
}

// -------------------------- CUSTOMERS --------------------------

// GET ALL CUSTOMERS
export const FETCH_CUSTOMERS_STARTED = 'FETCH_CUSTOMERS_STARTED';
export const FETCH_CUSTOMERS_SUCCESS = 'FETCH_CUSTOMERS_SUCCESS';
export const FETCH_CUSTOMERS_FAILURE = 'FETCH_CUSTOMERS_FAILURE';

export function fetchCustomers(url, request, dispatch) {

    const success = (res) => dispatch(fetchCustomersSuccess(res));

    const failure = (err) => dispatch(fetchCustomersFailure(err.message));

    makeHTTPRequest(url, request, success, failure);
}


export function fetchCustomersStarted() {
    return {
        type: FETCH_CUSTOMERS_STARTED,
    }
}

export function fetchCustomersSuccess(projects) {
    return {
        type: FETCH_CUSTOMERS_SUCCESS,
        payload: {
            data:
                [...projects]
        }

    }
}

export function fetchCustomersFailure(message) {
    return {
        type: FETCH_CUSTOMERS_FAILURE,
        payload: {
            error: message
        }
    }
}


// ADD NEW CUSTOMER
export const ADD_CUSTOMER_STARTED = 'ADD_CUSTOMER_STARTED'
export const ADD_CUSTOMER_SUCCESS = 'ADD_CUSTOMER_SUCCESS'
export const ADD_CUSTOMER_FAILURE = 'ADD_CUSTOMER_FAILURE'

export function addCustomerStarted() {
    return {
        type: ADD_CUSTOMER_STARTED
    }
}
export function addCustomerSuccess(res) {
    return {
        type: ADD_CUSTOMER_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function addCustomerFailure(message) {
    return {
        type: ADD_CUSTOMER_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addCustomerToDB(url, dispatch, profile) {
    dispatch(addCustomerStarted())

    const req = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(profile)
    };

    const success = (res) => dispatch(addCustomerSuccess(res));
    const failure = (err) => dispatch(addCustomerFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}


// -------------------------- PROJECTS --------------------------


// ADD NEW PROJECT

export const ADD_PROJECT_STARTED = 'ADD_PROJECT_STARTED'
export const ADD_PROJECT_SUCCESS = 'ADD_PROJECT_SUCCESS'
export const ADD_PROJECT_FAILURE = 'ADD_PROJECT_FAILURE'

export function addProjectStarted() {
    return {
        type: ADD_PROJECT_STARTED
    }
}

export function addProjectSuccess(res) {
    return {
        type: ADD_PROJECT_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function addProjectFailure(message) {
    return {
        type: ADD_PROJECT_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addProjectToDB(url, dispatch, project) {
    dispatch(addProjectStarted())

    const req = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(project)
    };

    const success = (res) => dispatch(addProjectSuccess(res));
    const failure = (err) => dispatch(addProjectFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}


// UPDATE PROJECT

export const UPDATE_PROJECT_STARTED = 'UPDATE_PROJECT_STARTED'
export const UPDATE_PROJECT_SUCCESS = 'UPDATE_PROJECT_SUCCESS'
export const UPDATE_PROJECT_FAILURE = 'UPDATE_PROJECT_FAILURE'

export function updateProjectStarted() {
    return {
        type: UPDATE_PROJECT_STARTED
    }
}

export function updateProjectSuccess(res) {
    return {
        type: UPDATE_PROJECT_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function updateProjectFailure(message) {
    return {
        type: UPDATE_PROJECT_FAILURE,
        payload: {
            error: message
        }
    }
}

export function updateProjectToDB(url, dispatch, project) {
    dispatch(updateProjectStarted())

    const req = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(project),
    };
    const success = (res) => dispatch(updateProjectSuccess(res));
    const failure = (err) => dispatch(updateProjectFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}


// GET PROJECT LINKS

export const FETCH_ALL_RESOURCE_LINKS_STARTED = 'FETCH_ALL_RESOURCE_LINKS_STARTED';
export const FETCH_ALL_RESOURCE_LINKS_SUCCESS = 'FETCH_ALL_RESOURCE_LINKS_SUCCESS';
export const FETCH_ALL_RESOURCE_LINKS_FAILURE = 'FETCH_ALL_RESOURCE_LINKS_FAILURE';

export function fetchAllResourceLinks(url, request, dispatch) {
    const success = (res) => dispatch(fetchAllResourceLinksSuccess(res));
    const failure = (err) => dispatch(fetchAllResourceLinksFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchAllResourceLinksStarted() {
    return {
        type: FETCH_ALL_RESOURCE_LINKS_STARTED,
    }
}

export function fetchAllResourceLinksSuccess(resources) {
    return {
        type: FETCH_ALL_RESOURCE_LINKS_SUCCESS,
        payload: {
            data: resources
        }

    }
}

export function fetchAllResourceLinksFailure(message) {
    return {
        type: FETCH_ALL_RESOURCE_LINKS_FAILURE,
        payload: {
            error: message
        }
    }
}


// GET RESOURCES DATA

export const FETCH_RESOURCES_DATA_STARTED = 'FETCH_RESOURCES_DATA_STARTED';
export const FETCH_RESOURCES_DATA_SUCCESS = 'FETCH_RESOURCES_DATA_SUCCESS';
export const FETCH_RESOURCES_DATA_FAILURE = 'FETCH_RESOURCES_DATA_FAILURE';

export function fetchResourcesData(url, request, dispatch) {
    const success = (res) => dispatch(fetchResourcesDataSuccess(res));
    const failure = (err) => dispatch(fetchResourcesDataFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchResourcesDataStarted() {
    return {
        type: FETCH_RESOURCES_DATA_STARTED,
    }
}

export function fetchResourcesDataSuccess(resource) {
    return {
        type: FETCH_RESOURCES_DATA_SUCCESS,
        payload: {
            data: resource
        }
    }
}

export function fetchResourcesDataFailure(message) {
    return {
        type: FETCH_RESOURCES_DATA_FAILURE,
        payload: {
            error: message
        }
    }
}


// SET SELECTED RESOURCE

export const SET_SELECTED_RESOURCE= 'SET_SELECTED_RESOURCE';

export function setSelectedResource(input) {
    return {
        type: SET_SELECTED_RESOURCE,
        payload: {
            data: input
        }
    }
}


// GET PROJECT LINKS

export const FETCH_ALL_PROJECT_LINKS_STARTED = 'FETCH_ALL_PROJECT_LINKS_STARTED';
export const FETCH_ALL_PROJECT_LINKS_SUCCESS = 'FETCH_ALL_PROJECT_LINKS_SUCCESS';
export const FETCH_ALL_PROJECT_LINKS_FAILURE = 'FETCH_ALL_PROJECT_LINKS_FAILURE';

export function fetchAllProjectLinks(url, request, dispatch) {
    const success = (res) => dispatch(fetchAllProjectLinksSuccess(res));
    const failure = (err) => dispatch(fetchAllProjectLinksFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchAllProjectLinksStarted() {
    return {
        type: FETCH_ALL_PROJECT_LINKS_STARTED,
    }
}

export function fetchAllProjectLinksSuccess(projects) {
    return {
        type: FETCH_ALL_PROJECT_LINKS_SUCCESS,
        payload: {
            data: projects
        }

    }
}

export function fetchAllProjectLinksFailure(message) {
    return {
        type: FETCH_ALL_PROJECT_LINKS_FAILURE,
        payload: {
            error: message
        }
    }
}


// GET PROJECTS DATA

export const FETCH_PROJECTS_DATA_STARTED = 'FETCH_PROJECTS_DATA_STARTED';
export const FETCH_PROJECTS_DATA_SUCCESS = 'FETCH_PROJECTS_DATA_SUCCESS';
export const FETCH_PROJECTS_DATA_FAILURE = 'FETCH_PROJECTS_DATA_FAILURE';

export function fetchProjectsData(url, request, dispatch) {
    const success = (res) => dispatch(fetchProjectsDataSuccess(res));
    const failure = (err) => dispatch(fetchProjectsDataFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchProjectsDataStarted() {
    return {
        type: FETCH_PROJECTS_DATA_STARTED,
    }
}

export function fetchProjectsDataSuccess(project) {
    return {
        type: FETCH_PROJECTS_DATA_SUCCESS,
        payload: {
            data: project
        }
    }
}

export function fetchProjectsDataFailure(message) {
    return {
        type: FETCH_PROJECTS_DATA_FAILURE,
        payload: {
            error: message
        }
    }
}


// SET SELECTED PROJECT

export const SET_SELECTED_PROJECT = 'SET_SELECTED_PROJECT';

export function setSelectedProject(input) {
    return {
        type: SET_SELECTED_PROJECT,
        payload: {
            data: input
        }
    }
}

// ----------------------------------------------------------------


// GET SPRINT LINKS

export const FETCH_ALL_SPRINT_LINKS_STARTED = 'FETCH_ALL_SPRINT_LINKS_STARTED';
export const FETCH_ALL_SPRINT_LINKS_SUCCESS = 'FETCH_ALL_SPRINT_LINKS_SUCCESS';
export const FETCH_ALL_SPRINT_LINKS_FAILURE = 'FETCH_ALL_SPRINT_LINKS_FAILURE';

export function fetchAllSprintLinks(url, request, dispatch) {
    const success = (res) => dispatch(fetchAllSprintLinksSuccess(res));
    const failure = (err) => dispatch(fetchAllSprintLinksFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchAllSprintLinksStarted() {
    return {
        type: FETCH_ALL_SPRINT_LINKS_STARTED,
    }
}

export function fetchAllSprintLinksSuccess(sprints) {
    return {
        type: FETCH_ALL_SPRINT_LINKS_SUCCESS,
        payload: {
            data: sprints
        }

    }
}

export function fetchAllSprintLinksFailure(message) {
    return {
        type: FETCH_ALL_SPRINT_LINKS_FAILURE,
        payload: {
            error: message
        }
    }
}


// GET SPRINTS DATA

export const FETCH_SPRINTS_DATA_STARTED = 'FETCH_SPRINTS_DATA_STARTED';
export const FETCH_SPRINTS_DATA_SUCCESS = 'FETCH_SPRINTS_DATA_SUCCESS';
export const FETCH_SPRINTS_DATA_FAILURE = 'FETCH_SPRINTS_DATA_FAILURE';

export function fetchSprintsData(url, request, dispatch) {
    const success = (res) => dispatch(fetchSprintsDataSuccess(res));
    const failure = (err) => dispatch(fetchSprintsDataFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchSprintsDataStarted() {
    return {
        type: FETCH_SPRINTS_DATA_STARTED,
    }
}

export function fetchSprintsDataSuccess(sprint) {
    return {
        type: FETCH_SPRINTS_DATA_SUCCESS,
        payload: {
            data: sprint
        }
    }
}

export function fetchSprintsDataFailure(message) {
    return {
        type: FETCH_SPRINTS_DATA_FAILURE,
        payload: {
            error: message
        }
    }
}


// SET SELECTED SPRINT

export const SET_SELECTED_SPRINT = 'SET_SELECTED_SPRINT';

export function setSelectedSprint(input) {
    return {
        type: SET_SELECTED_SPRINT,
        payload: {
            data: input
        }
    }
}

// CREATE SPRINT

export const CREATE_SPRINTS_STARTED = "CREATE_SPRINTS_STARTED";
export const CREATE_SPRINTS_SUCCESS = "CREATE_SPRINTS_SUCCESS";
export const CREATE_SPRINTS_FAILURE = "CREATE_SPRINTS_FAILURE";

export function createSprintStarted() {
    return {
        type: CREATE_SPRINTS_STARTED
    }
}

export function createSprintSuccess(res) {
    return {
        type: CREATE_SPRINTS_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function createSprintFailure(message) {
    return {
        type: CREATE_SPRINTS_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addSprintToDB(url, dispatch, sprint) {
    dispatch(createSprintStarted())
    const req = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(sprint)
    };

    const success = (res) => dispatch(createSprintSuccess(res));
    const failure = (err) => dispatch(createSprintFailure(err.message))
    makeHTTPRequest(url, req, success, failure);
}


// ------------------ PROFILES --------------------------------

export const FETCH_PROFILE_LINKS_STARTED = 'FETCH_PROFILE_LINKS_STARTED';
export const FETCH_PROFILE_LINKS_SUCCESS = 'FETCH_PROFILE_LINKS_SUCCESS';
export const FETCH_PROFILE_LINKS_FAILURE = 'FETCH_PROFILE_LINKS_FAILURE';

export function fetchProfileLinks(url, request, dispatch) {
    const success = (res) => dispatch(fetchProfileLinksSuccess(res));
    const failure = (err) => dispatch(fetchProfileLinksFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchProfileLinksStarted() {
    return {
        type: FETCH_PROFILE_LINKS_STARTED,
    }
}

export function fetchProfileLinksSuccess(res) {
    return {
        type: FETCH_PROFILE_LINKS_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function fetchProfileLinksFailure(message) {
    return {
        type: FETCH_PROFILE_LINKS_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_PROFILE_DATA_LIST_STARTED = 'FETCH_PROFILE_DATA_LIST_STARTED';
export const FETCH_PROFILE_DATA_LIST_SUCCESS = 'FETCH_PROFILE_DATA_LIST_SUCCESS';
export const FETCH_PROFILE_DATA_LIST_FAILURE = 'FETCH_PROFILE_DATA_LIST_FAILURE';

export function fetchProfileDataList(url, request, dispatch) {
    const success = (res) => dispatch(fetchProfileDataListSuccess(res));
    const failure = (err) => dispatch(fetchProfileDataListFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchProfileDataListStarted() {
    return {
        type: FETCH_PROFILE_DATA_LIST_STARTED,
    }
}

export function fetchProfileDataListSuccess(res) {
    return {
        type: FETCH_PROFILE_DATA_LIST_SUCCESS,
        payload: {
            data: res
        }

    }
}

export function fetchProfileDataListFailure(message) {
    return {
        type: FETCH_PROFILE_DATA_LIST_FAILURE,
        payload: {
            error: message
        }
    }
}

//-------------------------------------------------------------

// ADD NEW PROFILE

export const ADD_PROFILE_STARTED = 'ADD_PROFILE_STARTED'
export const ADD_PROFILE_SUCCESS = 'ADD_PROFILE_SUCCESS'
export const ADD_PROFILE_FAILURE = 'ADD_PROFILE_FAILURE'

export function addProfileStarted() {
    return {
        type: ADD_PROFILE_STARTED
    }
}
export function addProfileSuccess(res) {
    return {
        type: ADD_PROFILE_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function addProfileFailure(message) {
    return {
        type: ADD_PROFILE_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addProfilesToUser(url, req, dispatch) {
    const success = (res) => dispatch(addProfileToUserSuccess(res));
    const failure = (err) => dispatch(addProfileToUserFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}

export const ADD_PROFILE_TO_USER_STARTED = 'ADD_PROFILE_TO_USER_STARTED'
export const ADD_PROFILE_TO_USER_SUCCESS = 'ADD_PROFILE_TO_USER_SUCCESS'
export const ADD_PROFILE_TO_USER_FAILURE = 'ADD_PROFILE_TO_USER_FAILURE'

export function addProfileToUserStarted() {
    return {
        type: ADD_PROFILE_TO_USER_STARTED
    }
}
export function addProfileToUserSuccess(res) {
    return {
        type: ADD_PROFILE_TO_USER_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function addProfileToUserFailure(message) {
    return {
        type: ADD_PROFILE_TO_USER_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addProfileToDB(url, req, dispatch) {
    dispatch(addProfileStarted())
    const success = (res) => dispatch(addProfileSuccess(res));
    const failure = (err) => dispatch(addProfileFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}


// -------------------------- USERSTORIES --------------------------



//Add US IN THE PB TO SB
export const MOVE_USERSTORY_STARTED = "MOVE_USERSTORY_STARTED";
export const MOVE_USERSTORY_SUCCESS = "MOVE_USERSTORY_SUCCESS";
export const MOVE_USERSTORY_FAILURE = "MOVE_USERSTORY_FAILURE";



export function moveUserStoryStarted() {
    return {
        type: MOVE_USERSTORY_STARTED
    }
}

export function moveUserStorySuccess(res) {
    return {
        type: MOVE_USERSTORY_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function moveUserStoryFailure(message) {
    return {
        type: MOVE_USERSTORY_FAILURE,
        payload: {
            error: message
        }
    }
}

export function moveUserStory(url, request, dispatch) {
    dispatch(moveUserStoryStarted())

    const success = (res) => dispatch(moveUserStorySuccess(res));
    const failure = (err) => dispatch(moveUserStoryFailure(err.message))
    makeHTTPRequest(url, request, success, failure);
}




// CREATE USER STORY
export const CREATE_USERSTORY_STARTED = "CREATE_USERSTORY_STARTED";
export const CREATE_USERSTORY_SUCCESS = "CREATE_USERSTORY_SUCCESS";
export const CREATE_USERSTORY_FAILURE = "CREATE_USERSTORY_FAILURE";

export function createUserStoryStarted() {
    return {
        type: CREATE_USERSTORY_STARTED
    }
}

export function createUserStorySuccess(res) {
    return {
        type: CREATE_USERSTORY_SUCCESS,
        payload: {
            data: [{ ...res }]
        }
    }
}

export function createUserStoryFailure(message) {
    return {
        type: CREATE_USERSTORY_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addUserStoryToDB(url, dispatch, userstory) {
    dispatch(createUserStoryStarted())
    const req = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userstory)
    };

    const success = (res) => dispatch(createUserStorySuccess(res));
    const failure = (err) => dispatch(createUserStoryFailure(err.message))
    makeHTTPRequest(url, req, success, failure);
}



//---------------------------------

export const FETCH_USERSTORIES_LINKS_STARTED = 'FETCH_USERSTORIES_LINKS_STARTED';
export const FETCH_USERSTORIES_LINKS_SUCCESS = 'FETCH_USERSTORIES_LINKS_SUCCESS';
export const FETCH_USERSTORIES_LINKS_FAILURE = 'FETCH_USERSTORIES_LINKS_FAILURE';
export const SET_SELECTED_USER_STORY = 'SET_SELECTED_USER_STORY'

export function fetchUserStoriesLinks(url, request, dispatch) {

    const success = (res) => dispatch(fetchUserStoriesLinksSuccess(res));
    const failure = (err) => dispatch(fetchUserStoriesLinksFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchUserStoriesLinksStarted() {
    return {
        type: FETCH_USERSTORIES_LINKS_STARTED,
    }
}

export function fetchUserStoriesLinksSuccess(userStories) {
    return {
        type: FETCH_USERSTORIES_LINKS_SUCCESS,
        payload: {
            data: userStories
        }

    }
}

export function fetchUserStoriesLinksFailure(message) {
    return {
        type: FETCH_USERSTORIES_LINKS_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_USERSTORIES_DATA_LIST_STARTED = 'FETCH_USERSTORIES_DATA_LIST_STARTED';
export const FETCH_USERSTORIES_DATA_LIST_SUCCESS = 'FETCH_USERSTORIES_DATA_LIST_SUCCESS';
export const FETCH_USERSTORIES_DATA_LIST_FAILURE = 'FETCH_USERSTORIES_DATA_LIST_FAILURE';

export function fetchUserStoriesDataList(url, request, dispatch) {

    const success = (res) => dispatch(fetchUserStoriesDataListSuccess(res));
    const failure = (err) => dispatch(fetchUserStoriesDataListFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchUserStoriesDataListStarted() {
    return {
        type: FETCH_USERSTORIES_DATA_LIST_STARTED,
    }
}

export function fetchUserStoriesDataListSuccess(userStories) {
    return {
        type: FETCH_USERSTORIES_DATA_LIST_SUCCESS,
        payload: {
            data: userStories
        }

    }
}

export function fetchUserStoriesDataListFailure(message) {
    return {
        type: FETCH_USERSTORIES_DATA_LIST_FAILURE,
        payload: {
            error: message
        }
    }
}

export function setSelectedUserStory(userStory) {
    return {
        type: SET_SELECTED_USER_STORY,
        payload: {
            data: userStory
        }

    }
}
// UPDATE USERSTORY STATUS

export const SET_STATUS_STARTED = 'SET_STATUS_STARTED'
export const SET_STATUS_SUCCESS = 'SET_STATUS_SUCCESS'
export const SET_STATUS_FAILURE = 'SET_STATUS_FAILURE'

export function setStatusStarted() {
    return {
        type: SET_STATUS_STARTED
    }
}

export function setStatusSuccess(res) {
    return {
        type: SET_STATUS_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function setStatusFailure(message) {
    return {
        type: SET_STATUS_FAILURE,
        payload: {
            error: message
        }
    }
}

export function setStatusToDB(url, dispatch, status) {
    dispatch(setStatusStarted())

    const req = {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: status
    };
    const success = (res) => dispatch(setStatusSuccess(res));
    const failure = (err) => dispatch(setStatusFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}

// UPDATE USERSTORY PRIORITY

export const SET_PRIORITY_STARTED = 'SET_PRIORITY_STARTED'
export const SET_PRIORITY_SUCCESS = 'SET_PRIORITY_SUCCESS'
export const SET_PRIORITY_FAILURE = 'SET_PRIORITY_FAILURE'

export function setPriorityStarted() {
    return {
        type: SET_PRIORITY_STARTED
    }
}

export function setPrioritySuccess(res) {
    return {
        type: SET_PRIORITY_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function setPriorityFailure(message) {
    return {
        type: SET_PRIORITY_FAILURE,
        payload: {
            error: message
        }
    }
}

export function setPriorityToDB(url, dispatch, priority) {
    dispatch(setPriorityStarted())

    const req = {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: priority
    };
    const success = (res) => dispatch(setPrioritySuccess(res));
    const failure = (err) => dispatch(setPriorityFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}
// -------------------------- TYPOLOGIES --------------------------

export const FETCH_TYPOLOGIES_LINKS_STARTED = 'FETCH_TYPOLOGIES_LINKS_STARTED';
export const FETCH_TYPOLOGIES_LINKS_SUCCESS = 'FETCH_TYPOLOGIES_LINKS_SUCCESS';
export const FETCH_TYPOLOGIES_LINKS_FAILURE = 'FETCH_TYPOLOGIES_LINKS_FAILURE';

export function fetchTypologiesLinks(url, request, dispatch) {
    const success = (res) => dispatch(fetchTypologiesLinksSuccess(res));
    const failure = (err) => dispatch(fetchTypologiesLinksFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchTypologiesLinksStarted() {
    return {
        type: FETCH_TYPOLOGIES_LINKS_STARTED,
    }
}

export function fetchTypologiesLinksSuccess(typology) {
    return {
        type: FETCH_TYPOLOGIES_LINKS_SUCCESS,
        payload: {
            data: typology

        }

    }
}

export function fetchTypologiesLinksFailure(message) {
    return {
        type: FETCH_TYPOLOGIES_LINKS_FAILURE,
        payload: {
            error: message
        }
    }
}

// FETCH DATA LIST

export const FETCH_TYPOLOGY_DATA_LIST_STARTED = 'FETCH_TYPOLOGY_DATA_LIST_STARTED';
export const FETCH_TYPOLOGY_DATA_LIST_SUCCESS = 'FETCH_TYPOLOGY_DATA_LIST_SUCCESS';
export const FETCH_TYPOLOGY_DATA_LIST_FAILURE = 'FETCH_TYPOLOGY_DATA_LIST_FAILURE';

export function fetchTypologyDataList(url, request, dispatch) {
    const success = (res) => dispatch(fetchTypologyDataListSuccess(res));
    const failure = (err) => dispatch(fetchTypologyDataListFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchTypologyDataListStarted() {
    return {
        type: FETCH_TYPOLOGY_DATA_LIST_STARTED,
    }
}

export function fetchTypologyDataListSuccess(res) {
    return {
        type: FETCH_TYPOLOGY_DATA_LIST_SUCCESS,
        payload: {
            data: res
        }

    }
}

export function fetchTypologyDataListFailure(message) {
    return {
        type: FETCH_TYPOLOGY_DATA_LIST_FAILURE,
        payload: {
            error: message
        }
    }
}

// ADD NEW TYPOLOGY

export const ADD_TYPOLOGY_STARTED = 'ADD_TYPOLOGY_STARTED';
export const ADD_TYPOLOGY_SUCCESS = 'ADD_TYPOLOGY_SUCCESS';
export const ADD_TYPOLOGY_FAILURE = 'ADD_TYPOLOGY_FAILURE';

export function addTypologyStarted() {
    return {
        type: ADD_TYPOLOGY_STARTED
    }
}
export function addTypologySuccess(res) {
    return {
        type: ADD_TYPOLOGY_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function addTypologyFailure(message) {
    return {
        type: ADD_TYPOLOGY_FAILURE,
        payload: {
            error: message
        }
    }
}

export function addTypologyToDB(url, dispatch, typology) {
    dispatch(addTypologyStarted())

    const req = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(typology)
    };

    const success = (res) => dispatch(addTypologySuccess(res));
    const failure = (err) => dispatch(addTypologyFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}

//-----------------USERS----------------

export const FETCH_USER_STARTED = 'FETCH_USER_STARTED';
export const FETCH_USER_SUCCESS = 'FETCH_USER_SUCCESS';
export const FETCH_USER_FAILURE = 'FETCH_USER_FAILURE';

export function fetchUser(url, request, dispatch) {

    const success = (res) => dispatch(fetchUserSuccess(res));
    const failure = (err) => dispatch(fetchUserFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchUserStarted() {
    return {
        type: FETCH_USER_STARTED,
    }
}

export function fetchUserSuccess(user) {
    return {
        type: FETCH_USER_SUCCESS,
        payload: {
            data: user
        }

    }
}

export function fetchUserFailure(message) {
    return {
        type: FETCH_USER_FAILURE,
        payload: {
            error: message
        }
    }
}

export const CHANGE_USER_PASSWORD_STARTED = 'CHANGE_USER_PASSWORD_STARTED';
export const CHANGE_USER_PASSWORD_SUCCESS = 'CHANGE_USER_PASSWORD_SUCCESS';
export const CHANGE_USER_PASSWORD_FAILURE = 'CHANGE_USER_PASSWORD_FAILURE';

export function changeUserPassword(url, request, dispatch) {

    const success = (res) => dispatch(changeUserPasswordSuccess(res));
    const failure = (err) => dispatch(changeUserPasswordFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function changeUserPasswordStarted() {
    return {
        type: CHANGE_USER_PASSWORD_STARTED,
    }
}

export function changeUserPasswordSuccess(user) {
    return {
        type: CHANGE_USER_PASSWORD_SUCCESS,
        payload: {
            data: user
        }
    }
}

export function changeUserPasswordFailure(message) {
    return {
        type: CHANGE_USER_PASSWORD_FAILURE,
        payload: {
            error: message
        }
    }
}

// UPDATE USER

export const UPDATE_USER_STARTED = 'UPDATE_USER_STARTED'
export const UPDATE_USER_SUCCESS = 'UPDATE_USER_SUCCESS'
export const UPDATE_USER_FAILURE = 'UPDATE_USER_FAILURE'

export function updateUserStarted() {
    return {
        type: UPDATE_USER_STARTED
    }
}

export function updateUserSuccess(res) {
    return {
        type: UPDATE_USER_SUCCESS,
        payload: {
            data: res
        }
    }
}

export function updateUserFailure(message) {
    return {
        type: UPDATE_USER_FAILURE,
        payload: {
            error: message
        }
    }
}

export function updateUserToDB(url, dispatch, user) {
    dispatch(updateUserStarted())

    const req = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user),
    };
    const success = (res) => dispatch(updateUserSuccess(res));
    const failure = (err) => dispatch(updateUserFailure(err.message));
    makeHTTPRequest(url, req, success, failure);
}

//-----------------ALLOCATED PROJECTS----------------

export const FETCH_ALLOCATED_PROJECTS_STARTED = 'FETCH_ALLOCATED_PROJECTS_STARTED'
export const FETCH_ALLOCATED_PROJECTS_SUCCESS = 'FETCH_ALLOCATED_PROJECTS_SUCCESS'
export const FETCH_ALLOCATED_PROJECTS_FAILURE = 'FETCH_ALLOCATED_PROJECTS_FAILURE'

export function fetchAllocatedProjects(url, request, dispatch) {

    const success = (res) => dispatch(fetchAllocatedProjectsSuccess(res));
    const failure = (err) => dispatch(fetchAllocatedProjectsFailure(err.message));
    makeHTTPRequest(url, request, success, failure);

}

export function fetchAllocatedProjectsStarted() {

    return {
        type: FETCH_ALLOCATED_PROJECTS_STARTED
    }
}

export function fetchAllocatedProjectsSuccess(allocatedProjects) {
    return {
        type: FETCH_ALLOCATED_PROJECTS_SUCCESS,
        payload: {
            data: [...allocatedProjects]
        }
    }
}

export function fetchAllocatedProjectsFailure(message) {
    return {
        type: FETCH_ALLOCATED_PROJECTS_FAILURE,
        payload: {
            error: message
        }
    }
}

//-------------------------------------------------------------

export const SET_SELECTED_USER = 'SET_SELECTED_USER';

export function setSelectedUser(input) {
    return {
        type: SET_SELECTED_USER,
        payload: {
            data: input
        }
    }
}

export const FETCH_USERS_LINKS_STARTED = 'FETCH_USERS_LINKS_STARTED';
export const FETCH_USERS_LINKS_SUCCESS = 'FETCH_USERS_LINKS_SUCCESS';
export const FETCH_USERS_LINKS_FAILURE = 'FETCH_USERS_LINKS_FAILURE';

export function fetchUsersLinks(url, req, dispatch) {

    const success = (res) => dispatch(fetchUsersLinksSuccess(res));

    const failure = (message) => dispatch(fetchUsersLinksFailure(message));

    makeHTTPRequest(url, req, success, failure);
}

export function fetchUsersLinksStarted() {
    return {
        type: FETCH_USERS_LINKS_STARTED,
    }
}

export function fetchUsersLinksSuccess(users) {
    return {
        type: FETCH_USERS_LINKS_SUCCESS,
        payload: {
            data: users
        }
    }
}

export function fetchUsersLinksFailure(message) {
    return {
        type: FETCH_USERS_LINKS_FAILURE,
        payload: {
            error: message
        }
    }
}


export const FETCH_USERS_STARTED = 'FETCH_USERS_STARTED';
export const FETCH_USERS_SUCCESS = 'FETCH_USERS_SUCCESS';
export const FETCH_USERS_FAILURE = 'FETCH_USERS_FAILURE';

export function fetchUsers(url, req, dispatch) {

    const success = (res) => dispatch(fetchUsersSuccess(res));

    const failure = (message) => dispatch(fetchUsersFailure(message));

    makeHTTPRequest(url, req, success, failure);
}

export function fetchUsersStarted() {
    return {
        type: FETCH_USERS_STARTED,
    }
}

export function fetchUsersSuccess(users) {
    return {
        type: FETCH_USERS_SUCCESS,
        payload: {
            data: users
        }
    }
}

export function fetchUsersFailure(message) {
    return {
        type: FETCH_USERS_FAILURE,
        payload: {
            error: message
        }
    }
}


//----------------ACTIVITIES------------------


export const FETCH_ACTIVITIES_STARTED = 'FETCH_ACTIVITIES_STARTED';
export const FETCH_ACTIVITIES_SUCCESS = 'FETCH_ACTIVITIES_SUCCESS';
export const FETCH_ACTIVITIES_FAILURE = 'FETCH_ACTIVITIES_FAILURE';

export function fetchActivities(url, request, dispatch) {
    const success = (res) => dispatch(fetchActivitiesSuccess(res));
    const failure = (err) => dispatch(fetchActivitiesFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchActivitiesStarted() {
    return {
        type: FETCH_ACTIVITIES_STARTED,
    }
}

export function fetchActivitiesSuccess(activities) {
    return {
        type: FETCH_ACTIVITIES_SUCCESS,
        payload: {
            data: activities
        }

    }
}

export function fetchActivitiesFailure(message) {
    return {
        type: FETCH_ACTIVITIES_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_USER_PROFILES_STARTED = 'FETCH_USER_PROFILES_STARTED';
export const FETCH_USER_PROFILES_SUCCESS = 'FETCH_USER_PROFILES_SUCCESS';
export const FETCH_USER_PROFILES_FAILURE = 'FETCH_USER_PROFILES_FAILURE';

export function fetchUserProfiles(url, request, dispatch) {
    const success = (res) => dispatch(fetchUserProfilesSuccess(res));
    const failure = (err) => dispatch(fetchUserProfilesFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchUserProfilesStarted() {
    return {
        type: FETCH_USER_PROFILES_STARTED,
    }
}

export function fetchUserProfilesSuccess(activities) {
    return {
        type: FETCH_USER_PROFILES_SUCCESS,
        payload: {
            data: activities
        }

    }
}

export function fetchUserProfilesFailure(message) {
    return {
        type: FETCH_USER_PROFILES_FAILURE,
        payload: {
            error: message
        }
    }
}



// ----------- MODAL PAGE CONTROL ---------------

export const SET_OPEN_FORM = 'SET_OPEN_FORM';

export function setOpenForm(boolean) {
    return {
        type: SET_OPEN_FORM,
        payload: boolean
    }
}
import {
    ADD_CUSTOMER_FAILURE,
    ADD_CUSTOMER_STARTED,
    ADD_CUSTOMER_SUCCESS,
    ADD_PROFILE_FAILURE,
    ADD_PROFILE_STARTED,
    ADD_PROFILE_SUCCESS,
    ADD_PROFILE_TO_USER_FAILURE,
    ADD_PROFILE_TO_USER_STARTED,
    ADD_PROFILE_TO_USER_SUCCESS,
    ADD_PROJECT_FAILURE,
    ADD_PROJECT_STARTED,
    ADD_PROJECT_SUCCESS,
    ADD_TYPOLOGY_FAILURE,
    ADD_TYPOLOGY_STARTED,
    ADD_TYPOLOGY_SUCCESS,
    CHANGE_USER_PASSWORD_FAILURE,
    CHANGE_USER_PASSWORD_STARTED,
    CHANGE_USER_PASSWORD_SUCCESS,
    CREATE_RESOURCES_FAILURE,
    CREATE_RESOURCES_STARTED,
    CREATE_RESOURCES_SUCESS,
    CREATE_SPRINTS_FAILURE,
    CREATE_SPRINTS_STARTED,
    CREATE_SPRINTS_SUCCESS,
    CREATE_USERSTORY_FAILURE,
    CREATE_USERSTORY_STARTED,
    CREATE_USERSTORY_SUCCESS,
    FETCH_ACTIVITIES_FAILURE,
    FETCH_ACTIVITIES_STARTED,
    FETCH_ACTIVITIES_SUCCESS,
    FETCH_ALL_PROJECT_LINKS_FAILURE,
    FETCH_ALL_PROJECT_LINKS_STARTED,
    FETCH_ALL_PROJECT_LINKS_SUCCESS,
    FETCH_ALL_RESOURCE_LINKS_FAILURE,
    FETCH_ALL_RESOURCE_LINKS_STARTED,
    FETCH_ALL_RESOURCE_LINKS_SUCCESS,
    FETCH_ALL_SPRINT_LINKS_FAILURE,
    FETCH_ALL_SPRINT_LINKS_STARTED,
    FETCH_ALL_SPRINT_LINKS_SUCCESS,
    FETCH_ALLOCATED_PROJECTS_FAILURE,
    FETCH_ALLOCATED_PROJECTS_STARTED,
    FETCH_ALLOCATED_PROJECTS_SUCCESS,
    FETCH_APP_COLLECTIONS_FAILURE,
    FETCH_APP_COLLECTIONS_STARTED,
    FETCH_APP_COLLECTIONS_SUCCESS,
    FETCH_CUSTOMERS_FAILURE,
    FETCH_CUSTOMERS_STARTED,
    FETCH_CUSTOMERS_SUCCESS,
    FETCH_PROFILE_DATA_LIST_FAILURE,
    FETCH_PROFILE_DATA_LIST_STARTED,
    FETCH_PROFILE_DATA_LIST_SUCCESS,
    FETCH_PROFILE_LINKS_FAILURE,
    FETCH_PROFILE_LINKS_STARTED,
    FETCH_PROFILE_LINKS_SUCCESS,
    FETCH_PROJECTS_DATA_FAILURE,
    FETCH_PROJECTS_DATA_STARTED,
    FETCH_PROJECTS_DATA_SUCCESS,
    FETCH_RESOURCES_DATA_FAILURE,
    FETCH_RESOURCES_DATA_STARTED,
    FETCH_RESOURCES_DATA_SUCCESS,
    FETCH_SPRINTS_DATA_FAILURE,
    FETCH_SPRINTS_DATA_STARTED,
    FETCH_SPRINTS_DATA_SUCCESS,
    FETCH_TYPOLOGIES_LINKS_FAILURE,
    FETCH_TYPOLOGIES_LINKS_STARTED,
    FETCH_TYPOLOGIES_LINKS_SUCCESS,
    FETCH_TYPOLOGY_DATA_LIST_FAILURE,
    FETCH_TYPOLOGY_DATA_LIST_STARTED,
    FETCH_TYPOLOGY_DATA_LIST_SUCCESS,
    FETCH_USER_FAILURE,
    FETCH_USER_PROFILES_FAILURE,
    FETCH_USER_PROFILES_STARTED,
    FETCH_USER_PROFILES_SUCCESS,
    FETCH_USER_STARTED,
    FETCH_USER_SUCCESS,
    FETCH_USERS_FAILURE,
    FETCH_USERS_LINKS_FAILURE,
    FETCH_USERS_LINKS_STARTED,
    FETCH_USERS_LINKS_SUCCESS,
    FETCH_USERS_STARTED,
    FETCH_USERS_SUCCESS,
    FETCH_USERSTORIES_DATA_LIST_FAILURE,
    FETCH_USERSTORIES_DATA_LIST_STARTED,
    FETCH_USERSTORIES_DATA_LIST_SUCCESS,
    FETCH_USERSTORIES_LINKS_FAILURE,
    FETCH_USERSTORIES_LINKS_STARTED,
    FETCH_USERSTORIES_LINKS_SUCCESS,
    MOVE_USERSTORY_FAILURE,
    MOVE_USERSTORY_STARTED,
    MOVE_USERSTORY_SUCCESS,
    SET_OPEN_FORM, SET_PRIORITY_FAILURE, SET_PRIORITY_STARTED, SET_PRIORITY_SUCCESS,
    SET_SELECTED_PROJECT,
    SET_SELECTED_SPRINT,
    SET_SELECTED_USER,
    SET_SELECTED_USER_STORY,
    UPDATE_PROJECT_FAILURE,
    UPDATE_PROJECT_STARTED,
    UPDATE_PROJECT_SUCCESS,
    UPDATE_USER_FAILURE,
    UPDATE_USER_STARTED,
    UPDATE_USER_SUCCESS,
    SET_STATUS_STARTED,
    SET_STATUS_SUCCESS,
    SET_STATUS_FAILURE,
} from "./Actions"

const reducer = (state, action) => {

    let index
    const changeIndex = (providerVariable, identifier) => {
        return providerVariable.findIndex((profile) => profile[identifier] == action.payload.data[identifier])
    }

    switch (action.type) {

        // -------- FETCH APP COLLECTION LINKS -----------

        case FETCH_APP_COLLECTIONS_STARTED:
            return {
                ...state,
                applicationCollections: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_APP_COLLECTIONS_SUCCESS:
            return {
                ...state,
                applicationCollections: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_APP_COLLECTIONS_FAILURE:
            return {
                ...state,
                applicationCollections: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }


        // ------------- GET ALL CUSTOMERS -------------

        case FETCH_CUSTOMERS_STARTED:
            return {
                ...state,
                customers: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_CUSTOMERS_SUCCESS:
            return {
                ...state,
                customers: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_CUSTOMERS_FAILURE:
            return {
                ...state,
                customers: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- ADD NEW CUSTOMER -------------
        case ADD_CUSTOMER_STARTED:
            return {
                ...state,
                addCustomer: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case ADD_CUSTOMER_SUCCESS:
            return {
                ...state,
                addCustomer: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case ADD_CUSTOMER_FAILURE:
            return {
                ...state,
                addCustomer: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- ADD NEW PROJECT -------------

        case ADD_PROJECT_STARTED:
            return {
                ...state,
                addProjects: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case ADD_PROJECT_SUCCESS:
            return {
                ...state,
                addProjects: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case ADD_PROJECT_FAILURE:
            return {
                ...state,
                addProjects: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- GET ALL PROJECTS LINKS-------------

        case FETCH_ALL_PROJECT_LINKS_STARTED:
            return {
                ...state,
                projectLinks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_ALL_PROJECT_LINKS_SUCCESS:
            return {
                ...state,
                projectLinks: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_ALL_PROJECT_LINKS_FAILURE:
            return {
                ...state,
                projectLinks: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- GET PROJECT DATA ARRAY-------------

        case FETCH_PROJECTS_DATA_STARTED:
            return {
                ...state,
                projectDataList: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PROJECTS_DATA_SUCCESS:
            return {
                ...state,
                projectDataList: {
                    loading: false,
                    error: null,
                    data: [...state.projectDataList.data, action.payload.data]
                }
            }
        case FETCH_PROJECTS_DATA_FAILURE:
            return {
                ...state,
                projectDataList: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- SET ONE PROJECT DATA -------------

        case SET_SELECTED_PROJECT:
            return {
                ...state,
                selectedProject: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }


        // ------------- ADD NEW SPRINT -------------

        case CREATE_SPRINTS_STARTED:
            return {
                ...state,
                addSprints: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case CREATE_SPRINTS_SUCCESS:
            return {
                ...state,
                addSprints: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case CREATE_SPRINTS_FAILURE:
            return {
                ...state,
                addSprints: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- GET ALL SPRINT LINKS-------------

        case FETCH_ALL_SPRINT_LINKS_STARTED:
            return {
                ...state,
                sprintLinks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_ALL_SPRINT_LINKS_SUCCESS:
            return {
                ...state,
                sprintLinks: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_ALL_SPRINT_LINKS_FAILURE:
            return {
                ...state,
                sprintLinks: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- GET SPRINT DATA ARRAY-------------

        case FETCH_SPRINTS_DATA_STARTED:
            return {
                ...state,
                sprintDataList: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_SPRINTS_DATA_SUCCESS:
            return {
                ...state,
                sprintDataList: {
                    loading: false,
                    error: null,
                    data: [...state.sprintDataList.data, action.payload.data]
                }
            }
        case FETCH_SPRINTS_DATA_FAILURE:
            return {
                ...state,
                sprintDataList: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- SET ONE SPRINT DATA -------------

        case SET_SELECTED_SPRINT:
            return {
                ...state,
                selectedSprint: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        // ---------------PROFILES------------------------------
        case FETCH_PROFILE_LINKS_STARTED:
            return {
                ...state,
                profileLinks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PROFILE_LINKS_SUCCESS:
            return {
                ...state,
                profileLinks: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_PROFILE_LINKS_FAILURE:
            return {
                ...state,
                profileLinks: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case FETCH_PROFILE_DATA_LIST_STARTED:
            return {
                ...state,
                profileDataList: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PROFILE_DATA_LIST_SUCCESS:
            return {
                ...state,
                profileDataList: {
                    loading: false,
                    error: null,
                    data: [...state.profileDataList.data, action.payload.data]
                }
            }

        case FETCH_PROFILE_DATA_LIST_FAILURE:
            return {
                ...state,
                profileDataList: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }


        //----------------------------
        case CREATE_USERSTORY_STARTED:
            return {
                ...state,
                addUserStory: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case CREATE_USERSTORY_SUCCESS:
            return {
                ...state,
                addUserStory: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case CREATE_USERSTORY_FAILURE:
            return {
                ...state,
                addUserStory: {
                    loading: false,
                    error: action.payload.data,
                    data: []
                }
            }

        // ---------------------------------------------
        case ADD_PROFILE_STARTED:
            return {
                ...state,
                addProfiles: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case ADD_PROFILE_SUCCESS:
            return {
                ...state,
                addProfiles: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case ADD_PROFILE_FAILURE:
            return {
                ...state,
                addProfiles: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ---------------------------------------------
        case ADD_PROFILE_TO_USER_STARTED:
            return {
                ...state,
                addProfileToUser: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case ADD_PROFILE_TO_USER_SUCCESS:
            return {
                ...state,
                addProfileToUser: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case ADD_PROFILE_TO_USER_FAILURE:
            return {
                ...state,
                addProfileToUser: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- UPDATE NEW PROJECT -------------

        case UPDATE_PROJECT_STARTED:
            return {
                ...state,
                addProjects: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case UPDATE_PROJECT_SUCCESS:
            return {
                ...state,
                addProjects: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case UPDATE_PROJECT_FAILURE:
            return {
                ...state,
                addProjects: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }


        //------------------------------------------

        case FETCH_USERSTORIES_LINKS_STARTED:
            return {
                ...state,
                userStoriesLinks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_USERSTORIES_LINKS_SUCCESS:
            return {
                ...state,
                userStoriesLinks: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_USERSTORIES_LINKS_FAILURE:
            return {
                ...state,
                userStoriesLinks: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        case FETCH_USERSTORIES_DATA_LIST_STARTED:
            return {
                ...state,
                userStoriesDataList: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_USERSTORIES_DATA_LIST_SUCCESS:
            index = changeIndex(state.userStoriesDataList.data, 'code')
            if (index === -1) {
                return {
                    ...state,
                    userStoriesDataList: {
                        loading: false,
                        error: null,
                        data: [...state.userStoriesDataList.data, action.payload.data]
                    }
                }
            } else {
                return {...state}
            }
        case FETCH_USERSTORIES_DATA_LIST_FAILURE:
            return {
                ...state,
                userStoriesDataList: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case MOVE_USERSTORY_STARTED:
            return {
                ...state,
                moveUSToSprint: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case MOVE_USERSTORY_SUCCESS:
            return {
                ...state,
                moveUSToSprint: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case MOVE_USERSTORY_FAILURE:
            return {
                ...state,
                moveUSToSprint: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case SET_SELECTED_USER_STORY:
            return {
                ...state,
                selectedUserStory: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }


        case
        CREATE_RESOURCES_STARTED:
            return {
                ...state,
                resources: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case
        CREATE_RESOURCES_SUCESS:
            return {
                ...state,
                resources: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case
        CREATE_RESOURCES_FAILURE:
            return {
                ...state,
                resources: {
                    loading: false,
                    error: action.payload.data,
                    data: []
                }


            }
        // ------------- GET ALL RESOURCES LINKS-------------

        case FETCH_ALL_RESOURCE_LINKS_STARTED:
            return {
                ...state,
                resourceLinks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_ALL_RESOURCE_LINKS_SUCCESS:
            return {
                ...state,
                resourceLinks: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_ALL_RESOURCE_LINKS_FAILURE:
            return {
                ...state,
                resourceLinks: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- GET RESOURCE DATA ARRAY-------------

        case FETCH_RESOURCES_DATA_STARTED:
            return {
                ...state,
                resourceDataList: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_RESOURCES_DATA_SUCCESS:
            return {
                ...state,
                resourceDataList: {
                    loading: false,
                    error: null,
                    data: [...state.resourceDataList.data, action.payload.data]
                }
            }
        case FETCH_RESOURCES_DATA_FAILURE:
            return {
                ...state,
                resourceDataList: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }


        // ------------- GET/ADD TYPOLOGIES -------------

        case FETCH_TYPOLOGIES_LINKS_STARTED:
            return {
                ...state,
                typologiesLinks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_TYPOLOGIES_LINKS_SUCCESS:
            return {
                ...state,
                typologiesLinks: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_TYPOLOGIES_LINKS_FAILURE:
            return {
                ...state,
                typologiesLinks: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case FETCH_TYPOLOGY_DATA_LIST_STARTED:
            return {
                ...state,
                typologyDataList: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_TYPOLOGY_DATA_LIST_SUCCESS:
            return {
                ...state,
                typologyDataList: {
                    loading: false,
                    error: null,
                    data: [...state.typologyDataList.data, action.payload.data]
                }
            }

        case FETCH_TYPOLOGY_DATA_LIST_FAILURE:
            return {
                ...state,
                typologyDataList: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case ADD_TYPOLOGY_STARTED:
            return {
                ...state,
                addTypology: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case ADD_TYPOLOGY_SUCCESS:
            return {
                ...state,
                addTypology: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case ADD_TYPOLOGY_FAILURE:
            return {
                ...state,
                addTypology: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        //------------USER--------------------


        case CHANGE_USER_PASSWORD_STARTED:
            return {
                ...state,
                changePassword: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case CHANGE_USER_PASSWORD_SUCCESS:
            return {
                ...state,
                changePassword: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case CHANGE_USER_PASSWORD_FAILURE:
            return {
                ...state,
                changePassword: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- UPDATE USER -------------

        case UPDATE_USER_STARTED:
            return {
                ...state,
                updatedUser: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case UPDATE_USER_SUCCESS:
            return {
                ...state,
                updatedUser: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case UPDATE_USER_FAILURE:
            return {
                ...state,
                updatedUser: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        //------------GETALLOCATEDPROJECTS--------------------

        case FETCH_ALLOCATED_PROJECTS_STARTED:
            return {
                ...state,
                allocatedProjects: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_ALLOCATED_PROJECTS_SUCCESS:
            return {
                ...state,
                allocatedProjects: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_ALLOCATED_PROJECTS_FAILURE:
            return {
                ...state,
                allocatedProjects: {
                    loading: false,
                    error: action.payload.data,
                    data: []
                }
            }

        //------------GETUSERS--------------------

        case FETCH_USERS_LINKS_STARTED:
            return {
                ...state,
                usersLinks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_USERS_LINKS_SUCCESS:
            return {
                ...state,
                usersLinks: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_USERS_LINKS_FAILURE:
            return {
                ...state,
                usersLinks: {
                    loading: false,
                    error: action.payload.data,
                    data: []
                }
            }

        case FETCH_USERS_STARTED:
            return {
                ...state,
                usersDataList: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_USERS_SUCCESS:
            return {
                ...state,
                usersDataList: {
                    loading: false,
                    error: null,
                    data: [...state.usersDataList.data, action.payload.data]
                }
            }

        case FETCH_USERS_FAILURE:
            return {
                ...state,
                usersDataList: {
                    loading: false,
                    error: action.payload.data,
                    data: []
                }
            }

        //------------GET ONE USER--------------------

        case FETCH_USER_STARTED:
            return {
                ...state,
                selectedUser: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_USER_SUCCESS:
            return {
                ...state,
                selectedUser: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_USER_FAILURE:
            return {
                ...state,
                selectedUser: {
                    loading: false,
                    error: action.payload.data,
                    data: []
                }
            }

        case SET_SELECTED_USER:
            return {
                ...state,
                selectedUser: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        //--------GET_ACTIVITIES------

        case FETCH_ACTIVITIES_STARTED:
            return {
                ...state,
                activities: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_ACTIVITIES_SUCCESS:
            return {
                ...state,
                activities: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_ACTIVITIES_FAILURE:
            return {
                ...state,
                activities: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        //--------GET USER PROFILES------

        case FETCH_USER_PROFILES_STARTED:
            return {
                ...state,
                userProfiles: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_USER_PROFILES_SUCCESS:
            return {
                ...state,
                userProfiles: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_USER_PROFILES_FAILURE:
            return {
                ...state,
                userProfiles: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        // ------------- SET PRIORITY -------------
        case SET_PRIORITY_STARTED:
            return {
                ...state,
                setPriority: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case SET_PRIORITY_SUCCESS:
            return {
                ...state,
                setPriority: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case SET_PRIORITY_FAILURE:
            return {
                ...state,
                setPriority: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        // ------------- SET STATUS -------------
        case SET_STATUS_STARTED:
            return {
                ...state,
                setStatus: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case SET_STATUS_SUCCESS:
            return {
                ...state,
                setStatus: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case SET_STATUS_FAILURE:
            return {
                ...state,
                setStatus: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        // ----- MODAL PAGE CONTROL ---------
        case SET_OPEN_FORM:
            return {
                ...state,
                openForm: action.payload
            }

        default:
            return state;

    }
}


export default reducer;

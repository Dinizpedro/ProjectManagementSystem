import {DataGrid} from '@mui/x-data-grid'
import {useNavigate} from "react-router-dom";
import AppContext from "../context/AppContext";
import {useContext} from "react";


const DataTable = ({tableData, headers, id, url, onClickFunction, onClickNavigate,
                       sortingField}) => {

    const navigate = useNavigate();
    const {dispatch} = useContext(AppContext);

    const datagridSx = {
        '& div[data-rowIndex]': {
            fontSize: 14
        },
        "& .MuiDataGrid-columnHeaders": {
            backgroundColor: "#ebebf9",
            fontWeight: 'bold',
            fontSize: 15
        },
        '.MuiDataGrid-columnSeparator': {
            display: 'none',
        },
        '&.MuiDataGrid-root': {
            border: 'none',
        },
        "& .MuiDataGrid-renderingZone": {
            "& .MuiDataGrid-row": {
                "&:nth-child(2n)": {
                    backgroundColor: "rgba(235, 235, 235, .7)"
                }
            }
        },
        "& .wrapHeader .MuiDataGrid-colCellTitle": {
            overflow: "hidden",
            lineHeight: "20px",
            whiteSpace: "normal"
        }
    };

    const openLink = (params) => {
        if (onClickFunction != null) {
            dispatch(onClickFunction(params.row))
        }
        if (onClickNavigate === true) {
            navigate(url + '/' + params.id, "_self")
        }
    }

    let sortingState
    if (sortingField != null) {
        sortingState = {
            sorting: {
                sortModel: [{ field: sortingField, sort: 'asc' }],
            },
        }

    }

    return (
        <DataGrid
            initialState={sortingState}
            rows={tableData}
            getRowId={row => row[id]}
            columns={headers}
            rowHeight={48}
            pageSize={10}
            sx={datagridSx}
            onRowClick={openLink}
            autoHeight={true}
            autoPageSize={true}
            align={"left"}
            headerAlign={"center"}
        />
    );
};

export default DataTable

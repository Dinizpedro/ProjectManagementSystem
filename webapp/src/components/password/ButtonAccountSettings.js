import * as React from 'react';
import { Button } from "@material-ui/core";
import { Box } from "@mui/material";
import ModalPassword from './ModalPassword';

const ButtonAccountSettings = ({ prop1 }) => {

    const clickeiNoBotao = () => {
        return (
            <ModalPassword />
        );
    };

    return (
        <Box key="keyButtonAccountSettingsBox">
            <Button
                data-button-key="buttonAttribute"
                variant="contained"
                onClick={clickeiNoBotao}
            >
                Settings
            </Button>
        </Box>
    );
};

export default ButtonAccountSettings;
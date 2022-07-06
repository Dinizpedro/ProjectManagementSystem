import Stack from "@mui/material/Stack";
import Avatar from "@mui/material/Avatar";
import * as React from "react";

function ImageAvatars() {
    return (
            <Stack direction="row" spacing={2} justifyContent = "center" alignItems="center" marginTop={"100px"}>
            <Avatar alt="Daniel" src={require('../assets/Avatars/Daniel.jpg')}sx={{ width: 56, height: 56 }} />
            <Avatar alt="Diogo" src={require('../assets/Avatars/Diogo.jpg')} sx={{ width: 56, height: 56 }} />
            <Avatar alt="Gi" src={require('../assets/Avatars/Gi.jpg')} sx={{ width: 56, height: 56 }} />
            <Avatar alt="Marcio" src={require('../assets/Avatars/Marcio.jpg')} sx={{ width: 56, height: 56 }} />
            <Avatar alt="Mauricio" src={require('../assets/Avatars/Mauricio.jpg')} sx={{ width: 56, height: 56 }} />
            <Avatar alt="Patrick" src={require('../assets/Avatars/Patrick.jpg')} sx={{ width: 56, height: 56 }} />
            <Avatar alt="Pedro" src={require('../assets/Avatars/Pedro.jpg')} sx={{ width: 56, height: 56 }} />
            <Avatar alt="Tiago" src={require('../assets/Avatars/Tiago.jpg')} sx={{ width: 56, height: 56 }}/>
            <Avatar alt="Tania" src={require('../assets/Avatars/Tânia.jpg')} sx={{ width: 56, height: 56 }} />
            <Avatar alt="Valter" src={require('../assets/Avatars/Valter.jpg')} sx={{ width: 56, height: 56 }} />
            </Stack>
    );
}

export default ImageAvatars;
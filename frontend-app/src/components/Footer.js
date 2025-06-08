import React from 'react';
import {Box} from "@mui/material";

export function Footer() {
    return (
        <Box sx={{position: "fixed", bottom: "0", left: "0", textAlign: "center", display: "inline-flex", alignContent: "center", justifyContent: "center", alignItems: "center", width: "100vw", backgroundColor: "#1976d2", height: "5vh", color: "white", fontSize: "18px"}}>
            Politechnika Wrocławska All Rights Reserved 2025
        </Box>
    );
}
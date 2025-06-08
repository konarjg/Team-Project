import React from "react";
import { TopNav } from '../components/TopNav';
import { Footer } from '../components/Footer';
import { Box } from '@mui/material';
import { ForgotPasswordForm } from '../components/ForgotPasswordForm';

export function ForgotPassword() {
    return (
        <main>
            <TopNav/>
            <Box sx={{display: "flex", flex: "1", maxWidth: "98vw"}}>
                <ForgotPasswordForm></ForgotPasswordForm>
            </Box>
            <Footer/>
        </main>
    );
}
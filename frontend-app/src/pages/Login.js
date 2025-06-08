import React from "react";
import { TopNav } from '../components/TopNav';
import { Footer } from '../components/Footer';
import { Box } from '@mui/material';
import { LoginForm } from '../components/LoginForm';

export function Login() {
    return (
        <main>
            <TopNav/>
            <Box sx={{display: "flex", flex: "1", maxWidth: "98vw"}}>
                <LoginForm></LoginForm>
            </Box>
            <Footer/>
        </main>
    );
}
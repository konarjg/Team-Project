import React from "react";
import { TopNav } from '../components/TopNav';
import { Footer } from '../components/Footer';
import { CategoryBar } from "../components/CategoryBar";
import { AccountPanel } from "../components/AccountPanel";
import { Box, Typography, Divider } from '@mui/material';

export function Account() {
    return (
        <main>
            <TopNav/>
            <CategoryBar></CategoryBar>
            <Box sx={{ display: "flex", flexDirection: "column", flex: 1, maxWidth: "98vw", p: 4 }}>
                <AccountPanel></AccountPanel>
            </Box>
            <Footer/>
        </main>
    );
}

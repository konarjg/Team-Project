import React from "react";
import { TopNav } from '../components/TopNav';
import { Footer } from '../components/Footer';
import { CategoryBar } from "../components/CategoryBar";
import { CheckoutForm } from "../components/CheckoutForm";
import { Box, Typography, Divider } from '@mui/material';

export function Checkout() {
    return (
        <main>
            <TopNav/>
            <CategoryBar></CategoryBar>
            <Box sx={{ display: "flex", flexDirection: "column", flex: 1, maxWidth: "98vw", p: 4 }}>
                <CheckoutForm></CheckoutForm>
            </Box>
            <Footer/>
        </main>
    );
}

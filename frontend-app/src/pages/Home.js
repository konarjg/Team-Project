import React from "react";
import { TopNav } from '../components/TopNav';
import { Footer } from '../components/Footer';
import { CategoryBar } from "../components/CategoryBar";
import { Box, Typography, Divider } from '@mui/material';

export function Home() {
    return (
        <main>
            <TopNav/>
            <CategoryBar></CategoryBar>
            <Box sx={{ display: "flex", flexDirection: "column", flex: 1, maxWidth: "98vw", p: 4 }}>
                
                <Typography variant="h4" align="center" gutterBottom>
                    Welcome to Buyer Zone – Your Ultimate Shopping Destination!
                </Typography>

                <Typography variant="body1">
                    At <strong>Buyer Zone</strong>, we bring you the best selection of products, unbeatable deals, and a seamless shopping experience—all in one place.
                    Whether you're looking for the latest <strong>tech gadgets, fashion trends, home essentials,</strong> or <strong>exclusive discounts</strong>, we've got you covered!
                </Typography>

                <Divider sx={{ my: 2 }} />

                <Typography variant="h5">🚀 Why Shop with Us?</Typography>
                <Typography component="ul">
                    <li><strong>Endless Variety</strong> – Thousands of products from trusted brands across multiple categories.</li>
                    <li><strong>Unbeatable Prices</strong> – Daily deals, flash sales, and exclusive discounts tailored for smart shoppers.</li>
                    <li><strong>Fast & Reliable Shipping</strong> – Get your orders delivered quickly and securely.</li>
                </Typography>

                <Divider sx={{ my: 2 }} />

                <Typography variant="h5">🔍 Shop by Categories</Typography>
                <Typography component="ul">
                    <li>💻 <strong>Electronics & Gadgets</strong> – Smartphones, laptops, gaming accessories & more.</li>
                    <li>👗 <strong>Fashion & Style</strong> – Trendy outfits, footwear, and accessories for every occasion.</li>
                    <li>🏡 <strong>Home & Living</strong> – Furniture, kitchenware, and home essentials to elevate your space.</li>
                </Typography>

                <Divider sx={{ my: 2 }} />

                <Typography variant="h6" align="center" sx={{ mt: 4 }}>
                    Ready to shop smarter and save bigger? Explore <strong>Buyer Zone</strong> now and grab the best deals before they disappear!
                </Typography>
                
            </Box>
            <Footer/>
        </main>
    );
}

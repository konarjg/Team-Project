import { TopNav } from "../components/TopNav";
import { Footer } from "../components/Footer";
import { CategoryBar } from "../components/CategoryBar";
import { ProductCard } from "../components/ProductCard";
import { Box, Grid } from "@mui/material";
import { useParams } from "react-router-dom";
import { useState } from "react";

export function CategoryPage() {
    const categoryName = useParams();
    
    const [products, setProducts] = useState([
        { productId: 1, name: "Test", image: "http://localhost:3000/logo192.png", stock: 0 },
        { productId: 2, name: "Sample Product", image: "http://localhost:3000/logo192.png", stock: 10 },
        { productId: 3, name: "Another Item", image: "http://localhost:3000/logo192.png", stock: 7 },
    ]);

    return (
        <main>
            <TopNav />
            <CategoryBar />
            <Box sx={{ flex: 1, maxWidth: "98vw", p: 4 }}>
                <Grid container spacing={3}>
                    {products.map((p) => (
                        <Grid item xs={12} sm={6} md={4} lg={3} key={p.productId}>
                            <ProductCard product={p} />
                        </Grid>
                    ))}
                </Grid>
            </Box>
            <Footer />
        </main>
    );
}

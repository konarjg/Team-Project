import { TopNav } from "../components/TopNav";
import { Footer } from "../components/Footer";
import { CategoryBar } from "../components/CategoryBar";
import { ProductCard } from "../components/ProductCard";
import { Box, Grid } from "@mui/material";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";

export function CategoryPage() {
    const categoryName = useParams();
    const [category, setCategory] = useState(null);
    const [products, setProducts] = useState([]);

    useEffect(() => {
        const storedCategory = JSON.parse(sessionStorage.getItem("category")) || {name: "Electronics", icon: "💻", products: []};
        setCategory(storedCategory);
        setProducts(storedCategory.products);
    }, [categoryName]);

    return (
        <main>
            <TopNav />
            <CategoryBar />
            <Box sx={{ flex: 1, maxWidth: "98vw", p: 4 }}>
                <Grid container spacing={3}>
                    {products.map((p) => (
                        <Grid key={p.productId} item xs={12} sm={6} md={4} lg={3}>
                            <ProductCard product={p} />
                        </Grid>
                    ))}
                </Grid>
            </Box>
            <Footer />
        </main>
    );
}

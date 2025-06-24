import React, { useState, useEffect } from "react";
import { Card, CardMedia, CardContent, Typography, Button, Box } from "@mui/material";

export function ProductCard({ product }) {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    const storedCart = JSON.parse(sessionStorage.getItem("cart")) || [];
    setCartItems(storedCart);
  }, []);

  const cartItem = cartItems.find(item => item.productId === product.productId);
  const quantityInCart = cartItem ? cartItem.quantity : 0;
  const isOutOfStock = quantityInCart >= product.stock;

  const handleAddToCart = () => {
    if (isOutOfStock) return; // Prevent adding more than stock

    const updatedCart = cartItems.map(item =>
      item.productId === product.productId ? { ...item, quantity: item.quantity + 1 } : item
    );

    if (!cartItem) {
      updatedCart.push({ productId: product.productId, name: product.name, image: product.image, price: product.price, quantity: 1 });
    }

    sessionStorage.setItem("cart", JSON.stringify(updatedCart));
    setCartItems(updatedCart);
  };

  return (
    <Card sx={{ width: 300, boxShadow: 3, p: 2 }}>
      <CardMedia component="img" image={product.image} alt={product.name} sx={{ height: 200 }} />
      <CardContent>
        <Typography variant="h6">{product.name}</Typography>
        
        {/* Price Display */}
        <Typography variant="h6" color="text.secondary" gutterBottom>
          ${product.price}
        </Typography>

        {/* Stock & Quantity in Cart */}
        <Typography variant="body2" color={product.stock > 0 ? "success.main" : "error.main"}>
          {product.stock > 0 ? `In Stock: ${product.stock}` : "Out of Stock"}
        </Typography>
        <Typography variant="body2" color="info.main">
          In Cart: {quantityInCart}
        </Typography>

        {/* Add to Cart Button */}
        <Box sx={{ mt: 2 }}>
          <Button
            variant="contained"
            color="primary"
            disabled={isOutOfStock}
            onClick={handleAddToCart}
          >
            {isOutOfStock ? "Max Limit Reached" : "Add to Cart"}
          </Button>
        </Box>
      </CardContent>
    </Card>
  );
}
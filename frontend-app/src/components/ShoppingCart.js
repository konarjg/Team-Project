import React, { useState, useEffect } from "react";
import { Box, Button, Typography, IconButton, Card, CardMedia, CardContent } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import ShoppingCartCheckoutIcon from "@mui/icons-material/ShoppingCartCheckout";
import { Link } from "react-router-dom";

export function ShoppingCart() {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    const storedCart = JSON.parse(sessionStorage.getItem("cart")) || [];
    setCartItems(storedCart);
  }, []);

  const handleIncrease = (productId) => {
    const updatedCart = cartItems.map(item =>
      item.productId === productId ? { ...item, quantity: item.quantity + 1 } : item
    );
    sessionStorage.setItem("cart", JSON.stringify(updatedCart));
    setCartItems(updatedCart);
  };

  const handleDecrease = (productId) => {
    const updatedCart = cartItems.map(item =>
      item.productId === productId ? { ...item, quantity: item.quantity - 1 } : item
    ).filter(item => item.quantity > 0); 

    sessionStorage.setItem("cart", JSON.stringify(updatedCart));
    setCartItems(updatedCart);
  };

  return (
    <Box sx={{ maxWidth: "600px", mx: "auto", mt: 4 }}>
      <Typography variant="h4" align="center" gutterBottom>
        Shopping Cart
      </Typography>

      {cartItems.length > 0 ? (
        cartItems.map((item) => (
          <Card key={item.productId} sx={{ display: "flex", mb: 2 }}>
            <CardMedia component="img" image={item.image} alt={item.name} sx={{ width: 100 }} />
            <CardContent sx={{ flex: 1 }}>
              <Typography variant="h6">{item.name}</Typography>
              <Box sx={{ display: "flex", alignItems: "center", gap: 1, mt: 1 }}>
                <IconButton onClick={() => handleDecrease(item.productId)} color="error">
                  <RemoveIcon />
                </IconButton>
                <Typography>{item.quantity}</Typography>
                <IconButton onClick={() => handleIncrease(item.productId)} color="primary">
                  <AddIcon />
                </IconButton>
              </Box>
            </CardContent>
          </Card>
        ))
      ) : (
        <Typography align="center" variant="h6" color="text.secondary">
          Your cart is empty.
        </Typography>
      )}

      {cartItems.length > 0 && (
        <Button 
          variant="contained" 
          color="primary" 
          fullWidth 
          startIcon={<ShoppingCartCheckoutIcon />}
          component={Link}
          to="/checkout"
        >
          Checkout
        </Button>
      )}
    </Box>
  );
}

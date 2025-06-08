import React, { useState, useEffect } from "react";
import { Box, Button, Typography } from "@mui/material";
import PaymentIcon from "@mui/icons-material/Payment";

export function CheckoutForm() {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    const storedCart = JSON.parse(sessionStorage.getItem("cart")) || [];
    setCartItems(storedCart);
  }, []);

  const handlePayment = () => {
    sessionStorage.removeItem("cart"); // Simulates successful payment by clearing cart
    setCartItems([]);
    alert("🎉 Order placed successfully! Thank you for shopping at Buyer Zone.");
  };

  return (
    <Box sx={{ maxWidth: "600px", mx: "auto", mt: 4, textAlign: "center" }}>
      <Typography variant="h4" gutterBottom>
        Checkout
      </Typography>

      {cartItems.length > 0 ? (
        <>
          <Typography variant="h6" color="text.secondary">
            You have {cartItems.length} item(s) in your cart.
          </Typography>
          <Button 
            variant="contained" 
            color="primary" 
            startIcon={<PaymentIcon />} 
            sx={{ mt: 3 }}
            onClick={handlePayment}
          >
            Place Order
          </Button>
        </>
      ) : (
        <Typography variant="h6" color="text.secondary">
          Your cart is empty. Add items before proceeding!
        </Typography>
      )}
    </Box>
  );
}

import React, { useState, useEffect } from "react";
import { Box, Button, Typography } from "@mui/material";
import PaymentIcon from "@mui/icons-material/Payment";
import { createOrder } from "../services/OrderService";

export function CheckoutForm() {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    const storedCart = JSON.parse(sessionStorage.getItem("cart")) || [];
    setCartItems(storedCart);
  }, []);

  const handlePayment = () => {
    const storedUser = JSON.parse(sessionStorage.getItem("user"));
    const storedCart = cartItems;
    const total = cartItems.reduce((accumulator, currentItem) => {
        const itemTotal = (currentItem.price || 0) * (currentItem.quantity || 0);
        return accumulator + itemTotal;
    }, 0);

    const order = {
       email: storedUser.email,
       status: "PREPARING",
       total: total,
       products: storedCart
    };

    createOrder(order).then(t => {
      storedUser.orders.push(order);
      sessionStorage.setItem("user", JSON.stringify(storedUser));
      sessionStorage.removeItem("cart"); 
      setCartItems([]);
      alert(t);
    });
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

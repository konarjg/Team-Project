import React from "react";
import { Link } from "react-router-dom";
import { AppBar, Toolbar, Typography, Button } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";

export function TopNav() {
  // Check if user is logged in (stored in session storage)
  const isLoggedIn = sessionStorage.getItem("isLoggedIn") === "true";

  return (
    <AppBar component="header" position="static">
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          Buyer Zone
        </Typography>

        <Button color="inherit" component={Link} to="/">Home</Button>
        
        {isLoggedIn ? (
          <>
            <Button color="inherit" component={Link} to="/cart" startIcon={<ShoppingCartIcon />}>
              Cart
            </Button>
            <Button color="inherit" component={Link} to="/account">Account</Button>
            <Button color="inherit" onClick={() => {
              sessionStorage.removeItem("isLoggedIn"); 
              window.location.reload();
            }}>
              Logout
            </Button>
          </>
        ) : (
          <Button color="inherit" component={Link} to="/login">Log in</Button>
        )}
      </Toolbar>
    </AppBar>
  );
}

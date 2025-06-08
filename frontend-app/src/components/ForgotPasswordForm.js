import React, { useState } from "react";
import { Box, TextField, Button, Typography, Link } from "@mui/material";
import { useNavigate } from "react-router-dom";

export function ForgotPasswordForm() {
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Reset password request for:", email);
  };

  return (
    <Box
      sx={{
        flex: "1",
        mx: "20vw",
        mt: 5,
        p: 3,
        display: "flex",
        flexDirection: "column",
        gap: 2,
        borderRadius: 2,
        boxShadow: 3,
      }}
    >
      <Typography variant="h5" align="center">Forgot Password</Typography>

      <TextField
        label="Email"
        type="email"
        fullWidth
        variant="outlined"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <Button variant="contained" color="primary" fullWidth onClick={handleSubmit}>
        Reset Password
      </Button>

      <Typography align="center" sx={{ mt: 2 }}>
        Remember your password?{" "}
        <Link onClick={() => navigate("/login")} sx={{ cursor: "pointer" }}>Login</Link>
      </Typography>
    </Box>
  );
};

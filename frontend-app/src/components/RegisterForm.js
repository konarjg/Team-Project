import React, { useState } from "react";
import { Box, TextField, Button, Typography, InputAdornment, IconButton, Link } from "@mui/material";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import { useNavigate } from "react-router-dom";

export function RegisterForm() {
  const [values, setValues] = useState({ name: "", email: "", password: "", showPassword: false });
  const navigate = useNavigate();

  const handleChange = (event) => {
    setValues({ ...values, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Registering with:", values);
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
      <Typography variant="h5" align="center">Create an Account</Typography>

      <TextField
        label="Full Name"
        name="name"
        type="text"
        fullWidth
        variant="outlined"
        value={values.name}
        onChange={handleChange}
      />

      <TextField
        label="Email"
        name="email"
        type="email"
        fullWidth
        variant="outlined"
        value={values.email}
        onChange={handleChange}
      />

      <TextField
        label="Password"
        name="password"
        type={values.showPassword ? "text" : "password"}
        fullWidth
        variant="outlined"
        value={values.password}
        onChange={handleChange}
        InputProps={{
          endAdornment: (
            <InputAdornment position="end">
              <IconButton onClick={() => setValues({ ...values, showPassword: !values.showPassword })}>
                {values.showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          )
        }}
      />

      <Button variant="contained" color="primary" fullWidth onClick={handleSubmit}>
        Register
      </Button>

      <Typography align="center" sx={{ mt: 2 }}>
        Already have an account?{" "}
        <Link onClick={() => navigate("/login")} sx={{ cursor: "pointer" }}>Login</Link>
      </Typography>
    </Box>
  );
};
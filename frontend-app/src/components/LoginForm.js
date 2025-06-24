import React, { useState } from "react";
import { Box, TextField, Button, Typography, InputAdornment, IconButton, Link } from "@mui/material";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import { useNavigate } from "react-router-dom";
import {login} from "../services/UserService";

export function LoginForm() {
  const [values, setValues] = useState({ email: "", password: "", showPassword: false });
  const navigate = useNavigate();

  const handleChange = (event) => {
    setValues({ ...values, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    login(values.email, values.password).then(user => {
        if (user === null) {
          alert("Incorrect credentials!");
          return;
        }

        sessionStorage.setItem("isLoggedIn", true);
        sessionStorage.setItem("user", JSON.stringify(user));
        alert("Logged in!")
        navigate("/");
    });

    
  };

  return (
    <Box
      sx={{
        mx: "20vw",
        mt: 5,
        p: 3,
        display: "flex",
        flex: "1",
        flexDirection: "column",
        gap: 2,
        borderRadius: 2,
        boxShadow: 3,
      }}
    >
      <Typography variant="h5" align="center">Login</Typography>
      
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
        Login
      </Button>
      
      <Typography align="center">
        Don't have an account?{" "}
        <Link onClick={() => navigate("/register")} sx={{ cursor: "pointer" }}>Create an Account</Link>
      </Typography>
    </Box>
  );
};

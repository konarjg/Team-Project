import {React, useState, useEffect } from "react";
import { Box, Button } from "@mui/material";
import { Link } from "react-router-dom";
import {getCategories} from "../services/CategoryService";
import { useNavigate } from "react-router-dom";

export function CategoryBar() {
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
      getCategories().then(c => {
        setCategories(c);
      })
  }, []);

  function handleClick(category) {
    if (sessionStorage.getItem("category")) {
      sessionStorage.removeItem("category");
    }
    
    sessionStorage.setItem("category", JSON.stringify(category));
    navigate(`/category/${category.name.toLowerCase()}`);
  }

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        top: "5vh",
        left: "0",
        flex: "1",
        gap: 2,
        backgroundColor: "lightblue",
        p: 2,
        boxShadow: 1
      }}
    >
    {
      categories.map(c => (
        <Button key={c.categoryId} type="button" color="inherit" sx={{fontSize: "20px"}} onClick={(event) => handleClick(c)}>{c.icon} {c.name}</Button>
      ))
    }
    </Box>
  );
}

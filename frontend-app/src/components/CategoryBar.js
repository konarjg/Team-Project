import {React, useState, useEffect } from "react";
import { Box, Button } from "@mui/material";
import { Link } from "react-router-dom";

export function CategoryBar() {
  const [categories, setCategories] = useState([
        {categoryId: 1, name: "Electronics", icon: "💻"},
        {categoryId: 2, name: "Fashion", icon: "👗"}, 
        {categoryId: 3, name: "Home", icon: "🏡"}
    ]);

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
        <Button key={c.categoryId} type="button" color="inherit" sx={{fontSize: "20px"}} component={Link} to={`/category/${c.name}`}>{c.icon} {c.name}</Button>
      ))
    }
    </Box>
  );
}

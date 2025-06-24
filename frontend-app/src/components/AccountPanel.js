import React, { useState, useEffect } from "react";
import { Box, Typography, TextField, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, Card, CardMedia, CardContent } from "@mui/material";
import { update, getUserData } from "../services/UserService";

const POLLING_INTERVAL_MS = 15000;

export function AccountPanel() {
  const [user, setUser] = useState({ name: "", email: "", password: "" });
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(sessionStorage.getItem("user"));
    if (!storedUser?.email) {
      return;
    }

    const fetchData = async () => {
      try {
        const latestUserData = await getUserData(storedUser.email);
        setUser(latestUserData);
        setOrders(latestUserData.orders);
        sessionStorage.setItem("user", JSON.stringify(latestUserData));
      } catch (error) {
        console.error("Could not refresh data, session might be invalid.", error);
      }
    };

    fetchData();

    const intervalId = setInterval(fetchData, POLLING_INTERVAL_MS);

    return () => clearInterval(intervalId);
  }, []);

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSave = async () => {
    try {
      await update(user.email, user.name, user.password);
      alert("Changes saved successfully!");
    } catch (error) {
      alert("Failed to save changes.");
    }
  };

  return (
    <Box sx={{ maxWidth: "600px", mx: "auto", mt: 4 }}>
      <Typography variant="h4" gutterBottom align="center">
        Account Panel
      </Typography>

      <Box sx={{ mb: 4 }}>
        <Typography variant="h6">Update Credentials</Typography>
        <TextField label="Name" name="name" fullWidth variant="outlined" value={user.name} onChange={handleChange} sx={{ my: 1 }} />
        <TextField label="Email" name="email" fullWidth variant="outlined" value={user.email} onChange={handleChange} sx={{ my: 1 }} readOnly />
        <TextField label="Password" name="password" type="password" placeholder="Enter new password" fullWidth variant="outlined" onChange={handleChange} sx={{ my: 1 }} />
        <Button variant="contained" color="primary" onClick={handleSave} sx={{ mt: 2 }}>
          Save Changes
        </Button>
      </Box>

      <Typography variant="h6">Order History</Typography>

      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Order ID</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Total Cost</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {orders.map((order) => (
              <TableRow key={order.orderId}>
                <TableCell>{order.orderId}</TableCell>
                <TableCell>{order.status}</TableCell>
                <TableCell>${order.total.toFixed(2)}</TableCell>
                <TableCell>
                  <Button variant="outlined" onClick={() => setSelectedOrder(order)}>
                    View Details
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Dialog open={Boolean(selectedOrder)} onClose={() => setSelectedOrder(null)}>
        <DialogTitle>Order Details - ID: {selectedOrder?.orderId}</DialogTitle>
        <DialogContent>
          {selectedOrder && selectedOrder.products.map((product, index) => (
            <Card key={index} sx={{ display: "flex", mb: 2 }}>
              <CardMedia component="img" image={product.image} alt={product.name} sx={{ width: 80, height: 80, objectFit: 'contain' }} />
              <CardContent sx={{ flex: 1 }}>
                <Typography variant="h6">{product.name}</Typography>
                <Typography>Quantity: {product.quantity}</Typography>
                <Typography>Price: ${product.price.toFixed(2)}</Typography>
              </CardContent>
            </Card>
          ))}
          <Typography variant="h6" align="right">Total: ${selectedOrder?.total.toFixed(2)}</Typography>
        </DialogContent>
      </Dialog>
    </Box>
  );
}
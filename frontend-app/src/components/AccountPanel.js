import React, { useState, useEffect } from "react";
import { Box, Typography, TextField, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, Card, CardMedia, CardContent } from "@mui/material";

export function AccountPanel() {
  const [user, setUser] = useState({ name: "", email: "", password: "" });
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);

  useEffect(() => {
    // Load user credentials (simulation)
    const storedUser = JSON.parse(sessionStorage.getItem("user")) || { name: "John Doe", email: "johndoe@example.com", password: "password123" };
    setUser(storedUser);

    // Load orders (simulation)
    const storedOrders = JSON.parse(sessionStorage.getItem("orders")) || [
      { id: 1, total: 150, status: "SHIPPING", products: [{ name: "Wireless Headphones", image: "https://example.com/headphones.jpg", quantity: 1, price: 75 }, { name: "Gaming Mouse", image: "https://example.com/mouse.jpg", quantity: 2, price: 37.5 }] },
      { id: 2, total: 800, status: "PREPARING", products: [{ name: "Smartphone", image: "https://example.com/smartphone.jpg", quantity: 1, price: 800 }] }
    ];
    setOrders(storedOrders);
  }, []);

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    sessionStorage.setItem("user", JSON.stringify(user));
    alert("✅ Credentials updated successfully!");
  };

  return (
    <Box sx={{ maxWidth: "600px", mx: "auto", mt: 4 }}>
      <Typography variant="h4" gutterBottom align="center">
        Account Panel
      </Typography>

      {/* User Credentials Update Section */}
      <Box sx={{ mb: 4 }}>
        <Typography variant="h6">Update Credentials</Typography>
        <TextField label="Name" name="name" fullWidth variant="outlined" value={user.name} onChange={handleChange} sx={{ my: 1 }} />
        <TextField label="Email" name="email" fullWidth variant="outlined" value={user.email} onChange={handleChange} sx={{ my: 1 }} />
        <TextField label="Password" name="password" type="password" fullWidth variant="outlined" value={user.password} onChange={handleChange} sx={{ my: 1 }} />
        <Button variant="contained" color="primary" onClick={handleSave} sx={{ mt: 2 }}>
          Save Changes
        </Button>
      </Box>

      <Typography variant="h6">Order History</Typography>

      {/* Order Table Section */}
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
              <TableRow key={order.id}>
                <TableCell>{order.id}</TableCell>
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

      {/* Order Details Modal */}
      <Dialog open={Boolean(selectedOrder)} onClose={() => setSelectedOrder(null)}>
        <DialogTitle>Order Details</DialogTitle>
        <DialogContent>
          {selectedOrder && selectedOrder.products.map((product, index) => (
            <Card key={index} sx={{ display: "flex", mb: 2 }}>
              <CardMedia component="img" image={product.image} alt={product.name} sx={{ width: 80 }} />
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

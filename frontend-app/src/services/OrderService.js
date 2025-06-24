import axios from "axios"; 

export async function createOrder(order) {
    try {
        const response = await axios.post("http://localhost:9090/api/orders/create", order);
        return "🎉 Order placed successfully! Thank you for shopping at Buyer Zone.";
    }
    catch (error) {
        console.error(error);
        return "There was an error creating your order!";
    }
}
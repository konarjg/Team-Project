import axios from "axios"; 

export async function getCategories() {
    try {
        const response = await axios.get("http://localhost:9090/api/categories/all");
        return response.data;
    }
    catch (error) {
        console.error(error.message);
        return [];
    }
}
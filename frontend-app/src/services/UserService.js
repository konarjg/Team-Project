import axios from "axios"; 

export async function register(email, name, password) {
    try {
        const response = await axios.post("http://localhost:9090/api/users/register", {
            email, 
            name,
            password
        });

        return "Succesfully created an account!"
    } catch (error) {
        console.error("Registration failed: ", error.message);
        return "User with this email already exists!";
    }
}

export async function getUserData(email) {
  try {
    const response = await axios.get(`http://localhost:9090/api/users/details/${email}`);
    return response.data; 
  } catch (error) {
    console.error("Failed to fetch user data:", error);
    throw error;
  }
}

export async function login(email, password) {
    try {
        const response = await axios.post("http://localhost:9090/api/users/login", {
            email, 
            password
        });
        
        return response.data; 
    } catch (error) {
        console.error("Login failed: ", error.response?.data || error.message);
        return null;
    }
}

export async function update(email, name, password) {
    try {
        const response = await axios.put("http://localhost:9090/api/users/update", {
            email, 
            name,
            password
        });

        return "Successfully updated your credentials!";
    } catch (error) {
        console.error("Update failed:", error.response?.data || error.message);
        return "There was an error updating your credentials!";
    }
}
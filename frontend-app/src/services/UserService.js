const API_URL = 'http://localhost:3000';

export const UserService = {
    async register(username, email, password) {
        const response = await fetch(`${API_URL}/api/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, email, password }),
        });
        return await response.json();
    },

    async login(username, password) {
        const response = await fetch(`${API_URL}/api/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        });
        const data = await response.json();
        if (data.success) {
            localStorage.setItem('user', JSON.stringify(data.user));
        }
        return data;
    },

    logout() {
        localStorage.removeItem('user');
    },

    async updateProfile(updatedData) {
        const response = await fetch(`${API_URL}/api/profile`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${this.getToken()}`,
            },
            body: JSON.stringify(updatedData),
        });
        return await response.json();
    },

    getCurrentUser () {
        return JSON.parse(localStorage.getItem('user'));
    },

    getToken() {
        const user = this.getCurrentUser ();
        return user ? user.token : null;
    }
};
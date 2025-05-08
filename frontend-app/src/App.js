import React, { useState } from 'react';
import './App.css';
import LoginForm from './components/LoginForm';
import RegisterForm from './components/RegisterForm';
import ShopPage from './pages/ShopPage';
import { FaHome, FaShoppingCart, FaSignInAlt, FaUserPlus } from 'react-icons/fa';
import logo from './images/logo.svg';

const App = () => {
    const [currentUser , setCurrentUser ] = useState(null); // State to manage the current user
    const [view, setView] = useState('home'); // State to manage the current view
    const [registeredUsers, setRegisteredUsers] = useState([]); // State to manage registered users

    const handleLogin = (user) => {
        const foundUser  = registeredUsers.find(
            (u) => u.username === user.username && u.password === user.password
        );

        if (foundUser ) {
            setCurrentUser (foundUser );
            setView('shop');
        } else {
            alert('Invalid username or password');
        }
    };

    const handleRegister = (newUser ) => {
        setRegisteredUsers([...registeredUsers, newUser ]);
        setView('login');
    };

    const handleLogout = () => {
        setCurrentUser (null);
        setView('home');
    };

    const toggleView = (view) => {
        setView(view);
    };

    return (
        <div className="container">
            <header>
                <nav>
                    <ul>
                        <li>
                            <img src={logo} alt="Logo" className="logo" />
                        </li>
                        <li>
                            <button onClick={() => toggleView('home')}><FaHome /> Home</button>
                        </li>
						<li>
                            <button onClick={() => toggleView('shop')}><FaShoppingCart /> Shop</button>
                        </li>
                        {currentUser  ? (
                            <>
                                <li>
                                    <button onClick={handleLogout}>Logout</button>
                                </li>
                                <li>
                                    <h2 className="greeting">Hello, {currentUser .username}!</h2>
                                </li>
                            </>
                        ) : (
                            <>
                                <li>
                                    <button onClick={() => toggleView('login')}><FaSignInAlt /> Login</button>
                                </li>
                                <li>
                                    <button onClick={() => toggleView('register')}><FaUserPlus /> Register</button>
                                </li>
                            </>
                        )}
                    </ul>
                </nav>
            </header>

            {view === 'home' && <h1>Welcome to the Online Shop</h1>}
            {view === 'login' && !currentUser  && <LoginForm onLogin={handleLogin} />}
            {view === 'register' && <RegisterForm onRegister={handleRegister} />}
            {view === 'shop' && <ShopPage currentUser ={currentUser } onLogout={handleLogout} />}
        </div>
    );
};

export default App;

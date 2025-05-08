import React, { useState } from 'react';
import ProductList from '../components/ProductList';
import Cart from '../components/Cart';

const ShopPage = ({ currentUser , onLogout }) => {
    const [cartItems, setCartItems] = useState([]);
    const [view, setView] = useState('products');

    const addToCart = (product) => {
        setCartItems([...cartItems, product]);
    };

    const removeFromCart = (productId) => {
        setCartItems(cartItems.filter(item => item.id !== productId));
    };

    const checkout = () => {
        if (cartItems.length === 0) {
            alert('Your cart is empty!');
            return;
        }
        alert('Proceeding to checkout with the following items:\n' + cartItems.map(item => item.name).join(', '));
        setCartItems([]); // Clear the cart after checkout
    };

    const toggleView = (view) => {
        setView(view);
    };

    return (
        <div className="container">
            <h1>Shop</h1>
            <button onClick={onLogout}>Logout</button>
            <div>
                <button onClick={() => toggleView('products')}>View Products</button>
                <button onClick={() => toggleView('cart')}>View Cart</button>
            </div>
            {view === 'products' && <ProductList addToCart={addToCart} />}
            {view === 'cart' && <Cart cartItems={cartItems} removeFromCart={removeFromCart} checkout={checkout} />}
        </div>
    );
};

export default ShopPage;

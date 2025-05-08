import React from 'react';

const Cart = ({ cartItems, removeFromCart, checkout }) => {
    const total = cartItems.reduce((sum, item) => sum + item.price, 0);

    return (
        <div>
            <h2>Your Shopping Cart</h2>
            {cartItems.length === 0 ? (
                <p>Your cart is empty.</p>
            ) : (
                <div>
                    <ul>
                        {cartItems.map(item => (
                            <li key={item.id}>
                                {item.name} - ${item.price.toFixed(2)}
                                <button onClick={() => removeFromCart(item.id)}>Remove</button>
                            </li>
                        ))}
                    </ul>
                    <h3>Total: ${total.toFixed(2)}</h3>
                    <button onClick={checkout}>Proceed to Checkout</button>
                </div>
            )}
        </div>
    );
};

export default Cart;

import React, { useState } from 'react';
import './Login.css';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleLogin = async (event) => {
    event.preventDefault();
    if (!username || !password) {
      setErrorMessage('Both fields are required');
      return;
    }

    try {
      const response = await fetch('/data/users.json');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      const user = data.users.find(user => user.username === username && user.password === password);

      if (user) {
        setErrorMessage('');
        alert('Login successful!');
      } else {
        setErrorMessage('Invalid username or password');
      }
    } catch (error) {
      console.error('Error fetching the users.json file:', error);
      setErrorMessage('An error occurred while trying to log in');
    }
  };

  return (
    <div className="login-page">
      <div className="header">
        <div className="header-image"></div>
        <div className="header-contact">Contact us</div>
      </div>
      <div className="login-container">
        <h2>Log in now for great values</h2>
        <form onSubmit={handleLogin}>
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input 
              type="text" 
              id="username" 
              name="username" 
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required 
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input 
              type="password" 
              id="password" 
              name="password" 
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required 
            />
          </div>
          <div className="form-options">
            <div className="remember-me">
              <input type="checkbox" id="remember" name="remember" />
              <label htmlFor="remember">Remember me</label>
            </div>
            <div className="forgot-password">
              <a href="#">Forgot Password?</a>
            </div>
          </div>
          {errorMessage && <p className="error-message">{errorMessage}</p>}
          <button type="submit" className="login-button">Log in</button>
        </form>
        <div className="signup-link">
          <a href="#">Don't have an account?</a>
        </div>
      </div>
    </div>
  );
}

export default Login;

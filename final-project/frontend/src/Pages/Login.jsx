import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from './AuthService';

const Login = ( ) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();
  
  useEffect(() => {
    const role = localStorage.getItem('role');
    if (localStorage.getItem('accessToken')) {
      handleNavigation(role);
    }
  }, [navigate]);

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      await login(username, password);
      const role = localStorage.getItem('role');
      handleNavigation(role);
    } catch (error) {
      console.error('Login failed:', error);
      setError('Invalid username or password');
    } finally {
      setLoading(false);
    }
  };

  const handleNavigation = (role) => {
    if (role === "ROLE_USER"){
      navigate("/home");
    }
    else if (role === "ROLE_ADMIN"){
      navigate("/admin");
    }
  }

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2>Login</h2>
        {loading && <p style={styles.loadingText}>Loading...</p>}
        {error && <p style={styles.errorText}>{error}</p>}
        <form onSubmit={handleLogin}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            style={styles.input}
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            style={styles.input}
          />
          <button type="submit" style={styles.button}>
            Login
          </button>
        </form>
      </div>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    background: '#f8f9fa',
  },
  card: {
    width: '300px',
    padding: '1rem',
    background: 'white',
    borderRadius: '8px',
    boxShadow: '0 0 10px rgba(0,0,0,0.1)',
    textAlign: 'center',
  },
  loadingText: {
    color: '#007bff',
    marginBottom: '1rem',
  },
  errorText: {
    color: 'red',
    marginBottom: '1rem',
  },
  input: {
    display: 'block',
    marginBottom: '1rem',
    padding: '0.5rem',
    width: '100%',
    borderRadius: '4px',
    border: '1px solid #ddd',
    boxSizing: 'border-box',
  },
  button: {
    background: '#007bff',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    width: '100%',
    transition: 'background 0.3s ease',
  },
  buttonHover: {
    background: '#0056b3',
  },
};

export default Login;

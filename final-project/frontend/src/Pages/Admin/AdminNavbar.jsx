import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { logout } from '../AuthService';

const AdminNavbar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const goToUsers = () => {
    navigate('/admin/users');
  };

  const goToProducts = () => {
    navigate('/admin/products');
  };

  const goToSellers = () => {
    navigate('/admin/sellers');
  };

  return (
    <nav style={styles.navbar}>
      <div style={styles.container}>
        <Link to="/home" style={styles.link}>
          <h1 style={styles.title}>My E-Commerce App</h1>
        </Link>
        <div style={styles.buttonContainer}>
          <button onClick={goToUsers} style={styles.navButton}>
            Users
          </button>
          <button onClick={goToProducts} style={styles.navButton}>
            Products
          </button>
          <button onClick={goToSellers} style={styles.navButton}>
            Sellers
          </button>
          <button onClick={handleLogout} style={styles.logoutButton}>
            Logout
          </button>
        </div>
      </div>
    </nav>
  );
};

const styles = {
  navbar: {
    padding: '1rem',
    background: '#343a40',
    borderBottom: '2px solid #495057',
    boxShadow: '0 2px 4px rgba(0,0,0,0.2)',
    color: 'white',
  },
  container: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    maxWidth: '1200px',
    margin: '0 auto',
  },
  title: {
    fontSize: '1.8rem',
    fontWeight: 'bold',
    margin: 0,
  },
  link: {
    textDecoration: 'none',  // Altı çizili görünümü kaldırır
    color: 'white',  // Renk ayarı, başlık rengini korur
  },
  navButton: {
    background: '#007bff',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease, transform 0.2s ease',
    marginRight: '0.5rem',
  },
  logoutButton: {
    background: '#dc3545',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease, transform 0.2s ease',
  },
  buttonContainer: {
    display: 'flex',
    alignItems: 'center',
  },
};

export default AdminNavbar;
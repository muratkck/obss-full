import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { logout } from './AuthService';

const Navbar = ({ searchTerm, setSearchTerm, handleSearch }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const handleSearchClick = () => {
    handleSearch(searchTerm);
  };

  const goToFavorites = () => {
    navigate('/favorites');
  };

  const goToBlacklist = () => {
    navigate('/blacklists');
  };

  return (
    <nav style={styles.navbar}>
      <div style={styles.container}>
        <Link to="/home" style={styles.link}>
          <h1 style={styles.title}>My E-Commerce App</h1>
        </Link>
        <div style={styles.searchContainer}>
          <input
            type="text"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            placeholder="Search products..."
            style={styles.searchBox}
          />
          <button
            onClick={handleSearchClick}
            style={styles.searchButton}
          >
            Search
          </button>
        </div>
        <div style={styles.buttonContainer}>
          <button
            onClick={goToFavorites}
            style={styles.favoritesButton}
          >
            Favorites
          </button>
          <button
            onClick={goToBlacklist}
            style={styles.blacklistButton}
          >
            Blacklist
          </button>
          <button
            onClick={handleLogout}
            style={styles.logoutButton}
          >
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
  searchContainer: {
    display: 'flex',
    alignItems: 'center',
  },
  searchBox: {
    padding: '0.5rem',
    borderRadius: '4px',
    border: '1px solid #495057',
    marginRight: '0.5rem',
    outline: 'none',
    transition: 'box-shadow 0.3s ease, border-color 0.3s ease',
    color: 'black', // Yazı rengini siyah yaparak görünür hale getirin
  },
  searchButton: {
    background: '#007bff',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease, transform 0.2s ease',
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
  favoritesButton: {
    background: '#ffc107',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease, transform 0.2s ease',
    marginRight: '0.5rem',
  },
  blacklistButton: {
    background: '#28a745',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease, transform 0.2s ease',
    marginRight: '0.5rem',
  },
  buttonContainer: {
    display: 'flex',
    alignItems: 'center',
  },
};

export default Navbar;

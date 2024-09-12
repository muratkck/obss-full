import React from 'react';
import { removeSellerFromBlacklist } from '../AuthService';

const BlacklistedSellerCard = ({ seller, onRemove }) => {
  
  const handleRemove = async () => {
    try {
      await removeSellerFromBlacklist(seller.sellerUsername);
      if (onRemove) {
        onRemove(seller.sellerUsername);
      }
    } catch (error) {
      console.error('Error removing seller from blacklist:', error);
    }
  };

  return (
    <div style={styles.card}>
      <h3 style={styles.sellerName}>{seller.sellerUsername}</h3>
      <p style={styles.reason}>Added date: {seller.createdAt}</p>
      <button onClick={handleRemove} style={styles.removeButton}>Remove from Blacklist</button>
    </div>
  );
};

const styles = {
  card: {
    padding: '1rem',
    backgroundColor: '#fff',
    boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
    borderRadius: '8px',
    margin: '1rem 0',
    textAlign: 'left',
  },
  sellerName: {
    fontSize: '1.5rem',
    color: '#333',
    marginBottom: '0.5rem',
  },
  reason: {
    fontSize: '1rem',
    color: '#555',
  },
  removeButton: {
    marginTop: '1rem',
    padding: '0.5rem 1rem',
    backgroundColor: '#ff4d4f',
    color: '#fff',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
  },
};

export default BlacklistedSellerCard;

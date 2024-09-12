import React from 'react';
import BlacklistedSellerCard from './BlacklistedSellerCard';

const Blacklists = ({ sellers, onRemove }) => {
  return (
    <div>
      {sellers.map((seller) => (
        <BlacklistedSellerCard 
            key={seller.blacklistId} 
            seller={seller} 
            onRemove={onRemove} 
        />
      ))}
    </div>
  );
};

const styles = {
    container: {
      padding: '1rem',
      backgroundColor: '#fff',
      boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
      borderRadius: '8px',
      maxWidth: '1200px',
      margin: '2rem auto',
      textAlign: 'center',
    }
  };
export default Blacklists;

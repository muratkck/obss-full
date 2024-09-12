import React from 'react';
import FavoriteCard from './FavoriteCard';

const FavoriteList = ({ products, onRemove }) => {
  return (
    <div style={{
      display: 'flex',
      flexWrap: 'wrap',
      gap: '1rem', // Kartlar arasında boşluk
      justifyContent: 'center' // Kartları ortalar
    }}>
      {products.map(product => (
        <FavoriteCard key={product.id} product={product} onRemove={onRemove} />
      ))}
    </div>
  );
};

export default FavoriteList;

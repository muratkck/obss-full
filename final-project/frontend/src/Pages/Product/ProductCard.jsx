// ProductCard.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';

const ProductCard = ({ product }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/products/${product.id}`); // Ürün detay sayfasına yönlendir
  };

  return (
    <div
      onClick={handleClick}
      style={{
        border: '1px solid #ddd',
        borderRadius: '8px',
        padding: '1rem',
        margin: '0.5rem',
        width: '200px',
        textAlign: 'center',
        boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
        background: '#fff',
        cursor: 'pointer', // Fare işaretçisini el şeklinde yapar
        transition: 'transform 0.2s', // Hover efekti için geçiş
      }}
      onMouseEnter={(e) => e.currentTarget.style.transform = 'scale(1.05)'} // Hover efekti
      onMouseLeave={(e) => e.currentTarget.style.transform = 'scale(1)'} // Hover efekti
    >
      <h3>{product.name}</h3>
      <p>{product.brand}</p> {/* Varsayılan bir description field'ı ekledik */}
      <p><strong>${product.price}</strong></p> {/* Varsayılan bir price field'ı ekledik */}
    </div>
  );
};

export default ProductCard;

import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
  getProductById,
  addProductToFavorites,
  removeProductFromFavorites,
  checkIfProductIsFavorite,
  addSellerToBlacklist,
  checkIfSellerIsBlacklisted,
  removeSellerFromBlacklist
} from '../AuthService';

const ProductDetail = () => {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [avatarUrl, setAvatarUrl] = useState('');
  const [isFavorite, setIsFavorite] = useState(false);
  const [isBlacklisted, setIsBlacklisted] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await getProductById(productId);
        setProduct(response.data);

        // Rastgele avatar URL'si oluştur
        const randomAvatarUrl = `https://api.dicebear.com/9.x/bottts/svg`;
        setAvatarUrl(randomAvatarUrl);

        // Backend'den favori durumunu kontrol et
        const favoriteStatus = await checkIfProductIsFavorite(productId);
        setIsFavorite(favoriteStatus);

        // Backend'den kara liste durumunu kontrol et
        const blacklistStatus = await checkIfSellerIsBlacklisted(response.data.sellerNames);
        setIsBlacklisted(blacklistStatus);
      } catch (error) {
        console.error('Failed to fetch product:', error);
        navigate('/home');
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [productId, navigate]);

  const handleFavoriteToggle = async () => {
    try {
      if (isFavorite) {
        await removeProductFromFavorites(productId);
      } else {
        await addProductToFavorites(productId);
      }
      setIsFavorite(!isFavorite);
    } catch (error) {
      console.error('Failed to update favorites:', error);
    }
  };

  const handleBlacklistToggle = async (sellerUsername) => {
    try {
      if (isBlacklisted) {
        await removeSellerFromBlacklist(sellerUsername);
      } else {
        await addSellerToBlacklist(sellerUsername);
      }
      setIsBlacklisted(!isBlacklisted);
    } catch (error) {
      console.error('Failed to update blacklist:', error);
    }
  };

  if (loading) {
    return (
      <div style={styles.loadingContainer}>
        <h2>Loading...</h2>
      </div>
    );
  }

  if (!product) {
    return <p>Product not found</p>;
  }

  return (
    <div style={styles.container}>
      <img src={avatarUrl} alt="Product Avatar" style={styles.avatar} />
      <h2 style={styles.title}>{product.name}</h2>
      <h3 style={styles.brand}>{product.brand}</h3>
      <p style={styles.description}>{product.description}</p>
      <p style={styles.price}><strong>${product.price}</strong></p>
      <p style={styles.sellerNames}>{product.sellerNames}</p>
      {isBlacklisted ? <></> : 
            <button
            onClick={handleFavoriteToggle}
            style={isFavorite ? styles.favoriteButtonActive : styles.favoriteButton}
          >
            {isFavorite ? 'Remove from Favorites' : 'Add to Favorites'}
          </button>}

      <button
        onClick={() => handleBlacklistToggle(product.sellerNames)}
        style={isBlacklisted ? styles.blacklistButtonActive : styles.blacklistButton}
      >
        {isBlacklisted ? 'Remove Seller from Blacklist' : 'Add Seller to Blacklist'}
      </button>
    </div>
  );
};

// Stil tanımlamaları
const styles = {
  container: {
    padding: '1rem',
    backgroundColor: '#fff',
    boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
    borderRadius: '8px',
    maxWidth: '600px',
    margin: '2rem auto',
    textAlign: 'center',
    overflow: 'hidden',
  },
  loadingContainer: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    background: '#f8f9fa',
  },
  avatar: {
    width: '120px',
    height: '120px',
    borderRadius: '50%',
    objectFit: 'cover',
    marginBottom: '1rem',
  },
  title: {
    fontSize: '1.8rem',
    color: '#333',
    marginBottom: '0.5rem',
  },
  brand: {
    fontSize: '1.4rem',
    color: '#555',
    marginBottom: '0.5rem',
  },
  description: {
    fontSize: '1rem',
    color: '#777',
    marginBottom: '1rem',
  },
  price: {
    fontSize: '1.2rem',
    color: '#2c3e50',
    marginBottom: '1rem',
  },
  sellerNames: {
    fontSize: '0.9rem',
    color: '#95a5a6',
    marginBottom: '1rem',
  },
  favoriteButton: {
    background: '#007bff',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease',
    marginRight: '0.5rem',
  },
  favoriteButtonActive: {
    background: '#28a745',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease',
    marginRight: '0.5rem',
  },
  blacklistButton: {
    background: '#dc3545',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease',
  },
  blacklistButtonActive: {
    background: '#6c757d',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background 0.3s ease',
  },
};

export default ProductDetail;

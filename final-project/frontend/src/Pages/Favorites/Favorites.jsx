import React, { useEffect, useState } from 'react';
import { getFavorites } from '../AuthService';
import FavoriteList from './FavoriteList';

const Favorites = () => {
  const [favoriteProducts, setFavoriteProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchFavorites = async () => {
      try {
        const response = await getFavorites();
        setFavoriteProducts(response);
      } catch (error) {
        console.error('Failed to fetch favorite products:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchFavorites();
  }, []);

  const handleRemoveFavorite = (productId) => {
    setFavoriteProducts(favoriteProducts.filter(product => product.id !== productId));
  };

  if (loading) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh', background: '#f8f9fa' }}>
        <h2>Loading...</h2>
      </div>
    );
  }


  return (
    <div style={styles.container}>
      <h2 style={styles.title}>My Favorites</h2>
      {favoriteProducts.length === 0 ? 
        (<p>No favorite products found.</p>)
      :
        (<FavoriteList products={favoriteProducts} onRemove={handleRemoveFavorite} />)
      }
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
  },
  title: {
    fontSize: '1.8rem',
    color: '#333',
    marginBottom: '1rem',
  },
};

export default Favorites;

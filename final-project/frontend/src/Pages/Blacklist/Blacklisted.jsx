import React, { useEffect, useState } from 'react';
import { getBlacklistedSellers } from '../AuthService'; // Kara listeye alınmış satıcıları getiren fonksiyon
import Blacklists from './Blacklists';

const Blacklisted = () => {
  const [blacklistedSellers, setBlacklistedSellers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchBlacklistedSellers = async () => {
      try {
        const response = await getBlacklistedSellers();
        setBlacklistedSellers(response);
      } catch (error) {
        console.error('Failed to fetch blacklisted sellers:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchBlacklistedSellers();
  }, []);

  const handleRemoveSeller = (sellerUsername) => {
    setBlacklistedSellers(prevSellers =>
      prevSellers.filter(seller => seller.sellerUsername !== sellerUsername)
    );
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
      <h2 style={styles.title}>Blacklisted Sellers</h2>
      {blacklistedSellers.length === 0 ? 
      (<p style={styles.noSellersMessage}>No blacklisted sellers found.</p>)
       : 
       (<Blacklists sellers={blacklistedSellers} onRemove={handleRemoveSeller} />)
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
  noSellersMessage: {
    fontSize: '1.2rem',
    color: '#666',
    marginTop: '1rem',
  },
};

export default Blacklisted;

import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { searchProducts } from './AuthService';
import Navbar from './Navbar';
import ProductList from './Product/ProductList';

const HomePage = () => {
  const [loading, setLoading] = useState(true);
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();

  const handleSearch = async (term) => {
    setLoading(true);
    try {
      const response = await searchProducts(term, 0, 100);
      setProducts(response.data.content || []);
    } catch (error) {
      console.error('Search failed:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    handleSearch(''); // Sayfa yüklendiğinde boş arama terimi ile ürünleri getir
  }, [navigate]);

  return (
    <div>
      <Navbar searchTerm={searchTerm} setSearchTerm={setSearchTerm} handleSearch={handleSearch} />
      <div style={{ padding: '1rem' }}>
        {loading ? (
          <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh', background: '#f8f9fa' }}>
            <h2>Loading...</h2>
          </div>
        ) : (
          <ProductList products={products} />
        )}
      </div>
    </div>
  );
};

export default HomePage;

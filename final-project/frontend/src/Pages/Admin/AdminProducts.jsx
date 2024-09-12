import React, { useState } from 'react';
import Sidebar from './Sidebar';
import AddProductForm from './AddProductForm';
import ProductList from './ProductList';
import { searchProducts, deleteProduct } from '../AuthService'; // Add deleteProduct in AuthService

const AdminProducts = () => {
  const [showAddProductForm, setShowAddProductForm] = useState(false);
  const [showSearch, setShowSearch] = useState(false);
  const [products, setProducts] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  const handleAddProductClick = () => {
    setShowAddProductForm(!showAddProductForm);
    setShowSearch(false);
  };


  const handleSearchClick = () => {
    setShowSearch(true);
    setShowAddProductForm(false);
  };

  const handleSearch = async () => {
    try {
      const fetchedProducts = await searchProducts(searchQuery, 0, 100);
      setProducts(fetchedProducts.data.content);
    } catch (error) {
      console.error('Failed to search products:', error);
    }
  };

  const handleDelete = async (productId) => {
    try {
      await deleteProduct(productId);
      setProducts(products.filter(product => product.id !== productId));
      alert("Product is deleted successfully!");
    } catch (error) {
      console.error('Failed to delete product:', error);
    }
  };

  return (
    <div className="flex h-screen bg-gray-50">
      <Sidebar
        onAddProductClick={handleAddProductClick}
        onSearchClick={handleSearchClick}
        showAddProductForm={showAddProductForm}
        showSearch={showSearch}
      />
      <div className="flex-1 p-6">
        <h2 className="text-3xl font-bold text-gray-800 mb-6">Admin Products</h2>
        <p className="text-gray-600 mb-6">Manage your products efficiently from this dashboard.</p>
        {showAddProductForm && <AddProductForm />}
        {showSearch && (
          <div>
            <input
              type="text"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              placeholder="Search products..."
              className="border p-2 rounded w-full mb-4"
            />
            <button
              onClick={handleSearch}
              className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300"
            >
              Search
            </button>
            {products.length > 0 ? (
              <ProductList products={products} onDelete={handleDelete} />
            ) : (
              <p>No products found.</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminProducts;

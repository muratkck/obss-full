import React, { useState } from 'react';
import SidebarSeller from './SidebarSeller';
import AddSellerForm from './AddSellerForm';
import SellerList from './SellerList';
import { searchSellers, deleteSeller } from '../AuthService';

const AdminSellers = () => {
  const [showAddSellerForm, setShowAddSellerForm] = useState(false);
  const [showSearch, setShowSearch] = useState(false);
  const [sellers, setSellers] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  const handleAddSellerClick = () => {
    setShowAddSellerForm(!showAddSellerForm);
    setShowSearch(false);
  };

  const handleSearchClick = () => {
    setShowSearch(true);
    setShowAddSellerForm(false);
  };

  const handleSearch = async () => {
    try {
      const fetchedSellers = await searchSellers('', searchQuery, 0, 100);
      setSellers(fetchedSellers.data.content);
    } catch (error) {
      console.error('Failed to search sellers:', error);
    }
  };

  const handleDelete = async (sellerId) => {
    try {
      await deleteSeller(sellerId);
      setSellers(sellers.filter(seller => seller.id !== sellerId));
      alert("Seller is deleted successfully!");
    } catch (error) {
      console.error('Failed to delete seller:', error);
    }
  };

  return (
    <div className="flex h-screen bg-gray-50">
      <SidebarSeller
        onAddSellerClick={handleAddSellerClick}
        onSearchClick={handleSearchClick}
        showAddSellerForm={showAddSellerForm}
        showSearch={showSearch}
      />
      <div className="flex-1 p-6">
        <h2 className="text-3xl font-bold text-gray-800 mb-6">Admin Sellers</h2>
        <p className="text-gray-600 mb-6">Manage your sellers efficiently from this dashboard.</p>
        {showAddSellerForm && <AddSellerForm />}
        {/* {showSellerList && <SellerList sellers={sellers} onDelete={handleDelete} />} */}
        {showSearch && (
          <div>
            <input
              type="text"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              placeholder="Search sellers..."
              className="border p-2 rounded w-full mb-4"
            />
            <button
              onClick={handleSearch}
              className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300"
            >
              Search
            </button>
            <SellerList sellers={sellers} onDelete={handleDelete} />
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminSellers;

import React from 'react';

const Sidebar = ({ 
  onAddProductClick, 
  onSearchClick, 
  showAddProductForm, 
  showSearch 
}) => {
  return (
    <div className="w-64 bg-white p-4 shadow-md h-full border-r border-gray-200">
      <h3 className="text-2xl font-semibold mb-6">Admin Panel</h3>
      <button
        onClick={onAddProductClick}
        className={`w-full p-3 mb-2 rounded-lg text-white ${showAddProductForm ? 'bg-blue-700' : 'bg-blue-500'} hover:bg-blue-600`}
      >
        Add Product
      </button>
      <button 
        onClick={onSearchClick}
        className={`w-full p-3 mb-2 rounded-lg text-white ${showSearch ? 'bg-yellow-700' : 'bg-yellow-500'} hover:bg-yellow-600`}
      >
        Search Product
      </button>
    </div>
  );
};

export default Sidebar;

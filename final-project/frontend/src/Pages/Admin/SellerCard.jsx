import React from 'react';
import { useNavigate } from 'react-router-dom';

const SellerCard = ({ seller, onDelete }) => {
  const navigate = useNavigate();

  const handleDeleteClick = () => {
    onDelete(seller.id);
  };

  const handleUpdateClick = () => {
    navigate(`/admin/seller-details/${seller.id}`); // Navigate to AdminSellerDetails
  };

  return (
    <div className="bg-white p-4 rounded-lg shadow-md border border-gray-200">
      <h3 className="text-lg font-semibold text-gray-800">{seller.username}</h3>
      <p className="text-gray-600 mt-2">{seller.fullName}</p>
      <button
        onClick={handleUpdateClick}
        className="mt-4 bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
      >
        Update Seller
      </button>
      <button
        onClick={handleDeleteClick}
        className="mt-4 bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600"
      >
        Delete Seller
      </button>
    </div>
  );
};

export default SellerCard;

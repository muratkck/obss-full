import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ProductCard = ({ product, onDelete }) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const navigate = useNavigate();

  const toggleDescription = () => {
    setIsExpanded(!isExpanded);
  };

  const handleUpdateClick = (e) => {
    navigate(`/admin/product-details/${product.id}`);
  };

  const handleDeleteClick = (e) => {
    onDelete(product.id);
  };

  return (
    <div className="bg-white p-4 rounded-lg shadow-md border border-gray-200 overflow-hidden">
      <h3 className="text-lg font-semibold text-gray-800 truncate">{product.name}</h3>
      <div className="mt-2">
        <p
          className={`text-gray-600 ${isExpanded ? 'line-clamp-none' : 'line-clamp-3'}`}
        >
          {product.description}
        </p>
        <button
          onClick={(e) => {
            toggleDescription();
          }}
          className="text-blue-500 mt-2 underline"
        >
          {isExpanded ? 'Show Less' : 'Read More'}
        </button>
      </div>
      <p className="text-gray-800 font-bold mt-2">${product.price.toFixed(2)}</p>
      <div className="mt-4 flex gap-2">
        <button
          onClick={handleUpdateClick}
          className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-300"
        >
          Update Product
        </button>
        <button
          onClick={handleDeleteClick}
          className="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition duration-300"
        >
          Delete
        </button>
      </div>
    </div>
  );
};

export default ProductCard;

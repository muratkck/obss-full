import React from 'react';
import SellerCard from './SellerCard';

const SellerList = ({ sellers, onDelete }) => {
  return (
    <div className="grid gap-6 grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
      {sellers.map((seller) => (
        <SellerCard key={seller.id} seller={seller} onDelete={onDelete} />
      ))}
    </div>
  );
};

export default SellerList;

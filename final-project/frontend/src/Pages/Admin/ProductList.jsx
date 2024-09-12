import React from 'react';
import ProductCard from './ProductCard';

const ProductList = ({ products, onDelete }) => {
  return (
    <div className="grid gap-6 grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
      {products.map((product) => (
        <ProductCard key={product.id} product={product} onDelete={onDelete} />
      ))}
    </div>
  );
};

export default ProductList;

import React, { useState } from 'react';
import { addProduct } from '../AuthService'; // AuthService içindeki addProduct metodunu import edin

const AddProductForm = ({ onSuccess }) => {
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    brand: '',
    price: '',
    sellerUsername: ''
  });
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newErrors = {};

    // Validate form data
    if (!formData.name || formData.name.length < 5 || formData.name.length > 25) {
      newErrors.name = 'Product name should be between 5 and 25 characters!';
    }
    if (!formData.description || formData.description.length < 20 || formData.description.length > 300) {
      newErrors.description = 'Product description should be between 20 and 300 characters!';
    }
    if (!formData.brand || formData.brand.length < 1 || formData.brand.length > 50) {
      newErrors.brand = 'Brand name should be at least 1 character!';
    }
    if (!formData.price || formData.price <= 0) {
      newErrors.price = 'Price must be greater than zero!';
    }
    if (!formData.sellerUsername || formData.sellerUsername.length < 2 || formData.sellerUsername.length > 50) {
      newErrors.sellerUsername = 'Seller name should be between 2 and 50 characters!';
    }
    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      setLoading(true);
      try {
        await addProduct(formData); // POST isteği gönder
        alert("Product is added succesfully!");
        setFormData({
          name: '',
          description: '',
          brand: '',
          price: '',
          sellerUsername: ''
        });
      } catch (error) {

        console.error('Failed to add product:', error);
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-md max-w-lg mx-auto">
      <h3 className="text-xl font-semibold mb-4">Add New Product</h3>
      {/* Form alanları */}
      {/* Ürün Adı */}
      <div className="mb-4">
        <label htmlFor="name" className="block text-gray-700">Product Name</label>
        <input
          type="text"
          id="name"
          name="name"
          value={formData.name}
          onChange={handleChange}
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.name ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}
      </div>
      {/* Açıklama */}
      <div className="mb-4">
        <label htmlFor="description" className="block text-gray-700">Description</label>
        <textarea
          id="description"
          name="description"
          value={formData.description}
          onChange={handleChange}
          rows="4"
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.description ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.description && <p className="text-red-500 text-sm mt-1">{errors.description}</p>}
      </div>
      {/* Marka */}
      <div className="mb-4">
        <label htmlFor="brand" className="block text-gray-700">Brand</label>
        <input
          type="text"
          id="brand"
          name="brand"
          value={formData.brand}
          onChange={handleChange}
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.brand ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.brand && <p className="text-red-500 text-sm mt-1">{errors.brand}</p>}
      </div>
      {/* Fiyat */}
      <div className="mb-4">
        <label htmlFor="price" className="block text-gray-700">Price</label>
        <input
          type="number"
          id="price"
          name="price"
          value={formData.price}
          onChange={handleChange}
          step="0.01"
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.price ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.price && <p className="text-red-500 text-sm mt-1">{errors.price}</p>}
      </div>
      {/* Satıcı Adı */}
      <div className="mb-4">
        <label htmlFor="sellerUsername" className="block text-gray-700">Seller Username</label>
        <input
          type="text"
          id="sellerUsername"
          name="sellerUsername"
          value={formData.sellerUsername}
          onChange={handleChange}
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.sellerUsername ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.sellerUsername && <p className="text-red-500 text-sm mt-1">{errors.sellerUsername}</p>}
      </div>
      <button
        type="submit"
        className={`bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-300 ${loading ? 'opacity-50 cursor-not-allowed' : ''}`}
        disabled={loading}
      >
        {loading ? 'Adding...' : 'Add Product'}
      </button>
    </form>
  );
};

export default AddProductForm;

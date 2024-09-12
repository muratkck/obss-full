import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getSellerById, updateSeller } from '../AuthService'; // Add updateSeller in AuthService

const AdminSellerDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [seller, setSeller] = useState(null);
  const [formData, setFormData] = useState({
    username: ''
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSeller = async () => {
      try {
        const response = await getSellerById(id);
        setSeller(response.data);
        setFormData({
          username: response.data.username
        });
        setLoading(false);
      } catch (error) {
        console.error('Failed to fetch seller:', error);
      }
    };

    fetchSeller();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updateSeller(id, formData);
      alert('Seller updated successfully!');
      navigate('/admin/sellers'); // Redirect after successful update
    } catch (error) {
      alert('Failed to update seller. Please try again.');
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <div className="p-6">
      <h2 className="text-3xl font-bold text-gray-800 mb-6">Update Seller</h2>
      <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-md">
        <div className="mb-4">
          <label htmlFor="username" className="block text-gray-700">Username</label>
          <input
            type="text"
            id="username"
            name="username"
            value={formData.username}
            onChange={handleChange}
            className="mt-1 block w-full border rounded-md p-2"
            required
          />
        </div>
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-300"
        >
          Update Seller
        </button>
      </form>
    </div>
  );
};

export default AdminSellerDetails;

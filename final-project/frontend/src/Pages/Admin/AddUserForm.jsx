import React, { useState } from 'react';
import { addUser } from '../AuthService'; // AuthService içindeki addUser metodunu import edin

const AddUserForm = ({ onSuccess }) => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    name: '',
    surname: ''
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

    if (!formData.username || formData.username.length < 5 || formData.username.length > 20) {
      newErrors.username = 'Username must be between 5 and 20 characters!';
    }
    if (!formData.password || !/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/.test(formData.password)) {
      newErrors.password = 'Password must be at least 8 characters and include uppercase, lowercase, number, and special character!';
    }
    if (!formData.name || formData.name.length < 2 || formData.name.length > 20) {
      newErrors.name = 'Name must be between 2 and 20 characters!';
    }
    if (!formData.surname || formData.surname.length < 2 || formData.surname.length > 20) {
      newErrors.surname = 'Surname must be between 2 and 20 characters!';
    }
    
    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      setLoading(true);
      try {
        await addUser(formData); // POST isteği gönder
        alert("User is added succesfully!")
        setFormData({
          username: '',
          password: '',
          name: '',
          surname: ''
        });
      } catch (error) {
        // Hata yönetimi için gerekli işlemler
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-md max-w-lg mx-auto">
      <h3 className="text-xl font-semibold mb-4">Add New User</h3>
      {/* Form alanları */}
      {/* Kullanıcı Adı */}
      <div className="mb-4">
        <label htmlFor="username" className="block text-gray-700">Username</label>
        <input
          type="text"
          id="username"
          name="username"
          value={formData.username}
          onChange={handleChange}
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.username ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.username && <p className="text-red-500 text-sm mt-1">{errors.username}</p>}
      </div>
      {/* Şifre */}
      <div className="mb-4">
        <label htmlFor="password" className="block text-gray-700">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.password ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.password && <p className="text-red-500 text-sm mt-1">{errors.password}</p>}
      </div>
      {/* İsim */}
      <div className="mb-4">
        <label htmlFor="name" className="block text-gray-700">Name</label>
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
      {/* Soyisim */}
      <div className="mb-4">
        <label htmlFor="surname" className="block text-gray-700">Surname</label>
        <input
          type="text"
          id="surname"
          name="surname"
          value={formData.surname}
          onChange={handleChange}
          className={`mt-1 block w-full border rounded-md p-2 ${
            errors.surname ? 'border-red-500' : 'border-gray-300'
          }`}
        />
        {errors.surname && <p className="text-red-500 text-sm mt-1">{errors.surname}</p>}
      </div>
      <button
        type="submit"
        className={`bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-300 ${loading ? 'opacity-50 cursor-not-allowed' : ''}`}
        disabled={loading}
      >
        {loading ? 'Adding...' : 'Add User'}
      </button>
    </form>
  );
};

export default AddUserForm;

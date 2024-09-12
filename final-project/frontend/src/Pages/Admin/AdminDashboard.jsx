import React from 'react';
import { Route, Routes } from 'react-router-dom';
import AdminUsers from './AdminUsers';
import AdminProducts from './AdminProducts';
import AdminSellers from './AdminSellers';
import AdminNavbar from './AdminNavbar';
import AdminProductDetails from './AdminProductDetails';
import AdminSellerDetails from './AdminSellerDetails';
import AdminUserDetails from './AdminUserDetails';

const AdminDashboard = () => {

  return (
    <div>
      <AdminNavbar />
      <div>
        <Routes>
          <Route path="/" element={<h2>Welcome to Admin Dashboard</h2>} />
          <Route path="/users" element={<AdminUsers />} />
          <Route path="/user-details/:id" element={<AdminUserDetails />} />
          <Route path="/products" element={<AdminProducts />} />
          <Route path="/product-details/:id" element={<AdminProductDetails />} />
          <Route path="/sellers" element={<AdminSellers />} />
          <Route path="/seller-details/:id" element={<AdminSellerDetails />} />
        </Routes>
      </div>
    </div>
  );
};

export default AdminDashboard;

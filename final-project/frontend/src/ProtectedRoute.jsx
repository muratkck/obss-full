import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ element, requiredRole }) => {
  const isAuthenticated = !! localStorage.getItem('accessToken');
  const role = localStorage.getItem('role');
  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }
  if (requiredRole && role !== requiredRole) {
    return <Navigate to="/login" />;
  }
  return element;
};

export default ProtectedRoute;

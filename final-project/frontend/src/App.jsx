import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './Pages/Login';
import Home from './Pages/Home';
import ProductDetail from './Pages/Product/ProductDetail'; // ProductDetail bileşenini içe aktar
import Favorites from './Pages/Favorites/Favorites';
import Blacklisted from './Pages/Blacklist/Blacklisted';
import AdminDashboard from './Pages/Admin/AdminDashboard';
import ProtectedRoute from './ProtectedRoute';

const App = () => {
  const isAuthenticated = !!localStorage.getItem('accessToken');
  const role = localStorage.getItem('role');

  return (
    <>
      <Router>
          <MyRoutes isAuthenticated={isAuthenticated} role={role}/>
      </Router>
    </>
  );
};

const MyRoutes = ({isAuthenticated}) => {

  const role = localStorage.getItem('role');
  return (
    <>
    <Routes>
      <Route path="/" element={isAuthenticated ? <Navigate to="/home" /> : <Navigate to="/login" />} />
      <Route path="/login" element={<Login />} />
      <Route path="/home" element={<ProtectedRoute element={<Home />} isAuthenticated={isAuthenticated} requiredRole="ROLE_USER" />} />
      <Route path="/products/:productId" element={<ProductDetail element={<Home />} isAuthenticated={isAuthenticated} requiredRole="ROLE_USER" />} /> 
      <Route path="/favorites" element={<ProtectedRoute element={<Favorites />} isAuthenticated={isAuthenticated} requiredRole="ROLE_USER" />} />
      <Route path="/blacklists" element={<ProtectedRoute element={<Blacklisted />} isAuthenticated={isAuthenticated} requiredRole="ROLE_USER" />} />
      <Route path="/admin/*" element={<ProtectedRoute element={<AdminDashboard />} isAuthenticated={isAuthenticated} requiredRole="ROLE_ADMIN" />} />
    </Routes>
    </>
  )
}

export default App;

// axiosInstance.js
import axios from 'axios';

// Axios instance oluştur
const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080', // Backend URL'inizi buraya yazın
});

// Interceptor ekle
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      config.headers.Authorization = `${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosInstance;

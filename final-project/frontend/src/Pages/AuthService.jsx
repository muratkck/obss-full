// authService.js
import axiosInstance from './AxiosInstance';

export const login = async (username, password) => {
  try {
    const response = await axiosInstance.post('/auth/login', { username, password });
    const accessToken = response.data.accessToken;
    const role = response.data.roles[0];
    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('role', role);
    return response.data;
  } catch (error) {
    console.error('Login failed:', error);
    throw error;
  }
};

export const logout = () => {
  localStorage.removeItem('accessToken');
  localStorage.removeItem('role');
};

export const searchProducts = async (name, page, size) => {
    try {
      const response = await axiosInstance.get('/products', {
        params: { name , page, size}
      });
      return response.data;
    } catch (error) {
      console.error('Error fetching products:', error);
      throw error;
    }
};


export const getProductById = async (id) => {
    try {
      const response = await axiosInstance.get(`/products/${id}`, {
      });
      return response.data;
    } catch (error) {
      console.error('Error fetching products:', error);
      throw error;
    }
};


export const addProductToFavorites = async (productId) => {
    try {
      const response = await axiosInstance.post('/favorites', null, {
        params: { productId }
      });
      return response.data;
    } catch (error) {
      console.error('Error adding product to favorites:', error);
      throw error;
    }
};

export const removeProductFromFavorites = async (id) => {
    try {
      await axiosInstance.delete(`/favorites`, { params: { productId: id } });
    } catch (error) {
      console.error('Error removing product from favorites:', error);
      throw error;
    }
};

export const checkIfProductIsFavorite = async (productId) => {
    try {
      const response = await axiosInstance.get(`/favorites/check`, { params: { productId } });
      return response.data.data; // Backend'den favori durumu döner
    } catch (error) {
      console.error('Error checking if product is favorite:', error);
      throw error;
    }
};

export const getFavorites = async () => {
    try {
      const response = await axiosInstance.get('/favorites');
    //   console.log(response.data);
      return response.data.data.content;
    } catch (error) {
      console.error('Error fetching favorite products:', error);
      throw error;
    }
};

export const addSellerToBlacklist = async (sellerUsername) => {
    try {
      const response = await axiosInstance.post('/blacklists', null, {
        params: { sellerUsername }
      });
      console.log(response.data)
      return response.data;
    } catch (error) {
      console.error('Error adding product to favorites:', error);
      throw error;
    }
};

export const removeSellerFromBlacklist = async (sellerUsername) => {
    try {
      await axiosInstance.delete(`/blacklists`, { params: { sellerUsername: sellerUsername } });
    } catch (error) {
      console.error('Error removing product from favorites:', error);
      throw error;
    }
};


export const checkIfSellerIsBlacklisted = async (sellerUsername) => {
    try {
      const response = await axiosInstance.get(`/blacklists/check`, { params: { sellerUsername } });
      return response.data.data; // Backend'den favori durumu döner
    } catch (error) {
      console.error('Error checking if product is favorite:', error);
      throw error;
    }
};

export const getBlacklistedSellers = async () => {
    try {
      const response = await axiosInstance.get('/blacklists');
      console.log(response.data.data.content);
      return response.data.data.content;
    } catch (error) {
      console.error('Failed to fetch blacklisted sellers:', error);
      throw error;
    }
};

export const addProduct = async (productData) => {
  try {
    const response = await axiosInstance.post('/products', productData);
    // const data = response.data;
    return response.data; 
  } catch (error) {
    console.error('Error adding product:', error);
    throw error; // Hata yönetimi için
  }
};

export const deleteProduct = async (productId) => {
  try {
    await axiosInstance.delete(`/products/${productId}`);
  } catch (error) {
    console.error('Error deleting product:', error);
    throw error;
  }
};


export const searchUsers = async (username, fullname, page, size) => {
  try {
    const url = `/users?fullname=${fullname}&page=${page}&size=${size}`;
    
    const response = await axiosInstance.get(url);
    
    return response.data;
  } catch (error) {
    console.error('Error fetching users:', error);
    throw error;
  }
};


export const addUser = async (userData) => {
  try {
    const url = '/users'; 

    const response = await axiosInstance.post(url, userData);

    return response.data;
  } catch (error) {
    console.error('Error adding user:', error);
    throw error; 
  }
};

export const deleteUser = async (id) => {
  try {
    const url = `/users/${id}`; 

    await axiosInstance.delete(url);

  } catch (error) {
    console.error('Error deleting user:', error);
    throw error; 
  }
};


export const addSeller = async (sellerData) => {
  try {
    const url = '/sellers'; 

    const response = await axiosInstance.post(url, sellerData);

    return response.data;
  } catch (error) {
    console.error('Failed to add seller:', error);
    throw error; 
  }
};


export const searchSellers = async (username, fullname, page = 0, size = 100) => {
  try {
    const url = `/sellers?fullname=${fullname}&page=${page}&size=${size}`;
    const response = await axiosInstance.get(url);
    return response.data;
  } catch (error) {
    console.error('Error fetching sellers:', error);
    throw error;
  }
};

export const deleteSeller = async (sellerId) => {
  try {
    await axiosInstance.delete(`/sellers/${sellerId}`);
  } catch (error) {
    console.error('Error deleting seller:', error);
    throw error;
  }
};

export const updateProduct = async (id, productUpdateDto) => {
  try {
    const response = await axiosInstance.put(`/products/${id}`, productUpdateDto);
    return response.data;
  } catch (error) {
    console.error('Error updating product:', error);
    throw error;
  }
};



export const updateSeller = async (id, sellerUpdateDto) => {
  try {
    const response = await axiosInstance.put(`/sellers/${id}`, sellerUpdateDto);
    return response.data;
  }catch (error) {
    console.error('Error updating product:', error);
    throw error;
  }
};

export const getSellerById = async (id) => {
  try {
    const response = await axiosInstance.get(`/sellers/${id}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching products:', error);
    throw error;
  }
};

export const getUserById = async (userId) => {
  try {
    const response = await axiosInstance.get(`/users/${userId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching user by ID:', error);
    throw error;
  }
};

export const updateUser = async (userId, userUpdateDto) => {
  try {
    const response = await axiosInstance.put(`/users/${userId}`, userUpdateDto);
    return response.data;
  } catch (error) {
    console.error('Error updating user:', error);
    throw error;
  }
};
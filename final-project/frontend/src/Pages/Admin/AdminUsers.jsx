import React, { useState } from 'react';
import AddUserForm from './AddUserForm'; 
import UserList from './UserList'; 
import { searchUsers, deleteUser } from '../AuthService'; 
import UserSidebar from './UserSidebar';

const AdminUsers = () => {
  const [showAddUserForm, setShowAddUserForm] = useState(false);
  const [showSearch, setShowSearch] = useState(false);
  const [users, setUsers] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  const handleAddUserClick = () => {
    setShowAddUserForm(!showAddUserForm);
    setShowSearch(false);
  };


  const handleSearchClick = () => {
    setShowSearch(true);
    setShowAddUserForm(false);
  };

  const handleSearch = async () => {
    try {
      const fetchedUsers = await searchUsers('', searchQuery,  0, 100);
      setUsers(fetchedUsers.data.content);
    } catch (error) {
      console.error('Failed to search users:', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteUser(id); // Kullanıcıyı sil
      // Listeyi güncellemek için tekrar kullanıcıları al
      const fetchedUsers = await searchUsers('', '', 0, 100);
      setUsers(fetchedUsers.data.content);
      alert("User is deleted successfully!");
    } catch (error) {
      console.error('Failed to delete user:', error);
    }
  };

  return (
    <div className="flex h-screen bg-gray-50">
      <UserSidebar
        onAddUserClick={handleAddUserClick}
        onSearchClick={handleSearchClick}
        showAddUserForm={showAddUserForm}
        showSearch={showSearch}
      />
      <div className="flex-1 p-6">
        <h2 className="text-3xl font-bold text-gray-800 mb-6">Admin Users</h2>
        <p className="text-gray-600 mb-6">Manage your users efficiently from this dashboard.</p>
        {showAddUserForm && <AddUserForm />}
        {showSearch && (
          <div>
            <input
              type="text"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              placeholder="Search users..."
              className="border p-2 rounded w-full mb-4"
            />
            <button
              onClick={handleSearch}
              className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300"
            >
              Search
            </button>
            {users.length > 0 ? (
              <UserList users={users} onDelete={handleDelete} />
            ) : (
              <p>No users found.</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminUsers;

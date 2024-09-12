import React from 'react';
import { useNavigate } from 'react-router-dom';

const UserCard = ({ user, onDelete }) => {
  const navigate = useNavigate();

  const handleUpdateClick = (e) => {
    navigate(`/admin/user-details/${user.id}`);
  };

  const handleDeleteClick = (e) => {
    onDelete(user.id);
  };

  return (
    <div className="bg-white p-4 rounded-lg shadow-md border border-gray-200 flex items-center justify-between">
      <div>
        <h3 className="text-lg font-semibold text-gray-800">{user.name} {user.surname}</h3>
        <p className="text-gray-600 mt-2">Username: {user.username}</p>
      </div>
      <div className="ml-4 flex gap-2">
        <button
          onClick={handleUpdateClick}
          className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-300"
        >
          Update User
        </button>
        <button
          onClick={handleDeleteClick}
          className="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition duration-300"
        >
          Delete
        </button>
      </div>
    </div>
  );
};

export default UserCard;

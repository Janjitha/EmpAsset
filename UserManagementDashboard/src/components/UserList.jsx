import axios from "axios";
import { useEffect, useState } from "react";

const UserList = () => {

    const [users, setUsers] = useState([]);
    useEffect(() => {
        const getUsers = async () => {
            try {
                const resp = await axios.get("https://jsonplaceholder.typicode.com/users");
                let apiUsers = resp.data;
                const addedUsers =JSON.parse(localStorage.getItem("addedUsers")) || [];
                setUsers([...apiUsers,...addedUsers]);
            }
            catch (err) {
                console.log(err);
            }
        };
        getUsers();
    }, []);

    const deleteUser = async (id) => {

        const confirmDelete = window.confirm("Are you sure you want to delete this user?");
        if (!confirmDelete) {
            return;
        }
        try {
            await axios.delete(`https://jsonplaceholder.typicode.com/users/${id}`);
            const filteredUsers = users.filter(user => user.id !== id);
            setUsers(filteredUsers);
            localStorage.setItem("addedUsers",JSON.stringify( filteredUsers
                .filter(user => user.id > 10)));
        }
        catch (err) {
            console.log(err);
        }
    };
    return (
        <div className="container mt-4">
            <div className="card">
                <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center">
                    <h5 className="mb-0">User List</h5>
                </div>
                <div className="card-body">
                    <table className="table table-bordered table-hover align-middle">
                        <thead className="table-secondary">
                            <tr>
                                <th>S.No</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Company</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                users.map((user, index) => (
                                    <tr key={index}>
                                        <td>{index + 1}</td>
                                        <td>{user.name}</td>
                                        <td>{user.email}</td>
                                        <td>{user.phone}</td>
                                        <td>{user.company?.name}</td>
                                        <td>
                                            <button className="btn btn-danger btn-sm" 
                                            onClick={() =>deleteUser(user.id)}>
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );

};

export default UserList;
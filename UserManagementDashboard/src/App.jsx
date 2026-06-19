import { Routes, Route, NavLink } from "react-router-dom";
import UserList from "./components/UserList";
import AddUser from "./components/AddUser";

const App = () => {
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-3">
                <span className="navbar-brand">User Management Dashboard</span>
                <div className="navbar-nav">
                    <NavLink to="/users" className="nav-link">User List</NavLink>
                    <NavLink to="/add-user" className="nav-link">Add User</NavLink>
                </div>
            </nav>

            <Routes>
                <Route path="/" element={<UserList />} />
                <Route path="/users" element={<UserList />} />
                <Route path="/add-user" element={<AddUser />} />
            </Routes>
        </>
    );
};

export default App;
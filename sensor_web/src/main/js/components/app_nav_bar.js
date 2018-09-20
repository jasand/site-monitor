import React, { Component } from 'react';
import { Link } from 'react-router-dom';

//---------------------------------------------------------------
// Top navbar menu
//---------------------------------------------------------------
class AppNavBar extends Component {
    render() {
        return (
            <nav className="navbar navbar-expand-sm navbar-dark bg-dark sticky-top">
                <Link className="navbar-link" to="/">
                    <img src='/images/gear-icon.jpeg' width="40" height="40" />
                </Link>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-divider">&nbsp;&nbsp;</li>
                        <li className="nav-item active">
                            <Link className="navbar-link" to="/">
                                Site Monitor
                            </Link>
                        </li>
                        <li className="nav-divider">&nbsp;&nbsp;</li>
                        <li className="nav-item">
                            <Link className="navbar-link" to="/dashboard">
                                Dashboard
                            </Link>
                        </li>
                        <li className="nav-divider">&nbsp;&nbsp;</li>
                        <li className="nav-item">
                            <Link className="navbar-link" to="/reports">
                                Reports
                            </Link>
                        </li>
                        <li className="nav-divider">&nbsp;&nbsp;</li>
                        <li className="nav-item">
                            <Link className="navbar-link" to="/admin">
                                Admin
                            </Link>
                        </li>
                        <li className="nav-divider">&nbsp;&nbsp;</li>
                        <li className="nav-item">
                            <Link className="navbar-link" to="/login">
                                Login
                            </Link>
                        </li>
                        <li className="nav-divider">&nbsp;&nbsp;</li>
                        <li className="nav-item">
                            <Link className="navbar-link" to="/" onClick={() => console.log("LOGOUT")}>
                                Logout
                            </Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}

export default AppNavBar;

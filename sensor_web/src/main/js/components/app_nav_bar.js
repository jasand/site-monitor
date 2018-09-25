import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import {logout} from '../actions';

//---------------------------------------------------------------
// Top navbar menu
//---------------------------------------------------------------
class AppNavBar extends Component {
    renderMenuItems() {
        console.log(this.props.login);
        const loggedIn = this.props.login.token ? true : false;
        const isAdminUser = loggedIn && this.props.login.roles.includes('ROLE_ADMIN');
        console.log("Logged in: " + loggedIn + "  Admin: " + isAdminUser);
        if (!loggedIn) {
            return (
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
                            <Link className="navbar-link" to="/login">
                                Login
                            </Link>
                        </li>
                    </ul>
                </div>
            );
        } else {
            return (
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
                        { isAdminUser ?  this.renderAdminItem() : '' }
                        { isAdminUser ?  (<li className="nav-divider">&nbsp;&nbsp;</li>) : '' }
                        <li className="nav-item">
                            <Link className="navbar-link" to="/" onClick={() => {
                                console.log("LOGOUT");
                                this.props.logout(this.props.login.token);
                            }}>
                                Logout
                            </Link>
                        </li>
                    </ul>
                </div>
            );
        }
    }

    renderAdminItem() {
        return (
            <li className="nav-item">
                <Link className="navbar-link" to="/admin">
                    Admin
                </Link>
            </li>
        );
    }


    render() {
        return (
            <nav className="navbar navbar-expand-sm navbar-dark bg-dark sticky-top">
                <Link className="navbar-link" to="/">
                    <img src='/images/gear-icon.jpeg' width="40" height="40"/>
                </Link>
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                {this.renderMenuItems()}
            </nav>
        );
    }
}

function mapStateToProps(state) {
    return {
        login: state.login
    };
}

export default connect(mapStateToProps, {logout})(AppNavBar);

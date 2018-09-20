import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {fetchSensorgroups, fetchSites} from '../actions';
import {connect} from "react-redux";
import AdminSensorGroupItem from './admin_sensor_group_item'

class AdminSensors extends Component {
    componentDidMount() {
        this.props.fetchSensorgroups(this.props.login.token);
        this.props.fetchSites(this.props.login.token);
    }

    renderSensorgroups() {
        return _.map(this.props.sensorgroups, sensorgroup => {
            return (
                <AdminSensorGroupItem key={sensorgroup.id} sensorGroup={sensorgroup}/>
            );
        });
    }

    render() {
        const siteName = this.props.sites[1] != null ? this.props.sites[1].siteName : '';
        return (
            <div>
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item"><Link to="/admin">Admin</Link></li>
                        <li className="breadcrumb-item active"  aria-current="page">
                            <Link to="/admin/sensors">Sensors</Link>
                        </li>
                    </ol>
                </nav>
                <h1>Admin sensors</h1>
                {this.renderSensorgroups()}
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        sensorgroups: state.sensorgroups,
        sites: state.sites,
        login: state.login
    }
}

export default connect(mapStateToProps, {fetchSensorgroups, fetchSites})(AdminSensors);
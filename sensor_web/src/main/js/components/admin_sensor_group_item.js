import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import AdminSensorItem from './admin_sensor_item';

class AdminSensorGroupItem extends Component {

    renderSensor(sensor) {
        return (
            <li key={sensor.id} className="list-group-item">
                <AdminSensorItem sensor={sensor}/>
            </li>
        )
    }

    renderSensors() {
        if (this.props.sensorGroup != null && this.props.sensorGroup.sensors != null) {
            return this.props.sensorGroup.sensors.map(this.renderSensor);
        }
    }

    render() {
        const linkUrl = "/admin/sensors/sensorgroup/" + this.props.sensorGroup.id;
        return (
            <div className="card" style={{marginTop: 10}}>
                <div className="card-header">
                    <div className="row">
                        <div className="col-md-2">
                            <img src="/images/sensorgroup-1.png" width="60" height="60"/>
                        </div>
                        <div className="col-md-7">
                            Sensorgroup ID: <b>{this.props.sensorGroup.sensorGroupId}</b><br/>
                            Description: {this.props.sensorGroup.description}<br/>
                            First connect: {this.props.sensorGroup.firstConnect}<br/>
                        </div>
                        <div className="col-md-3">
                            <div className="row">
                                <div className="col-6">
                                    <img src="/images/iot-site-2.png" width="40" height="40"/>
                                </div>
                                <div className="col-6">
                                    <Link to={linkUrl} className="btn btn-primary float-right" role="button">Edit</Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="card-body">
                    <ul className="list-group">
                        {this.renderSensors()}
                    </ul>
                </div>
            </div>
        );
    }
}

export default AdminSensorGroupItem;
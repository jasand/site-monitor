import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class AdminSensorItem extends Component {
    render() {
        const linkUrl = "/admin/sensors/" + this.props.sensor.id;
        return (
            <div >
                <div className="row">
                    <div className="col-md-2">
                        <img src="/images/sensor-5.png" width="60" height="60"/>
                    </div>
                    <div className="col-md-8">
                        Sensor ID: {this.props.sensor.sensorId}<br/>
                        Sensor Type: {this.props.sensor.sensorType}<br/>
                        Last value: {this.props.sensor.lastValue}<br/>
                        UnitOfMeasure: {this.props.sensor.unitOfMeasure ? this.props.sensor.unitOfMeasure.unitName : ''}<br/>
                        Last connect: {this.props.sensor.lastConnect}<br/>
                        Description: {this.props.sensor.description}<br/>
                        FirstConnect: {this.props.sensor.firstConnect}<br/>
                        conversionFunction: {this.props.sensor.conversionFunction}<br/>
                        minimumValue: {this.props.sensor.minimumValue}<br/>
                        maximumValue: {this.props.sensor.maximumValue}<br/>
                        status: {this.props.sensor.status}<br/>
                        statusTime: {this.props.sensor.statusTime}<br/>
                        warningFlag: {this.props.sensor.warningFlag}<br/>
                        mute: {this.props.sensor.mute}<br/>
                    </div>
                    <div className="col-md-2">
                        <Link to={linkUrl} className="btn btn-primary float-right" role="button">Edit</Link>
                    </div>
                </div>
            </div>
        );
    }
}

export default AdminSensorItem;
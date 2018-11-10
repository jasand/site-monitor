import React, {Component} from 'react';

class SensorDetails extends Component {
    render() {
        return (
            <div style={{marginTop: 10}}>
                <div className="row">
                    <div className="col-md-2">
                        <img src="/images/sensor-5.png" width="60" height="60"/>
                    </div>
                    <div className="col-md-8">
                        <b>Sensor ID:</b>{this.props.sensor.sensorId}<br/>
                        <b>Sensor Type:</b> {this.props.sensor.sensorType}<br/>
                        <b>Description:</b> {this.props.sensor.description}<br/>
                        <b>Last value:</b> {this.props.sensor.lastValue}<br/>
                        <b>Last connect:</b> {this.props.sensor.lastConnect}<br/>
                    </div>
                    <div className="col-md-2">
                        <img src="/images/dashboard-high-512.png" width="50" height="50"/>
                    </div>
                </div>
            </div>
        );
    }
}

export default SensorDetails;
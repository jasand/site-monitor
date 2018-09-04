import React, {Component} from 'react';
import SensorDetails from './sensor_details';

class SensorGroupDetails extends Component {

    renderSensor(sensor) {
        return (
            <li key={sensor.id} className="list-group-item">
                <SensorDetails sensor={sensor}/>
            </li>
        )
    }
    renderSensors() {
        if (this.props.sensorGroup != null && this.props.sensorGroup.sensors != null) {
            return this.props.sensorGroup.sensors.map(this.renderSensor);
        }
    }

    render() {
        return (
            <div className="card" style={{marginTop: 10}}>
                <div className="card-header">
                    <div className="row">
                        <div className="col-md-3">
                            <img src="/images/sensorgroup-1.png" width="50" height="50"/>
                        </div>
                        <div className="col-md-9">
                            <p><b>{this.props.sensorGroup.sensorGroupId}</b></p>
                            <p>{this.props.sensorGroup.description}</p>
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

export default SensorGroupDetails;
import React, {Component} from 'react';
import SensorGroupDetails from './sensor_group_details';

class MachineDetails extends Component {

    renderSensorGroup(sensorGroup) {
        return (
            <SensorGroupDetails key={sensorGroup.id} sensorGroup={sensorGroup}/>
        );
    }

    renderSensorGroups() {
        if (this.props.machine != null) {
            return this.props.machine.sensorGroups.map(this.renderSensorGroup);
        }
    }

    render() {
        console.log('Returning Machine');
        return (
            <div className="card" style={{marginTop: 20}}>
                <div className="card-header">
                    <div className="row">
                        <div className="col-md-3">
                            <img src="/images/machine-1.png" width="100" height="100"/>
                        </div>
                        <div className="col-md-6">
                            <p><b>{this.props.machine.name}</b></p>
                            <p>{this.props.machine.description}</p>
                        </div>
                        <div className="col-md-3">
                            <img src="/images/traffic-light-red.jpg" width="50" height="100"/>
                        </div>
                    </div>
                </div>
                <div className="card-body">
                    {this.renderSensorGroups()}
                </div>
            </div>
        );
    }
}

export default MachineDetails;
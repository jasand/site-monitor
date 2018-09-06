import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import {fetchSite} from '../actions';
import MachineDetails from './machine_details';
import SensorGroupDetails from './sensor_group_details';

class SiteDetails extends Component {
    componentDidMount() {
        const {siteId} = this.props.match.params;
        this.props.fetchSite(siteId);
    }


    renderSensorGroup(sensorGroup) {
        return (
            <SensorGroupDetails key={sensorGroup.id} sensorGroup={sensorGroup}/>
        );
    }

    renderSensorGroups() {
        if (this.props.site.sensorGroups != null) {
            return this.props.site.sensorGroups.map(this.renderSensorGroup);
        }
    }

    render() {
        const {siteId} = this.props.match.params;
        var linkUrl = '/dashboard/' + siteId;
        return (
            <div>
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item"><Link to="/dashboard">Dashboard</Link></li>
                        <li className="breadcrumb-item active"  aria-current="page">
                            <Link to={linkUrl}>{this.props.site.siteName}</Link>
                        </li>
                    </ol>
                </nav>
                <div style={{marginTop: 20}}>
                    <div className="row">
                        <div className="col-md-6">
                            <h2>{this.props.site.siteName}</h2>
                            <b>Address: </b> {this.props.site.siteAddress}<br/>
                        </div>
                        <div className="col-md-3">
                            <img src="/images/iot-site-2.png" width="150" height="150"/>
                        </div>
                        <div className="col-md-3">
                            <img src="/images/traffic-light-red.jpg" width="75" height="150"/>
                        </div>
                    </div>
                </div>
                <h2>Sensor Groups</h2>
                {this.renderSensorGroups()}
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {site: state.site}
}

export default connect(mapStateToProps, {fetchSite})(SiteDetails);

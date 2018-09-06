import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class AdminPage extends Component {
    render() {
        return (
            <div>
                <p></p>
                <h2>Resource Administration</h2>
                <div className="row" style={{marginTop: 20}}>
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-header">
                                <h2>Sites</h2>
                            </div>
                            <div className="card-body">
                                <Link to="/admin/sites">
                                    <img src="/images/iot-site-2.png" className="mx-auto d-block" width="200" height="200"/>
                                </Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-header">
                                <h2>Sensors</h2>
                            </div>
                            <div className="card-body">
                                <Link to="/admin/sensors">
                                    <img src="/images/sensor-5.png" className="mx-auto d-block" width="200" height="200"/>
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AdminPage;

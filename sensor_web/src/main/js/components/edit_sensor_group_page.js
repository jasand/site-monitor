import _ from 'lodash';
import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {Field, reduxForm} from 'redux-form';
import {fetchSensorgroup, fetchSites, updateSensorgroup} from '../actions';
import {connect} from "react-redux";

class EditSensorGroupPage extends Component {
    componentDidMount() {
        const {id} = this.props.match.params;
        this.props.fetchSensorgroup(id);
        this.props.fetchSites();
    }

    // TODO: Sjekk denne for CSS: https://redux-form.com/7.4.2/bundle.css (https://redux-form.com/7.4.2/examples/initializefromstate/)

    renderTextField(field) {
        return (
            <div className="form-group row">
                <label className="col-sm-3 col-form-label">{field.label}</label>
                <input
                    className="form-control  col-sm-6"
                    type="text"
                    {...field.input}
                />
            </div>
        );
    }

    renderDisabledTextField(field) {
        return (
            <div className="form-group row">
                <label className="col-sm-3 col-form-label">{field.label}</label>
                <input
                    className="form-control  col-sm-6"
                    type="text"
                    {...field.input}
                    disabled
                />
            </div>
        );
    }

    onSubmit(sensorGroup) {
        if (sensorGroup.siteId == "") {
            sensorGroup.siteId = null;
        }
        console.log(sensorGroup);
        this.props.updateSensorgroup(sensorGroup, () => {
            this.props.history.push('/admin/sensors');
        });
    }

    render() {
        const {id} = this.props.match.params;
        const linkUrl = "/admin/sensors/sensorgroup/" + id;
        const { handleSubmit } = this.props;
        return (
            <div>
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item"><Link to="/admin">Admin</Link></li>
                        <li className="breadcrumb-item">
                            <Link to="/admin/sensors">Sensors</Link>
                        </li>
                        <li className="breadcrumb-item active" aria-current="page">
                            <Link to={linkUrl}>Sensorgroup {id}</Link>
                        </li>
                    </ol>
                </nav>
                <div>
                    <h1>Edit sensorgroup: {id}</h1>
                    <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>
                        <Field
                            label="ID"
                            name="id"
                            component={this.renderDisabledTextField}
                        />
                        <div className="form-group row">
                            <label className="col-sm-3 col-form-label">Site</label>
                            <Field name="siteId" component="select" className="form-control col-sm-6 ">
                                <option value="">Connect a site...</option>
                                {_.map(this.props.sites, site => (
                                    <option value={site.id} key={site.id}>
                                        {site.siteName}
                                    </option>
                                ))}
                            </Field>
                        </div>
                        <Field
                            label="MachineId"
                            name="machineId"
                            component={this.renderDisabledTextField}
                        />
                        <Field
                            label="SensorGroupId"
                            name="sensorGroupId"
                            component={this.renderDisabledTextField}
                        />
                        <Field
                            label="Description"
                            name="description"
                            component={this.renderTextField}
                        />
                        <Field
                            label="FirstConnect"
                            name="firstConnect"
                            component={this.renderDisabledTextField}
                        />
                        <div style={{marginTop: 15}}>
                            <Link to="/admin/sensors/" className="btn btn-default" role="button">Cancel</Link>
                            <button className="btn btn-primary"
                                    onClick={() => {
                                        confirm("Are you sure you want to Update this sensor?")
                                    }}
                                    style={{marginLeft: 10}}>
                                Update
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state) {
    console.log(state.sensorgroup)
    var initVals = state.sensorgroup
    return {
        sensorgroup: state.sensorgroup,
        sites: state.sites,
        initialValues: initVals
    }
}

export default connect(mapStateToProps, {fetchSensorgroup, fetchSites, updateSensorgroup})(
    reduxForm({
        form: 'EditSensorgroupForm',
        enableReinitialize: true
    })(EditSensorGroupPage)
);

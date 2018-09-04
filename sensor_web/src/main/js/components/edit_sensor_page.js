import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {fetchSensor, fetchSites, fetchUnits, fetchSensortypes, updateSensor} from '../actions';
import {connect} from "react-redux";
import {Field, reduxForm} from 'redux-form';

class EditSensorPage extends Component {
    componentDidMount() {
        const {id} = this.props.match.params;
        this.props.fetchSensor(id);
        this.props.fetchSites();
        this.props.fetchUnits();
        this.props.fetchSensortypes();
    }

    // TODO: Sjekk denne for CSS: https://redux-form.com/7.4.2/bundle.css (https://redux-form.com/7.4.2/examples/initializefromstate/)

    renderTextField(field) {
        const {meta} = field;
        const className = `form-control col-sm-6 ${meta.touched && meta.error ? 'is-invalid' : ''}`;
        return (
            <div className="form-group row ">
                <label className="col-sm-3 col-form-label">{field.label}</label>
                <input
                    className={className}
                    type="text"
                    {...field.input}
                />
                <div className="invalid-feedback col-sm-4">
                    {meta.touched ? meta.error : ''}
                </div>
            </div>
        );
    }

    renderDisabledTextField(field) {
        return (
            <div className="form-group row">
                <label className="col-sm-3 col-form-label">{field.label}</label>
                <input
                    className="form-control col-sm-6"
                    type="text"
                    {...field.input}
                    disabled
                />
            </div>
        );
    }

    renderSelectField(field) {
        const options = this.props != null
            ? this.props.sensortypes.map(sensortype => (
                <option value={sensortype} key={sensortype}>{sensortype}</option>
            )) : "";
        return (
            <div>
                <label className="col-sm-2 col-form-label">{field.label}</label>
                <select className="form-control col-sm-6" {...field.input}>
                    <option value=""> Select sensor type</option>
                    {options}
                </select>
            </div>
        );
    }

    onSubmit(sensor) {
        // Hack siden bare id settes i select. Finn ut mer om form og select...
        this.props.units.map(unit => {
            if (sensor.unitOfMeasure.id == unit.id) {
                sensor.unitOfMeasure.unitName = unit.unitName;
                sensor.unitOfMeasure.unitSymbol = unit.unitSymbol;
            }
        });
        console.log(sensor);
        this.props.updateSensor(sensor, () => {
            this.props.history.push('/admin/sensors');
        });
    }

    render() {
        const {id} = this.props.match.params;
        const linkUrl = "/admin/sensors/" + id;
        const {handleSubmit} = this.props;
        return (
            <div>
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item"><Link to="/admin">Admin</Link></li>
                        <li className="breadcrumb-item">
                            <Link to="/admin/sensors">Sensors</Link>
                        </li>
                        <li className="breadcrumb-item active" aria-current="page">
                            <Link to={linkUrl}>Sensor {id}</Link>
                        </li>
                    </ol>
                </nav>
                <h1>Edit sensor: {id}</h1>
                <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>
                    <Field
                        label="SensorId"
                        name="sensorId"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="SensorGroupId"
                        name="sensorGroupId"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="FirstConnect"
                        name="firstConnect"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="LastConnect"
                        name="lastConnect"
                        component={this.renderDisabledTextField}
                    />
                    <div className="form-group row">
                        <label className="col-sm-3 col-form-label">SensorType</label>
                        <Field name="sensorType" component="select" className="form-control col-sm-6 ">
                            <option value="">Select a sensortype...</option>
                            {this.props.sensortypes.map(sensortype => (
                                <option value={sensortype} key={sensortype}>
                                    {sensortype}
                                </option>
                            ))}
                        </Field>
                    </div>
                    <div className="form-group row">
                        <label className="col-sm-3 col-form-label">Unit of measure</label>
                        <Field name="unitOfMeasure.id" component="select" className="form-control col-sm-6 ">
                            <option value="">Select a unit...</option>
                            {this.props.units.map(unit => (
                                <option value={unit.id} key={unit.id}>
                                    {unit.unitName + ' - ' + unit.unitSymbol}
                                </option>
                            ))}
                        </Field>
                    </div>
                    <Field
                        label="Description"
                        name="description"
                        component={this.renderTextField}
                    />
                    <Field
                        label="ConversionFunction"
                        name="conversionFunction"
                        component={this.renderTextField}
                    />
                    <Field
                        label="MinimumValue"
                        name="minimumValue"
                        component={this.renderTextField}
                    />
                    <Field
                        label="MaximumValue"
                        name="maximumValue"
                        component={this.renderTextField}
                    />
                    <Field
                        label="Last value"
                        name="lastValue"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="Status"
                        name="status"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="StatusTime"
                        name="statusTime"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="WarningFlag"
                        name="warningFlag"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="Mute"
                        name="mute"
                        component={this.renderDisabledTextField}
                    />
                    <div style={{marginTop: 15}}>
                        <Link to="/admin/sensors/" className="btn btn-default" role="button">Cancel</Link>
                        <button type="submit" className="btn btn-primary"
                                onClick={() => {
                                    confirm("Are you sure you want to Update this sensor?")
                                }}
                                style={{marginLeft: 10}}>
                            Update
                        </button>
                    </div>
                </form>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        sensor: state.sensor,
        sites: state.sites,
        units: state.units,
        sensortypes: state.sensortypes,
        initialValues: state.sensor
    }
}

function validate(values) {
    const errors = {};

    if (!values.description) {
        errors.description = "Provide description";
    }
    if (values.conversionFunction && !values.conversionFunction.includes("X")) {
        errors.conversionFunction = "Function must contain letter X";
    }

    return errors;
}

export default connect(mapStateToProps, {fetchSensor, fetchSites, fetchUnits, fetchSensortypes, updateSensor})(
    reduxForm({
        validate: validate,
        form: 'EditSensorForm',
        enableReinitialize: true
    })(EditSensorPage)
);

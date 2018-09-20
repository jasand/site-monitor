import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Field, reduxForm} from 'redux-form';
import {fetchSite, updateSite} from '../actions';
import {connect} from "react-redux";

class EditSitePage extends Component {
    componentDidMount() {
        const {siteId} = this.props.match.params;
        this.props.fetchSite(siteId, this.props.login.token);
    }

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

    onSubmit(site) {
        console.log("SUBMIT...");
        console.log(site);
        this.props.updateSite(site, this.props.login.token, () => {
            this.props.history.push('/admin/sites');
        });
    }

    render() {
        const {siteId} = this.props.match.params;
        const siteUrl = "/admin/sites/" + siteId;
        const { handleSubmit } = this.props;
        return (
            <div>
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item"><Link to="/admin">Admin</Link></li>
                        <li className="breadcrumb-item">
                            <Link to="/admin/sites">Sites</Link>
                        </li>
                        <li className="breadcrumb-item active" aria-current="page">
                            <Link to={siteUrl}>Site {siteId}</Link>
                        </li>
                    </ol>
                </nav>
                <h1>Edit Site {siteId}</h1>
                <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>
                    <Field
                        label="ID"
                        name="id"
                        component={this.renderDisabledTextField}
                    />
                    <Field
                        label="siteName"
                        name="siteName"
                        component={this.renderTextField}
                    />
                    <Field
                        label="description"
                        name="description"
                        component={this.renderTextField}
                    />
                    <Field
                        label="siteAddress"
                        name="siteAddress"
                        component={this.renderTextField}
                    />
                    <Field
                        label="contactPerson"
                        name="contactPerson"
                        component={this.renderTextField}
                    />
                    <Field
                        label="contactPhone"
                        name="contactPhone"
                        component={this.renderTextField}
                    />
                    <Field
                        label="contactEmail"
                        name="contactEmail"
                        component={this.renderTextField}
                    />
                    <Field
                        label="lat"
                        name="lat"
                        component={this.renderTextField}
                    />
                    <Field
                        label="lng"
                        name="lng"
                        component={this.renderTextField}
                    />
                    <Field
                        label="accumulatedStatus"
                        name="accumulatedSensorStatus"
                        component={this.renderDisabledTextField}
                    />
                    <div style={{marginTop: 15}}>
                        <Link to="/admin/sites" className="btn btn-default" role="button">Cancel</Link>
                        <button type="submit" className="btn btn-primary"
                                onClick={() => {
                                    confirm("Are you sure you want to Update this site?")
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
    var initVals = state.site
    return {
        site: state.site,
        login: state.login,
        initialValues: initVals
    }
}

export default connect(mapStateToProps, {fetchSite, updateSite})(
    reduxForm({
        form: 'EditSiteForm',
        enableReinitialize: true
    })(EditSitePage)
);

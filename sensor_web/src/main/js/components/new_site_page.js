import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Field, reduxForm} from 'redux-form';
import { connect } from 'react-redux';
import { createSite } from '../actions';

class NewSitePage extends Component {
    renderTextField(field) {
        return (
            <div className="form-group row">
                <label className="col-sm-3 col-form-label">{field.label}</label>
                <input
                    className="form-control col-sm-6"
                    type="text"
                    {...field.input}
                />
            </div>
        );
    }

    onSubmit(site) {
        this.props.createSite(site, () => {
            this.props.history.push('/admin/sites');
        });
    }

    render() {
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
                            <Link to="/admin/sites/new">New</Link>
                        </li>
                    </ol>
                </nav>
                <h1>New Site</h1>
                <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>
                    <Field
                        label="siteIdent"
                        name="siteIdent"
                        component={this.renderTextField}
                    />
                    <Field
                        label="siteName"
                        name="siteName"
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
                    <Link to="/admin/sites" className="btn btn-secondary" role="button" style={{marginRight: 10}}>Cancel</Link>
                    <button type="submit" className="btn btn-primary">Create</button>
                </form>
            </div>
        );
    }
}

export default reduxForm({
    form: 'NewSiteForm'
})(
    connect(null, { createSite })(NewSitePage)
);

import React, { Component } from 'react';
import {Field, reduxForm} from 'redux-form';
import {login} from '../actions';
import {connect} from "react-redux";

class LoginPage extends Component {

    onSubmit(loginData) {
        console.log("SUBMIT...");
        console.log(loginData);
        this.props.login(loginData, (res) => {
            console.log("Login callback: " + res);
            console.log(res);
        })
    }

    renderTextField(field) {
        const className = `form-control col-sm-6`;
        return (
            <div className="form-group row ">
                <label className="col-sm-3 col-form-label">{field.label}</label>
                <input
                    className={className}
                    type="text"
                    {...field.input}
                />
            </div>
        );
    }

    renderPasswordField(field) {
        const className = `form-control col-sm-6`;
        return (
            <div className="form-group row ">
                <label className="col-sm-3 col-form-label">{field.label}</label>
                <input
                    className={className}
                    type="password"
                    {...field.input}
                />
            </div>
        );
    }

    render() {
        const { handleSubmit } = this.props;
        return (
            <div>
                <h1>Login</h1>
                <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>
                    <Field
                        label="User name"
                        name="userName"
                        component={this.renderTextField}
                    />
                    <Field
                        label="Password"
                        name="password"
                        component={this.renderPasswordField}
                    />
                    <div style={{marginTop: 15}}>
                        <button type="submit" className="btn btn-primary"
                                style={{marginLeft: 10}}>
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        login: state.login
    }
}

export default connect(mapStateToProps, {login})(
    reduxForm({
        form: 'LoginPage'
    })(LoginPage)
);
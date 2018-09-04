import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import { fetchSites, deleteSite } from '../actions';
import { connect } from 'react-redux';

class AdminSiteItem extends Component {
    render() {
        var linkUrl = '/admin/sites/' + this.props.site.id;
        return (
            <li className="list-group-item">
                <div className="row">
                    <div className="col-md-2">
                        <img src="/images/factory.png" width="50" height="50"/>
                    </div>
                    <div className="col-md-7">
                        SiteName: {this.props.site.siteName} <br/>
                        Description: {this.props.site.description}
                    </div>
                    <div className="col-md-3">
                        <button className="btn btn-danger"
                                onClick={() => {
                                    if (confirm("Are you sure you want to delete the site?")) {
                                        this.props.deleteSite(this.props.site.id, () => {
                                            this.props.fetchSites();
                                        })
                                    }

                                }}>
                            Delete
                        </button>
                        <Link to={linkUrl} className="btn btn-primary float-right" role="button">Edit</Link>
                    </div>
                </div>
            </li>
        );
    }
}

export default connect(null, {fetchSites,deleteSite})(AdminSiteItem);
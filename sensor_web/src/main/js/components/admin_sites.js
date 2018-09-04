import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from "react-redux";
import {fetchSites} from '../actions';
import AdminSiteItem from './admin_site_item';

class AdminSites extends Component {
    componentDidMount() {
        this.props.fetchSites();
    }

    renderSiteItems() {
        return _.map(this.props.sites, site => {
            return (
                <AdminSiteItem key={site.id} site={site}/>
            );
        });
    }

    render() {
        return (
            <div>
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item"><Link to="/admin">Admin</Link></li>
                        <li className="breadcrumb-item active" aria-current="page">
                            <Link to="/admin/sites">Sites</Link>
                        </li>
                    </ol>
                </nav>
                <h1>Sites</h1>
                <div className="row">
                    <div className="col-12">
                        <Link to="/admin/sites/new" className="btn btn-primary float-right" role="button">New Site</Link>
                    </div>
                </div>
                <p/>
                <div>
                    <ul className="list-group">
                        {this.renderSiteItems()}
                    </ul>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {sites: state.sites}
}

export default connect(mapStateToProps, {fetchSites})(AdminSites);
import _ from 'lodash';
import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchSites} from '../actions';
import SiteIndexCard from './site_index_card';

class Dashboard extends Component {
    componentDidMount() {
        this.props.fetchSites(this.props.login.token);
    }

    renderSites() {
        return _.map(this.props.sites, site => {
            return (
                <SiteIndexCard key={site.id} site={site}/>
            );
        });
    }

    render() {
        return (
                <div className="row">
                    {this.renderSites()}
                </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        sites: state.sites,
        login: state.login
    }
}

export default connect(mapStateToProps, {fetchSites})(Dashboard);
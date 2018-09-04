import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class SiteIndexCard extends Component {
    render() {
        var linkUrl = '/dashboard/' + this.props.site.id;
        return (
            <div className="col-md-6 col-lg-4" style={{marginTop: 20}} >
                <div className="card">
                    <div className="card-header">
                        <b>{this.props.site.siteName}</b>
                    </div>
                    <div className="card-body">
                        <div className="row">
                            <div className="col-6">
                                <Link to={linkUrl}><img src="/images/factory.png" width="100" height="100"/></Link>
                            </div>
                            <div className="col-6">
                                <img src="images/traffic-light-red.jpg" width="50" height="100"/>
                            </div>
                        </div>
                        <b>Site Ident:</b> {this.props.site.siteIdent}<br/>
                        <b>Status: </b> Normal
                    </div>
                </div>
            </div>
        );
    }
}

export default SiteIndexCard;
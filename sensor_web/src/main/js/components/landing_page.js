import React, {Component} from 'react';

class LandingPage extends Component {
    render() {
        return (
            <div>
                <p></p>
                <div className="jumbotron">
                    <div>
                        <h1>Maintenance Web</h1>
                        <p className="lead">Fix it before its broken. Predictive maintenance heaven!</p>
                    </div>
                </div>
                <div>
                    <h3>Nice picture to appear professional:</h3>
                    <img src="images/internet-of-things.jpg" height="100%" width="100%"/>
                    <p>bla-bla-bla...</p>
                </div>
            </div>
        );
    }
}

export default LandingPage;

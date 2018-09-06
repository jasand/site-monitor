import React, {Component} from 'react';

class LandingPage extends Component {
    render() {
        return (
            <div>
                <p></p>
                <div className="jumbotron">
                    <div>
                        <h1>Site Monitor</h1>
                        <p className="lead">Hobby project to learn ReactJS and some IoT.</p>
                    </div>
                </div>
                <div>
                    <img src="images/internet-of-things.jpg" height="100%" width="100%"/>
                </div>
            </div>
        );
    }
}

export default LandingPage;

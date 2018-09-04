import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {createStore, applyMiddleware} from 'redux';
import {BrowserRouter, Route, Switch} from 'react-router-dom';

import ReduxPromise from 'redux-promise';

import AppNavBar from './components/app_nav_bar';
import LandingPage from './components/landing_page';
import Dashboard from './components/dashboard';
import SiteDetails from './components/site_details';
import ReportsPage from './components/reports_page';
import AdminPage from './components/admin_page';
import AdminSites from './components/admin_sites';
import AdminSensors from './components/admin_sensors';
import NewSitePage from './components/new_site_page';
import EditSitePage from './components/edit_site_page';
import EditSensorGroupPage from './components/edit_sensor_group_page';
import EditSensorPage from './components/edit_sensor_page';
import reducers from './reducers';

const createStoreWithMiddleware = applyMiddleware(ReduxPromise)(createStore);

ReactDOM.render(
    <Provider store={createStoreWithMiddleware(reducers)}>
        <div>

            <BrowserRouter>
                <div>
                    <AppNavBar/>
                    <Switch>
                        <Route path="/dashboard/:siteId" component={SiteDetails}/>
                        <Route path="/dashboard" component={Dashboard}/>
                        <Route path="/reports" component={ReportsPage}/>
                        <Route path="/admin/sites/new" component={NewSitePage}/>
                        <Route path="/admin/sites/:siteId" component={EditSitePage}/>
                        <Route path="/admin/sites" component={AdminSites}/>
                        <Route path="/admin/sensors/sensorgroup/:id" component={EditSensorGroupPage}/>
                        <Route path="/admin/sensors/:id" component={EditSensorPage}/>
                        <Route path="/admin/sensors" component={AdminSensors}/>
                        <Route path="/admin" component={AdminPage}/>
                        <Route path="/" component={LandingPage}/>
                    </Switch>
                </div>
            </BrowserRouter>
        </div>
    </Provider>
    , document.querySelector('.sensor-app'));
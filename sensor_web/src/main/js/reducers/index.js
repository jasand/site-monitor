import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form';
import SitesReducer from './reducer_sites';
import SiteReducer from './reducer_site';
import SensorgroupReducers from './reducer_sensorgroups';
import SensorgroupReducer from './reducer_sensorgroup';
import SensorReducer from './reducer_sensor';
import UnitsReducer from './reducer_units';
import SensorTypesReducer from './reducer_sensortypes';


const rootReducer = combineReducers({
    sites: SitesReducer,
    site: SiteReducer,
    sensorgroups: SensorgroupReducers,
    sensorgroup: SensorgroupReducer,
    sensor: SensorReducer,
    units: UnitsReducer,
    sensortypes: SensorTypesReducer,
    form: formReducer
});

export default rootReducer;

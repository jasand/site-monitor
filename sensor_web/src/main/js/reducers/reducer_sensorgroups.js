import _ from 'lodash';
import { FETCH_SENSORGROUPS } from '../actions';

export default function(state = {}, action) {
    switch (action.type) {
        case FETCH_SENSORGROUPS:
            return _.mapKeys(action.payload.data, 'id');
        default:
            return state;
    }
}
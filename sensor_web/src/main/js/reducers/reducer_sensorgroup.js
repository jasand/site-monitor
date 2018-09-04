import {FETCH_SENSORGROUP} from '../actions';

export default function(state = {}, action) {
    switch (action.type) {
        case FETCH_SENSORGROUP:
            return action.payload.data;
        default:
            return state;
    }
}
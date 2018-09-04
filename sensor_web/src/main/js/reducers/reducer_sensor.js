import {FETCH_SENSOR} from '../actions';

export default function(state = {}, action) {
    switch (action.type) {
        case FETCH_SENSOR:
            return action.payload.data;
        default:
            return state;
    }
}
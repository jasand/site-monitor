import {FETCH_UNITS} from '../actions';

export default function(state = [], action) {
    switch (action.type) {
        case FETCH_UNITS:
            return action.payload.data;
        default:
            return state;
    }
}
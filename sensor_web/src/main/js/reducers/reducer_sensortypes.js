import {FETCH_SENSORTYPES} from '../actions';

export default function(state = [], action) {
    switch (action.type) {
        case FETCH_SENSORTYPES:
            return action.payload.data;
        default:
            return state;
    }
}
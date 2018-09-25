import {LOGIN, LOGOUT} from '../actions';

export default function(state = {}, action) {
    switch (action.type) {
        case LOGIN:
            console.log("LOGIN reducer.");
            return action.payload.data;
        case LOGOUT:
            console.log("LOGOUT reducer.");
            return {};
        default:
            return state;
    }
}
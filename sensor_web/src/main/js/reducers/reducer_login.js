import {LOGIN} from '../actions';

export default function(state = {}, action) {
    //console.log("LOGIN: ");
    //console.log(action);
    switch (action.type) {
        case LOGIN:
            console.log("LOGIN: ");
            console.log(action);
            return action.payload.data;
        default:
            return state;
    }
}
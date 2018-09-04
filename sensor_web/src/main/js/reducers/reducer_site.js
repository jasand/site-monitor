import { FETCH_SITE } from '../actions';

export default function(state = {}, action) {
    switch (action.type) {
        case FETCH_SITE:
            return action.payload.data;
        default:
            return state;
    }
}
import axios from 'axios';

export const LOGIN = 'login';
export const LOGOUT = 'logout';
export const FETCH_SITES = 'fetch_sites';
export const FETCH_SITE = 'fetch_site';
export const UPDATE_SITE = 'update_site';
export const DELETE_SITE = 'delete_site';
export const CREATE_SITE = 'create_site';
export const FETCH_SENSORGROUPS = 'fetch_sensorgroups';
export const FETCH_SENSORGROUP = 'fetch_sensorgroup';
export const UPDATE_SENSORGROUP = 'update_sensorgroup';
export const FETCH_SENSOR = 'fetch_sensor';
export const UPDATE_SENSOR = 'update_sensor';
export const FETCH_UNITS = 'fetch_units';
export const FETCH_SENSORTYPES = 'fetch_sensortypes';

const ROOT_URL = 'http://localhost:8080/api';

function apiUrl() {
    const protocol = window.location.protocol;
    const host = window.location.hostname;
    const port = window.location.port == 8088 ? '8080' : window.location.port;
    const portIdent = port && port != '' ? ':' + port : '';
    const api_url = protocol + '//' + host + portIdent + '/api';
    console.log("API_URL: " + api_url);
    return api_url;
}

export function login(loginData, callback) {
    const request = axios.post(`${apiUrl()}/login`, loginData); //.then((res) => callback(res));
    console.log("Logging in...");
    return {
        type: LOGIN,
        payload: request
    };
};

export function logout(token, callback) {
    const request = axios.delete(`${apiUrl()}/logout/${token}`);//.then(() => callback());
    console.log("Logging out...");
    return {
        type: LOGOUT,
        payload: request
    };
};

export function fetchSites(token) {
    console.log("Token: " + token);
    const request = axios.get(`${apiUrl()}/sites`, { headers: { 'X-Auth-Token': token  } });
    console.log("Fetching sites");
    return {
        type: FETCH_SITES,
        payload: request
    };
};

export function fetchSite(siteId, token) {
    const request = axios.get(`${apiUrl()}/sites/${siteId}`, { headers: { 'X-Auth-Token': token  } });
    console.log("Fetching site " + siteId);
    return {
        type: FETCH_SITE,
        payload: request
    };
};

export function updateSite(site, token, callback) {
    const request = axios.put(`${apiUrl()}/sites/${site.id}`, site,
        { headers: { 'X-Auth-Token': token  } }).then(() => callback());
    console.log("Updating site " + site.id);
    return {
        type: UPDATE_SITE,
        payload: request
    };
};

export function createSite(site, token, callback) {
    const request = axios.post(`${apiUrl()}/sites`, site,
        { headers: { 'X-Auth-Token': token  } }).then(() => callback());
    console.log("Creating site...");
    return {
        type: CREATE_SITE,
        payload: request
    };
}

export function deleteSite(siteId, token, callback) {
    const request = axios.delete(`${apiUrl()}/sites/${siteId}`,
        { headers: { 'X-Auth-Token': token  } }).then(() => callback());
    console.log("Deleting site...");
    return {
        type: DELETE_SITE,
        payload: request
    };
}

export function fetchSensorgroups(token) {
    const request = axios.get(`${apiUrl()}/sensorgroups`, { headers: { 'X-Auth-Token': token  } });
    console.log("Fetching sensorgroups");
    return {
        type: FETCH_SENSORGROUPS,
        payload: request
    };
};

export function fetchSensorgroup(id, token) {
    const request = axios.get(`${apiUrl()}/sensorgroups/${id}`, { headers: { 'X-Auth-Token': token  } });
    console.log("Fetching sensorgroup " + id);
    return {
        type: FETCH_SENSORGROUP,
        payload: request
    };
};

export function updateSensorgroup(sensorGroup, token, callback) {
    const request = axios.put(`${apiUrl()}/sensorgroups/${sensorGroup.id}`, sensorGroup,
        { headers: { 'X-Auth-Token': token  } }).then(() => callback());
    console.log("Updating sensorgroup " + sensorGroup.id);
    return {
        type: UPDATE_SENSORGROUP,
        payload: request
    };
};

export function fetchSensor(id, token) {
    const request = axios.get(`${apiUrl()}/sensors/${id}`, { headers: { 'X-Auth-Token': token  } });
    console.log("Fetching sensor " + id);
    return {
        type: FETCH_SENSOR,
        payload: request
    };
};

export function updateSensor(sensor, token, callback) {
    const request = axios.put(`${apiUrl()}/sensors/${sensor.id}`, sensor,
        { headers: { 'X-Auth-Token': token  } }).then(() => callback());
    console.log("Updating sensor " + sensor.id);
    return {
        type: UPDATE_SENSOR,
        payload: request
    };
};

export function fetchUnits(token) {
    const request = axios.get(`${apiUrl()}/units`, { headers: { 'X-Auth-Token': token  } });
    console.log("Fetching units");
    return {
        type: FETCH_UNITS,
        payload: request
    };
};

export function fetchSensortypes(token) {
    const request = axios.get(`${apiUrl()}/sensortypes`, { headers: { 'X-Auth-Token': token  } });
    console.log("Fetching sensortypes");
    return {
        type: FETCH_SENSORTYPES,
        payload: request
    };
};

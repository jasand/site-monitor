import axios from 'axios';

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

export function fetchSites() {
    const request = axios.get(`${ROOT_URL}/sites`);
    console.log("Fetching sites");
    return {
        type: FETCH_SITES,
        payload: request
    };
};

export function fetchSite(siteId) {
    const request = axios.get(`${ROOT_URL}/sites/${siteId}`);
    console.log("Fetching site " + siteId);
    return {
        type: FETCH_SITE,
        payload: request
    };
};

export function updateSite(site, callback) {
    const request = axios.put(`${ROOT_URL}/sites/${site.id}`, site).then(() => callback());
    console.log("Updating site " + site.id);
    return {
        type: UPDATE_SITE,
        payload: request
    };
};

export function createSite(site, callback) {
    const request = axios.post(`${ROOT_URL}/sites`, site).then(() => callback());
    console.log("Creating site...");
    return {
        type: CREATE_SITE,
        payload: request
    };
}

export function deleteSite(siteId, callback) {
    const request = axios.delete(`${ROOT_URL}/sites/${siteId}`).then(() => callback());
    console.log("Deleting site...");
    return {
        type: DELETE_SITE,
        payload: request
    };
}

export function fetchSensorgroups() {
    const request = axios.get(`${ROOT_URL}/sensorgroups`);
    console.log("Fetching sensorgroups");
    return {
        type: FETCH_SENSORGROUPS,
        payload: request
    };
};

export function fetchSensorgroup(id) {
    const request = axios.get(`${ROOT_URL}/sensorgroups/${id}`);
    console.log("Fetching sensorgroup " + id);
    return {
        type: FETCH_SENSORGROUP,
        payload: request
    };
};

export function updateSensorgroup(sensorGroup, callback) {
    const request = axios.put(`${ROOT_URL}/sensorgroups/${sensorGroup.id}`, sensorGroup).then(() => callback());
    console.log("Updating sensorgroup " + sensorGroup.id);
    return {
        type: UPDATE_SENSORGROUP,
        payload: request
    };
};

export function fetchSensor(id) {
    const request = axios.get(`${ROOT_URL}/sensors/${id}`);
    console.log("Fetching sensor " + id);
    return {
        type: FETCH_SENSOR,
        payload: request
    };
};

export function updateSensor(sensor, callback) {
    const request = axios.put(`${ROOT_URL}/sensors/${sensor.id}`, sensor).then(() => callback());
    console.log("Updating sensor " + sensor.id);
    return {
        type: UPDATE_SENSOR,
        payload: request
    };
};

export function fetchUnits() {
    const request = axios.get(`${ROOT_URL}/units`);
    console.log("Fetching units");
    return {
        type: FETCH_UNITS,
        payload: request
    };
};

export function fetchSensortypes() {
    const request = axios.get(`${ROOT_URL}/sensortypes`);
    console.log("Fetching sensortypes");
    return {
        type: FETCH_SENSORTYPES,
        payload: request
    };
};

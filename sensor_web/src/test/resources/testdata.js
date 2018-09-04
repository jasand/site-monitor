
var siteListString = '[\
    {\
        "id": 2,\
        "siteIdent": "Linneavegen",\
        "siteName": "LinneavegenSite",\
        "siteAddress": null,\
        "contactPerson": null,\
        "contactPhone": null,\
        "contactEmail": null,\
        "sensorGroups": null,\
        "machines": null,\
        "accumulatedSensorStatus": null\
    },\
    {\
        "id": 3,\
        "siteIdent": "Algarheim",\
        "siteName": "Maskinlab",\
        "siteAddress": null,\
        "contactPerson": null,\
        "contactPhone": null,\
        "contactEmail": null,\
        "sensorGroups": null,\
        "machines": null,\
        "accumulatedSensorStatus": null\
    },\
    {\
        "id": 200,\
        "siteIdent": "secret-site",\
        "siteName": "JanBanan special secret Site",\
        "siteAddress": "Ny adress",\
        "contactPerson": "Jan Marzipan",\
        "contactPhone": "555-SENSOR",\
        "contactEmail": "mail@mail.no",\
        "sensorGroups": null,\
        "machines": null,\
        "accumulatedSensorStatus": null\
    },\
    {\
        "id": 1,\
        "siteIdent": "100",\
        "siteName": "JanBananSite",\
        "siteAddress": "Ny adress",\
        "contactPerson": "Jan Banan",\
        "contactPhone": "555-SENSOR",\
        "contactEmail": "mail@mail.no",\
        "sensorGroups": null,\
        "machines": null,\
        "accumulatedSensorStatus": null\
    }]';

var siteListArray = JSON.parse(siteListString);

var siteList = { sites: siteListArray };

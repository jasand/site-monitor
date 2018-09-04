CREATE USER sensoruser WITH PASSWORD 'PassWord';

CREATE DATABASE sensordata encoding = 'UTF8';

GRANT ALL PRIVILEGES ON DATABASE sensordata to sensoruser;

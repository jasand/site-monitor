
-- Table for raw incoming readings
CREATE TABLE IF NOT EXISTS sensor_reading_queue (
  id              bigserial primary key not null,
  sensor_group_id text not null,
  sensor_id       text not null,
  sensor_value    numeric(15,5),
  received        timestamp with time zone,
  processed       timestamp with time zone
);


------------------------------------------------
-- Regular application tables
------------------------------------------------

CREATE TABLE IF NOT EXISTS sites (
  id 					      bigserial primary key not null,
  site_name         text,
  description       text,
  site_address		  text,
  contact_person    text,
  contact_phone     text,
  contact_email     text,
  latitude          numeric(9,6),
  longitude          numeric(9,6)
);


CREATE TABLE IF NOT EXISTS units_of_measure (
  id 				        bigserial primary key not null,
  unit_name         text,
  unit_symbol       text
);

CREATE TABLE IF NOT EXISTS sensor_groups (
  id 				          bigserial primary key not null,
  site_id				      bigint REFERENCES sites (id),
  sensor_group_id     text,
  description         text,
  first_connect       timestamp with time ZONE
);

CREATE TABLE IF NOT EXISTS sensors (
  id 				           bigserial primary key not null,
  sensor_group_id			 bigint REFERENCES sensor_groups (id) not null,
  sensor_ident  		   text not null,
  sensor_type          text CHECK (sensor_type IN ('Temperature', 'Humidity', 'Light', 'Vibration', 'Pulse', 'Frequency', 'Counting')),
  first_connect		     timestamp with time zone not null,
  last_connect         timestamp with time zone,
  description          text,
  conversion_function  text,
  units_of_measure_id  bigint REFERENCES units_of_measure (id),
  minimum_value        numeric(15,5),
  maximum_value        numeric(15,5),
  last_value           numeric(15,5),
  status               text CHECK (status IN ('OK', 'WARN', 'ERR', 'NA')),
  status_time				   timestamp with time zone,
  warning_flag         boolean,
  mute		       	     boolean
);

CREATE TABLE IF NOT EXISTS sensor_readings (
  id          bigserial primary key not null,
  sensor_id   bigint REFERENCES sensors (id),
  raw         numeric(15,5),
  value       numeric(15,5),
  time        timestamp with time zone
);

CREATE TABLE IF NOT EXISTS users (
  id       bigserial primary key not null,
  username varchar(50) not null,
  password varchar(50) not null,
  enabled  boolean not null
);

CREATE TABLE IF NOT EXISTS roles (
  id    bigserial primary key not null,
  role  varchar(50) not null
);

CREATE TABLE IF NOT EXISTS user_roles (
  userid BIGINT REFERENCES users (id),
  roleid BIGINT REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS user_sessions (
  token    varchar(50) not null primary key,
  username varchar(50) not null,
  expires  timestamp with time zone not null
);


create unique index idx_username on users (username);


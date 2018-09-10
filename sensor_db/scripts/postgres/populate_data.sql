
INSERT INTO units_of_measure VALUES(DEFAULT, 'NA', 'NA');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Celsius', '°C');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Lux', 'lx');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Lumen', 'lu');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Hertz', 'Hz');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Kilohertz', 'kHz');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Megahertz', 'MHz');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Gigahertz', 'GHz');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Newton', 'N');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Pascal', 'P');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Joule', 'J');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Watt', 'W');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Volt', 'V');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Ohm', 'Ω');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Ampere', 'A');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Omdreininger per minutt', 'o/min');
INSERT INTO units_of_measure VALUES(DEFAULT, 'Omdreininger', 'Revs');

INSERT INTO users VALUES ('jan', 'passwd', true);
INSERT INTO users VALUES ('admin', 'passwd', true);

INSERT INTO authorities VALUES ('jan', 'ROLE_USER');
INSERT INTO authorities VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO sites VALUES(DEFAULT, 'JanBananSite', 'Description', NULL,NULL,NULL,NULL,NULL,NULL,FALSE);
INSERT INTO sites VALUES(DEFAULT, 'JanBananSite 2', 'Description', NULL,NULL,NULL,NULL,NULL,NULL,FALSE);


INSERT INTO public.sensor_groups (id, site_id, sensor_group_id, description, first_connect) VALUES (DEFAULT, 1, '013A00ABCDEF', null, '2018-02-25 20:40:27.441000');
INSERT INTO public.sensor_groups (id, site_id, sensor_group_id, description, first_connect) VALUES (DEFAULT, 1, 'sensor-group-1', null, '2018-07-20 22:00:59.339000');
INSERT INTO public.sensor_groups (id, site_id, sensor_group_id, description, first_connect) VALUES (DEFAULT, 1, 'sensor-group-2', null, '2018-07-20 22:00:59.339000');
INSERT INTO public.sensor_groups (id, site_id, sensor_group_id, description, first_connect) VALUES (DEFAULT, null, 'JansOutdoorGarageSensors', null, '2018-08-03 20:21:36.658000');
INSERT INTO public.sensor_groups (id, site_id, sensor_group_id, description, first_connect) VALUES (DEFAULT, 1, 'JansIndoorGarageSensors', 'Sjekker porten', '2018-08-04 20:26:29.637000');
INSERT INTO public.sensor_groups (id, site_id, sensor_group_id, description, first_connect) VALUES (DEFAULT, null, 'NodeMCUSite', 'xxxxxxx', '2018-03-02 22:08:32.354000');

INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 4, 'pressure', null, '2018-08-03 20:21:36.658000', '2018-08-04 18:04:04.526000', null, 'X', null, null, null, 98843.14000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 4, 'humidity', null, '2018-08-03 20:21:36.721000', '2018-08-04 18:04:04.632000', null, 'X', null, null, null, 32.17000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 4, 'light', null, '2018-08-03 20:21:36.821000', '2018-08-04 18:04:04.732000', null, 'X', null, null, null, 856.83000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 4, 'temperature', 'Temperature', '2018-08-03 20:21:36.000000', '2018-08-04 18:04:04.422000', 'Temperatur utenfor garasjen', 'X', 2, null, null, 28.49000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 5, 'distance', null, '2018-08-04 20:35:51.697000', '2018-08-04 20:51:39.827000', null, 'X', null, null, null, 21.00000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 5, 'door', null, '2018-08-04 20:26:29.637000', '2018-08-04 20:51:39.929000', null, 'X', null, null, null, 1.00000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 5, 'accuSensor', null, '2018-08-04 20:32:54.899000', '2018-08-04 20:51:40.031000', null, 'X', null, null, null, 1.00000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 2, 'emulator-sensor-1', null, '2018-07-20 22:00:59.339000', '2018-07-20 22:04:29.365000', null, 'X', null, null, null, 500.00000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 2, 'emulator-sensor-2', null, '2018-07-20 22:00:59.339000', '2018-07-20 22:04:29.365000', null, 'X', null, null, null, 500.00000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 3, 'emulator-sensor-3', null, '2018-07-20 22:00:59.339000', '2018-07-20 22:04:29.367000', null, 'X', null, null, null, 678.00000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 3, 'emulator-sensor-4', null, '2018-07-20 22:00:59.339000', '2018-07-20 22:04:29.367000', null, 'X', null, null, null, 308.00000, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 1, '013A00ABBEEF', 'Temperature', '2018-02-25 20:40:27.441000', '2018-03-02 21:48:46.998000', null, 'X', 2, null, null, 666.66600, null, null, null, null, null);
INSERT INTO public.sensors (id, sensor_group_id, sensor_ident, sensor_type, first_connect, last_connect, description, conversion_function, units_of_measure_id, minimum_value, maximum_value, last_value, status, status_time, warning_flag, mute, deleted) VALUES (DEFAULT, 6, 'NodeMCUDummySensor', null, '2018-03-02 22:08:32.354000', '2018-03-03 10:17:44.848000', null, 'X', null, null, null, 5.00000, null, null, null, null, null);




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
INSERT INTO users VALUES ('frank', 'passwd', true);
INSERT INTO users VALUES ('paal', 'passwd', true);
INSERT INTO users VALUES ('admin', 'passwd', true);

INSERT INTO authorities VALUES ('jan', 'ROLE_USER');
INSERT INTO authorities VALUES ('frank', 'ROLE_USER');
INSERT INTO authorities VALUES ('paal', 'ROLE_USER');
INSERT INTO authorities VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO sites VALUES(DEFAULT,'site-ident-1','JanBananSite',NULL,NULL,NULL,NULL,FALSE);
INSERT INTO sites VALUES(DEFAULT,'Linneavegen','LinneavegenSite',NULL,NULL,NULL,NULL,FALSE);
INSERT INTO sites VALUES(DEFAULT,'Algarheim','Maskinlab',NULL,NULL,NULL,NULL,FALSE);

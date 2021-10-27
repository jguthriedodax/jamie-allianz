-- Barcelona
INSERT INTO City(id, name) VALUES(1, 'Barcelona');

INSERT INTO District(id, name, city_id) VALUES(1, 'Gracia', 1);
INSERT INTO District(id, name, city_id) VALUES(2, 'Eixample', 1);

INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(1, 'Parc Güell', '41.414441', '2.152675', 1);
INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(2, 'Metro Joanic', '41.405722', '2.162981', 1);
INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(3, 'Placa del Gall', '41.385806', '2.156206', 2);
INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(4, 'Placa Tetuan', '41.394770', '2.175504', 2);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(20, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 1);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(19, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 1);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(24, CURRENT_TIMESTAMP, 1);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(32, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 2);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(29, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 2);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(26, CURRENT_TIMESTAMP, 2);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(32, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 3);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(30, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 3);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(31, CURRENT_TIMESTAMP, 3);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(25, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 4);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(26, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 4);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(31, CURRENT_TIMESTAMP, 4);

-- Wien
INSERT INTO City(id, name) VALUES(2, 'Wien');

INSERT INTO District(id, name, city_id) VALUES(3, 'Währing', 2);
INSERT INTO District(id, name, city_id) VALUES(4, 'Penzing', 2);

INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(5, 'Institut für Astronomie', '48.231132', '16.334474', 3);
INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(6, 'Allianz Stadion', '48.197520', '16.265287', 4);
INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(7, 'Wienerblick', '48.181186', '16.247155', 4);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(12, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 5);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(14, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 5);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(10, CURRENT_TIMESTAMP, 5);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(15, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 6);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(12, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 6);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(10, CURRENT_TIMESTAMP, 6);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(15, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 7);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(15, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 7);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(16, CURRENT_TIMESTAMP, 7);

-- Leeds
INSERT INTO City(id, name) VALUES(3, 'Leeds');

INSERT INTO District(id, name, city_id) VALUES(5, 'Headingley', 3);
INSERT INTO District(id, name, city_id) VALUES(6, 'Beeston', 3);

INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(8, 'Headingley Cricket Ground', '53.817606', '-1.581995', 5);
INSERT INTO Sensor(id, name, latitude, longitude, district_id) VALUES(9, 'Elland Road Football Ground', '53.777777', '-1.570961', 6);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(18, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 8);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(22, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 8);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(22, CURRENT_TIMESTAMP, 8);

INSERT INTO Reading(reading, at_time, sensor_id) VALUES(23, DATEADD('MINUTE',-10, CURRENT_TIMESTAMP), 9);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(19, DATEADD('MINUTE',-5, CURRENT_TIMESTAMP), 9);
INSERT INTO Reading(reading, at_time, sensor_id) VALUES(24, CURRENT_TIMESTAMP, 9);
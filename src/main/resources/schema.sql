DROP TABLE IF EXISTS EVENTS;

CREATE TABLE EVENTS (id VARCHAR(50) NOT NULL, duration FLOAT NOT NULL, type VARCHAR(250), host VARCHAR(250), alert BOOLEAN NOT NULL);
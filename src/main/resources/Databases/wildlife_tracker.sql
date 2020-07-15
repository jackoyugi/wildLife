SET MODE PostgresQL;

CREATE TABLE IF NOT EXISTS animals(

    id SERIAL PRIMARY KEY,
    name VARCHAR,
    type VARCHAR,
    age VARCHAR,
    health VARCHAR

);
CREATE TABLE IF NOT EXISTS sightings(
    id SERIAL PRIMARY KEY,
    animalId INTEGER,
    location VARCHAR,
    ranger VARCHAR,
);
CREATE TABLE IF NOT EXISTS endangeredanimals(
    id SERIAL PRIMARY KEY,
    animalid INTEGER,
    locationid INTEGER,
    date TIMESTAMP,
    animaltype VARCHAR,
    rangerid INTEGER
);
CREATE TABLE IF NOT EXISTS locations(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);
CREATE TABLE IF NOT EXISTS rangers(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    tag INTEGER
);
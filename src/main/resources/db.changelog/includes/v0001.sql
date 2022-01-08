-- liquibase formatted sql

-- changeset Liquibase:1
CREATE SCHEMA anime;

CREATE TABLE anime.anime (
    id SERIAL NOT NULL,
    name VARCHAR NOT NULL
);

CREATE UNIQUE INDEX anime_id_uindex ON anime.anime (id);

ALTER TABLE anime.anime ADD CONSTRAINT anime_pk PRIMARY KEY (id);

CREATE TABLE movie
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title  VARCHAR(255),
    "year" VARCHAR(255),
    imdbid VARCHAR(255),
    type   VARCHAR(255),
    poster VARCHAR(255),
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

INSERT INTO movie (title, type) VALUES ('TEEEEST', 'Action');
INSERT INTO movie (title, type) VALUES ('arab', 'drama');

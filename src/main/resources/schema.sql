CREATE TABLE USER_ACCOUNT
(
    id         SERIAL PRIMARY KEY,
    email      CHAR(50) NOT NULL,
    first_name CHAR(50),
    last_name  CHAR(50)
)

CREATE TABLE USER_SETTINGS
(
    id        SERIAL PRIMARY KEY,
    user_id   INT NOT NULL,
    max_aqi   INT,
    latitude  INT,
    longitude INT
);

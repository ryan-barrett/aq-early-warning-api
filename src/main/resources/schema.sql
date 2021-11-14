CREATE TABLE USER_ACCOUNT
(
    id           SERIAL PRIMARY KEY,
    apple_id     VARCHAR(50),
    email        CHAR(50) NOT NULL,
    first_name   CHAR(50),
    last_name    CHAR(50),
    last_checked TIMESTAMP
)

CREATE TABLE USER_SETTINGS
(
    id        SERIAL PRIMARY KEY,
    user_id   INT NOT NULL,
    max_aqi   INT,
    latitude  DOUBLE,
    longitude DOUBLE
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(128) NOT NULL,
    username   VARCHAR(128) NOT NULL,
    password   VARCHAR(128) NOT NULL,
    birth_date DATE,
    company_id BIGINT REFERENCES company (id)
);
CREATE TABLE company
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);
CREATE TABLE chat
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);
CREATE TABLE user_chat
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id),
    chat_id    BIGINT REFERENCES chat (id),
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(128) NOT NULL
);

DROP TABLE users;

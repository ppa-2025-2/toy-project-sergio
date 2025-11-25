CREATE TABLE evidences (
    id              UUID                PRIMARY KEY,
    content_type    VARCHAR(200)        NOT NULL,
    content         BYTEA               NOT NULL,
    filename        VARCHAR(100)        NOT NULL
    created_at      CURRENT_TIMESTAMP   DEFAULT
);
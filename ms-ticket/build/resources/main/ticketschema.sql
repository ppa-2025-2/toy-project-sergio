DROP TABLE IF EXISTS tickets;

CREATE TABLE IF NOT EXISTS tickets (
    id              INTEGER         NOT NULL PRIMARY KEY AUTOINCREMENT,
    criador_id      INTEGER         NOT NULL,
    destinatario_id INTEGER         NOT NULL,
    responsavel_id  INTEGER,
    objeto          VARCHAR(255)    NOT NULL,
    acao            VARCHAR(255)    NOT NULL,
    detalhes        VARCHAR(255)    NOT NULL,
    local           VARCHAR(255)    NOT NULL,
    status          VARCHAR(255)    NOT NULL,
    created_at      TEXT            NOT NULL DEFAULT current_timestamp,
    updated_at      TEXT            NOT NULL DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS tickets_users (
    id          INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    user_id     INTEGER     NOT NULL,
    ticket_id   INTEGER     NOT NULL
);

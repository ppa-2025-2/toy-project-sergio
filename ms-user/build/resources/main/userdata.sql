DELETE FROM users;
DELETE FROM roles;

INSERT INTO roles (name) VALUES
('ROLE_USER'),
('ROLE_GUEST'),
('ROLE_VIEWER')
;

INSERT INTO users (id, handle, email, password) VALUES
(1, 'goduser', 'goduser@adm.com', '123456abc');

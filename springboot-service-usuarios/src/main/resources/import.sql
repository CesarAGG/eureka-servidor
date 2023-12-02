INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('gustavoo', '$2a$10$dEwhg3rBJgSeQFg2w68AE.tARkyyaEUPPRq6VuHogyHwWTN2NO5.q', 1, 'Gustavo', 'Rodriguez', 'gustavo@uanl.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('nissan', '$2a$10$jZ6/V97n3.YMwB8uX2epbOzK5MFehJnfu/X/isMEnqPGVW1LwTZ5y', 1, 'Nissan', 'Gtr', 'nissan@uanl.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_to_roles (user_id, rooles_id) VALUES (1, 1);
INSERT INTO usuarios_to_roles (user_id, rooles_id) VALUES (2, 2);
INSERT INTO usuarios_to_roles (user_id, rooles_id) VALUES (2, 1);
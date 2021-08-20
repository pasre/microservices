INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('pablo', '$2a$10$FhLPWj..VjnCqLtQyQXiM.J/anwVDQeI/uIw3CPI9sI0wSa7JEdXm', 1, 'Pablo', 'Sala', 'pablo.sala@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$SAGO/k.3DoqP/Eo5DEkUhu2bs6aZWgcLyXLlpXk87x0S2Ne65LIra', 1, 'Admin', '', 'admin@gmail.com');

INSERT INTO roles (nombre) values ('ROLE_USER');
INSERT INTO roles (nombre) values ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, roles_id) values (1, 1);
INSERT INTO usuarios_roles (usuario_id, roles_id) values (2, 2);
INSERT INTO usuarios_roles (usuario_id, roles_id) values (2, 1);
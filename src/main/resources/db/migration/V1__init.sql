CREATE SCHEMA aws_challenge
    AUTHORIZATION postgres;

CREATE TABLE aws_challenge.role (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE aws_challenge.user (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    document_number VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(20),
    birthdate DATE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    id_role BIGINT REFERENCES aws_challenge.role (id) NOT NULL
);

INSERT INTO aws_challenge.role (name, description) VALUES
('ADMIN', 'Administrator with full access'),
('OWNER', 'Restaurant owner with management access'),
('EMPLOYEE', 'Restaurant employee with limited access'),
('CLIENT', 'Client with access to order food');

INSERT INTO aws_challenge.user
("name", lastname, document_number, phone, birthdate, email, "password", id_role)
VALUES('Admin', 'System', '1111111110', '5555555555', '1990-01-01', 'admin.system@mail.com', '$2a$10$F95Qb3F5Fltn2yf.Myo7deLRfnULiTUDG6XW1H8aYBzdNEnQ..lmC', 1);
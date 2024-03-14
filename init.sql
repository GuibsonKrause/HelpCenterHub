CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    subject VARCHAR NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    closed_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE ticket_feedbacks (
    id BIGSERIAL PRIMARY KEY,
    ticket_id BIGINT UNIQUE NOT NULL,
    user_id BIGINT NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Inserir usuários
INSERT INTO users (name, email, password) VALUES ('manager', 'manager@example.com', 'admin');
INSERT INTO users (name, email, password) VALUES ('user1', 'user1@example.com', 'admin');
INSERT INTO users (name, email, password) VALUES ('user2', 'user2@example.com', 'admin');

-- Inserir tipos de usuários
INSERT INTO user_roles (user_id, role) VALUES (1, 'MANAGER');
INSERT INTO user_roles (user_id, role) VALUES (2, 'USER');
INSERT INTO user_roles (user_id, role) VALUES (3, 'USER');

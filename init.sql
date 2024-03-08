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
    ticket_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE ticket_updates (
    id BIGSERIAL PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    update_type VARCHAR NOT NULL,
    description TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets (id),
    FOREIGN KEY (updated_by) REFERENCES users (id)
);

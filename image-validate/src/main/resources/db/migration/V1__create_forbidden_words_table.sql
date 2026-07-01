CREATE TABLE forbidden_words (
    id BIGSERIAL PRIMARY KEY,
    word VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP
);
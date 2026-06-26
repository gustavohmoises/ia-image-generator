CREATE TABLE images (
    id BIGSERIAL PRIMARY KEY,
    request_id UUID NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    prompt TEXT NOT NULL
);
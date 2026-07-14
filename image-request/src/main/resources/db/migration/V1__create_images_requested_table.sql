CREATE TABLE images_requested (
    id BIGSERIAL PRIMARY KEY,
    request_id UUID NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    prompt TEXT NOT NULL,
    requested_at TIMESTAMPTZ NOT NULL
);
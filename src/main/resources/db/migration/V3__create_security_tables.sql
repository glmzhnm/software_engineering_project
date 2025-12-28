CREATE TABLE t_roles (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE t_users (
                         id BIGSERIAL PRIMARY KEY,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         full_name VARCHAR(255)
);

CREATE TABLE t_user_roles (
                              user_id BIGINT NOT NULL REFERENCES t_users (id) ON DELETE CASCADE,
                              role_id BIGINT NOT NULL REFERENCES t_roles (id) ON DELETE CASCADE,
                              PRIMARY KEY (user_id, role_id)
);
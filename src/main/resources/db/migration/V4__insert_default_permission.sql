INSERT INTO t_roles (name) VALUES
                               ('ROLE_ADMIN'),
                               ('ROLE_USER'),
                               ('ROLE_MANAGER')
    ON CONFLICT (name) DO NOTHING;
INSERT INTO t_users (email, password, full_name)
VALUES
    ('admin@gmail.com', 'g1234', 'Galymzhan Admin');
INSERT INTO t_user_roles (user_id, role_id)
VALUES (
           (SELECT id FROM t_users WHERE email = 'admin@gmail.com'),
           (SELECT id FROM t_roles WHERE name = 'ROLE_ADMIN')
       );
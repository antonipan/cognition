insert into "roles" ("role") values
('teacher'),
('student'),
('admin');

insert into "users" ("username", "password") values
('anton', '2105'),
('irina', '1507'),
('moisey', '1510'),
('seva', '0111'),
('eva', '0711');

insert into user_roles (user_id, role_id) values
(1, 3);


-- create table
-- table users


create table if not exists users (
    "id" bigserial not null primary key,
    "username" varchar(255) not null,
    "password" varchar(255) not null
);


-- table roles
create table if not exists roles (
    "id"  bigserial primary key,
    "role" varchar(45) not null
);

-- table roles_of_users
create table if not exists user_roles (
    "user_id" integer not null,
    "role_id" int not null,
    foreign key ("user_id") references "users"("id"),
    foreign key ("role_id") references "roles"("id"),

    unique (user_id)
);


-- table teachers
create table if not exists teachers (
    "user_id" integer not null,
    "name" varchar(45) not null,
    "subject_id" varchar(255) not null,
    "shcool_id" integer not null,
    foreign key ("user_id") references "users"("id"),
    foreign key ("subject_id") references "subjects"("id")
);

-- table: subjects
create table if not exists subjects (
    "subject_id" serial not null,
    "name" varchar (255) not null
);

create table if not exists shcools (
    "shcool_id" serial not null,
    "name" serial not null
);






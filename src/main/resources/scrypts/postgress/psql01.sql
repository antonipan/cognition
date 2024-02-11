-- create table
-- table users
create table if not exists users(
    "id" serial not null primary key,
    "username" varchar(255) not null,
    "password" varchar(255) not null
);

-- table roles
create table if not exists roles (
    "id"  serial primary key,
    "role" varchar(45) not null
);

create table if not exists user_roles (
    "user_id" integer not null,
    "role_id" int not null,
    foreign key ("user_id") references "users"("id"),
    foreign key ("role_id") references roles(id)
);

create table if not exists teachers (
    "user_id" integer not null,
    "name" varchar(45) not null,
    "subject"
);
-- table: subjects
create table if not exists subjects (
    "subject_id" serial not null,
    "name" varchar (255)
);



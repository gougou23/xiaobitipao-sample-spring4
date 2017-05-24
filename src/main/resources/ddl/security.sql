create table users(
    username character varying(50) not null primary key,
    password character varying(50) not null,
    enabled boolean not null
);

create table authorities (
    username character varying(50) not null,
    authority character varying(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

create table groups (
    id SERIAL primary key,
    group_name character varying(50) not null
);

create table group_authorities (
    group_id bigint not null,
    authority character varying(50) not null,
    constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
    id SERIAL primary key,
    username character varying(50) not null,
    group_id bigint not null,
    constraint fk_group_members_group foreign key(group_id) references groups(id)
);

-- data

insert into users values('haha', 'password', true);
insert into users values('papa', 'password', true);
insert into users values('admin', 'password', true);


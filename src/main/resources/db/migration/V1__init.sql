create table  dirorfile (
        id              integer  auto_increment,
        path            varchar not null,
        name            varchar not null,
        link            integer not null,
        permisions      varchar not null,
        username        varchar not null,
        groupname       varchar not null,
        is_directory    boolean not null,
        size            integer not null,
        text            varchar not null,
        time            varchar not null,
        primary key (id)
);

create table  account (
        id          BINARY(16) not null,
        name        varchar not null,
        username       varchar not null,
        password    varchar not null,
        primary key (id)
);

create table forum (
        id              BINARY(16) not null,
        username        varchar not null,
        title       varchar not null,
        primary key (id)
);

create table  chat_message (
        id              BINARY(16) not null,
        username        varchar not null,
        message       varchar not null,
        forum_id integer REFERENCES forum(id),
        primary key (id)
);


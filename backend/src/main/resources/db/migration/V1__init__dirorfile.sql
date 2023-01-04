create table dirorfile (
        id              integer not null,
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
create table user
(
  id      bigint auto_increment
    primary key,
  name    varchar(100) not null,
  sex     tinyint      null,
  age     int          null,
  email   varchar(100) null,
  address varchar(255) null,
  phone   varchar(100) null,
  constraint table_name_id_uindex
  unique (id)
);


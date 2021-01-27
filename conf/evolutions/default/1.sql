# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table items (
  item_id                       serial not null,
  user_uid                      integer not null,
  subject                       varchar(255),
  item_desc                     varchar(255),
  timestamp                     timestamptz,
  status                        varchar(255),
  constraint pk_items primary key (item_id)
);

create table users (
  uid                           serial not null,
  username                      varchar(255),
  password                      varchar(255),
  constraint pk_users primary key (uid)
);

create index ix_items_user_uid on items (user_uid);
alter table items add constraint fk_items_user_uid foreign key (user_uid) references users (uid) on delete restrict on update restrict;


# --- !Downs

alter table if exists items drop constraint if exists fk_items_user_uid;
drop index if exists ix_items_user_uid;

drop table if exists items cascade;

drop table if exists users cascade;


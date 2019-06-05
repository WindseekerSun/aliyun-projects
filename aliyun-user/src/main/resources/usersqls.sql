create table user(
  id  bigint not null AUTO_INCREMENT,
  user_name varchar(30) not null ,
  nick_name varchar(30) default null,
  password varchar(100) not null ,
  real_name varchar(30),
  birthday date,
  phone char(12),
  gmt_create datetime,
  gmt_modified datetime,
  primary key (id)
);
create unique index in_user_user_name on user(user_name);
alter table user add column email varchar(50);
create unique index in_user_email on user(email);
create unique index in_user_phone on user(phone);
-- 用户登陆记录表
create table login_message(
  id bigint not null auto_increment,
  user_id bigint not null comment '关联user表的id字段',
  ip char(15) comment '登陆的ip',
  login_time datetime comment '登陆时间',
  primary key (id)
);
create index in_login_message_userid on login_message(user_id);
create index in_login_message_logintime on login_message(login_time);
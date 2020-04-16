CREATE DATABASE IF NOT EXISTS `ds_0` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
CREATE DATABASE IF NOT EXISTS `ds_1` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE ds_0;
CREATE TABLE IF NOT EXISTS `user`
(
    id   bigint(64)  not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into user
values (1001, '上海', '尹吉欢');
# ------------------------------------------------------------------------
USE ds_1;
CREATE TABLE IF NOT EXISTS `user`
(
    id   bigint(64)  not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into user
values (1002, '北京', '张三');
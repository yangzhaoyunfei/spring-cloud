CREATE DATABASE IF NOT EXISTS `ds_2` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
use ds_2;
CREATE TABLE IF NOT EXISTS `user_0`
(
    id   bigint(64)  not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `user_1`
(
    id   bigint(64)  not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `user_2`
(
    id   bigint(64)  not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `user_3`
(
    id   bigint(64)  not null,
    city varchar(20) not null,
    name varchar(20) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

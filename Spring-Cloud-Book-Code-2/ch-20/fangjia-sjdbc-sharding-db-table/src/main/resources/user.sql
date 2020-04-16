CREATE DATABASE IF NOT EXISTS `cxytiandi_0` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
CREATE DATABASE IF NOT EXISTS `cxytiandi_1` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE cxytiandi_0;
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
# --------------------------------------------
USE cxytiandi_1;
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
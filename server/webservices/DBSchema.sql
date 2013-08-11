CREATE DATABASE IF NOT EXISTS `newstoday`;

USE `newstoday`;

CREATE TABLE IF NOT EXISTS `news` (
	`id` int(50) PRIMARY KEY AUTO_INCREMENT,
	`title` varchar(500) NOT NULL DEFAULT '',
	`author` varchar(100) NOT NULL DEFAULT '',
	`content` TEXT NOT NULL DEFAULT '',
	`from`  varchar(100) NOT NULL DEFAULT '',
	`post_time` int(10) DEFAULT 0,
	`created` int(10) DEFAULT 0,
	`changed` int(10) DEFAULT 0,
	`status` int(2) DEFAULT 1,
	`top` int(2) DEFAULT 0,
	`weight` int(10) DEFAULT 0
);

CREATE TABLE IF NOT EXISTS `cron` (
	`id` int(50) PRIMARY KEY AUTO_INCREMENT,
	`started` int(10) DEFAULT 0,
	`ended` int(10) DEFAULT 0,
	`imported` int(10) DEFAULT 0,
	`comment` varchar(255) DEFAULT ''
);
/*
 Navicat Premium Data Transfer

 Source Server         : eth
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 128.14.153.134:3306
 Source Schema         : lunpan

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 14/09/2018 14:12:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for _account
-- ----------------------------
DROP TABLE IF EXISTS `_account`;
CREATE TABLE `_account` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) COLLATE utf8_bin NOT NULL,
  `amount` decimal(12,4) NOT NULL,
  `type` varchar(10) COLLATE utf8_bin NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `all_eth` decimal(12,4) DEFAULT NULL,
  `play_sum` decimal(12,4) DEFAULT NULL,
  `child_sum` int(10) DEFAULT NULL,
  `recharge_sum` decimal(12,4) DEFAULT NULL,
  `withdraw_sum` decimal(12,4) DEFAULT NULL,
  `balance` decimal(12,4) DEFAULT NULL,
  `is_machine` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9921 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for _admin
-- ----------------------------
DROP TABLE IF EXISTS `_admin`;
CREATE TABLE `_admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `pass` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for _detail
-- ----------------------------
DROP TABLE IF EXISTS `_detail`;
CREATE TABLE `_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `roomid` int(6) DEFAULT NULL,
  `userid` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `shortid` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `qiname` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `amount` decimal(12,4) DEFAULT NULL,
  `award` decimal(12,4) DEFAULT NULL,
  `number` int(2) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for _user
-- ----------------------------
DROP TABLE IF EXISTS `_user`;
CREATE TABLE `_user` (
  `id` bigint(100) unsigned NOT NULL AUTO_INCREMENT,
  `parent` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `balance` decimal(12,4) DEFAULT NULL,
  `id_md5` varchar(32) COLLATE utf8_bin NOT NULL,
  `id_short` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `all_eth` decimal(12,4) unsigned NOT NULL DEFAULT '0.0000',
  `pass` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '40',
  `play_sum` decimal(12,4) DEFAULT NULL,
  `child_sum` int(10) unsigned NOT NULL DEFAULT '0',
  `recharge_sum` decimal(12,4) DEFAULT NULL,
  `withdraw_sum` decimal(12,4) DEFAULT NULL,
  `is_machine` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=238675 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) COLLATE utf8_bin NOT NULL,
  `amount` decimal(12,6) NOT NULL,
  `type` varchar(10) COLLATE utf8_bin NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `all_eth` decimal(12,6) DEFAULT NULL,
  `play_sum` decimal(12,6) DEFAULT NULL,
  `child_sum` int(10) DEFAULT NULL,
  `recharge_sum` decimal(12,6) DEFAULT NULL,
  `withdraw_sum` decimal(12,6) DEFAULT NULL,
  `balance` decimal(12,6) DEFAULT NULL,
  `is_machine` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9921 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ctime` datetime DEFAULT NULL,
  `_name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `no1` int(2) DEFAULT NULL,
  `no2` int(2) DEFAULT NULL,
  `no3` int(2) DEFAULT NULL,
  `no4` int(2) DEFAULT NULL,
  `no5` int(2) DEFAULT NULL,
  `no6` int(2) DEFAULT NULL,
  `no7` int(2) DEFAULT NULL,
  `no8` int(2) DEFAULT NULL,
  `no9` int(2) DEFAULT NULL,
  `no10` int(2) DEFAULT NULL,
  `no11` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `result` decimal(12,6) DEFAULT NULL,
  `type` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `balance` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `short_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1035914 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for dsystem
-- ----------------------------
DROP TABLE IF EXISTS `dsystem`;
CREATE TABLE `dsystem` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `account` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `withdraw_number` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `roomid` int(6) DEFAULT NULL,
  `message` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `_name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `amount` decimal(12,4) DEFAULT NULL,
  `progress` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(100) unsigned NOT NULL AUTO_INCREMENT,
  `parent` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `balance` decimal(12,6) DEFAULT NULL,
  `id_md5` varchar(32) COLLATE utf8_bin NOT NULL,
  `id_short` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `all_eth` decimal(12,4) unsigned NOT NULL DEFAULT '0.0000',
  `win_rate` int(11) NOT NULL DEFAULT '40',
  `play_sum` decimal(12,6) DEFAULT NULL,
  `child_sum` int(10) unsigned NOT NULL DEFAULT '0',
  `recharge_sum` decimal(12,6) DEFAULT NULL,
  `withdraw_sum` decimal(12,6) DEFAULT NULL,
  `is_machine` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=238683 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;

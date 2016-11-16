/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : mocontact

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2016-11-16 15:11:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `contacts`
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) NOT NULL,
  `csex` varchar(1) NOT NULL,
  `cphone` varchar(20) DEFAULT '-',
  `ctel` varchar(20) DEFAULT '-',
  `cemail` varchar(30) DEFAULT '-',
  `cqq` varchar(20) DEFAULT '-',
  `cwork` varchar(50) DEFAULT '-',
  `caddress` varchar(50) DEFAULT '-',
  `gid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO contacts VALUES ('1', '王聪', '男', '18833827200', '-', 'wangcong9614@hotmail.com', '305037115', '天津大学仁爱学院', '天津市静海县天津大学仁爱学院', '0', '1');
INSERT INTO contacts VALUES ('2', '张晓宇', '女', '13116152536', '-', 'kleineweltzhang@hotmail.com', '465815453', '当当网', '北京市', '0', '1');
INSERT INTO contacts VALUES ('9', '12', '男', '123', '123', '123', '123', '1', '1', '1', '1');
INSERT INTO contacts VALUES ('10', '测试', '女', '123', '2', '2', '2', '2', '2', '0', '8');
INSERT INTO contacts VALUES ('11', '111', '男', '', '', '', '', '', '', '2', '8');
INSERT INTO contacts VALUES ('12', '1111', '男', '123', '123', '', '', '', '', '0', '12');
INSERT INTO contacts VALUES ('13', 'aaaaa', '男', '', '', '', '', '', '', '0', '12');
INSERT INTO contacts VALUES ('17', 'tttttt', '男', '', '', 'w', '1', 'w', 'w', '0', '13');

-- ----------------------------
-- Table structure for `groups`
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(20) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO groups VALUES ('0', '未分组', null);
INSERT INTO groups VALUES ('1', '家人', '1');
INSERT INTO groups VALUES ('2', '朋友', '8');
INSERT INTO groups VALUES ('6', '22222', '10');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `realname` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO users VALUES ('1', 'aaa', '123456789', '王聪');
INSERT INTO users VALUES ('8', '111', '123456789', '1');
INSERT INTO users VALUES ('10', 'test', '123456789', '王聪');
INSERT INTO users VALUES ('11', 'qqq', '123456789', '王聪');
INSERT INTO users VALUES ('12', 'lll', '123456789', '王聪');
INSERT INTO users VALUES ('13', 'v', '12345678', '一');

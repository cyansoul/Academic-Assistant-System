/*
Navicat MySQL Data Transfer

Source Server         : worker
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2016-12-14 23:02:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `messageboard`
-- ----------------------------
DROP TABLE IF EXISTS `messageboard`;
CREATE TABLE `messageboard` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `context` varchar(1000) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `accepter` bigint(20) DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `buildDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messageboard
-- ----------------------------
INSERT INTO `messageboard` VALUES ('1', 'aa', 'test', null, '3', '2', '1', '2016-12-11 00:00:00');
INSERT INTO `messageboard` VALUES ('3', 'aa', 'this is a test', '1', '2', '3', '1', '2016-12-12 00:00:00');
INSERT INTO `messageboard` VALUES ('4', 'aa', 'This is a test again', '1', '2', '3', '1', '2016-12-12 00:34:41');
INSERT INTO `messageboard` VALUES ('7', 'Hello Teach', 'this is a test', null, '2', '3', '1', '2016-12-12 00:56:17');
INSERT INTO `messageboard` VALUES ('8', 'hello', 'hello', null, '4', '2', '1', '2016-12-12 01:02:45');
INSERT INTO `messageboard` VALUES ('9', 'Today', 'cetcetcece', null, '4', '2', '1', '2016-12-12 01:03:05');
INSERT INTO `messageboard` VALUES ('10', 'test', 'test', null, '4', '2', '1', '2016-12-12 01:03:28');
INSERT INTO `messageboard` VALUES ('11', 'aa', 'Hello', '3', '3', '2', '1', '2016-12-12 01:04:39');
INSERT INTO `messageboard` VALUES ('12', 'Hello Teach', 'Hello', '7', '3', '2', '1', '2016-12-12 01:04:47');
INSERT INTO `messageboard` VALUES ('13', 'Hello Teach', 'hello', '12', '2', '3', '1', '2016-12-12 18:52:55');
INSERT INTO `messageboard` VALUES ('14', 'sddf', 'fdsff', null, '2', '3', '0', '2016-12-14 14:59:13');

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student` bigint(20) NOT NULL,
  `bookingDate` date NOT NULL,
  `professor` bigint(20) NOT NULL,
  `context` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '0:未读;1:已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('1', '2', '2016-12-14', '3', 'aa', '1');
INSERT INTO `schedule` VALUES ('2', '2', '2016-12-01', '3', 'dasd', '1');
INSERT INTO `schedule` VALUES ('3', '2', '2016-12-10', '3', '12am', '0');
INSERT INTO `schedule` VALUES ('4', '2', '2017-01-01', '3', 'today  i will come', '1');
INSERT INTO `schedule` VALUES ('5', '2', '2016-12-23', '3', 'OK', '1');
INSERT INTO `schedule` VALUES ('6', '2', '2016-12-03', '3', 'OK', '1');
INSERT INTO `schedule` VALUES ('7', '2', '2016-12-02', '3', 'sadad', '0');
INSERT INTO `schedule` VALUES ('8', '2', '2016-12-04', '3', 'ada', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(12) DEFAULT NULL COMMENT '1:man;2:woman;3:unknow',
  `major` varchar(50) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `role` int(1) DEFAULT NULL COMMENT '1:教授;2:学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'admin', 'admin', '1999-07-06', 'man', 'IT', 'Koala.jpg', 'admin@qq.com', '1');
INSERT INTO `user` VALUES ('3', 'White', 'aaaaa', '2016-12-01', 'man', 'professor', 'Koala.jpg', 'white@gmail.com', '2');
INSERT INTO `user` VALUES ('4', 'Tome', 'aaaaa', '2016-12-06', 'man', 'major', 'Jellyfish.jpg', 'tom@126.com', '1');
INSERT INTO `user` VALUES ('5', 'lily', 'aaaaa', '2016-12-13', 'man', 'DDD', 'Penguins.jpg', '123@126.com', '2');
INSERT INTO `user` VALUES ('6', 'juju', 'aaaaa', '2016-12-14', 'man', 'jjj', 'Chrysanthemum.jpg', 'aaaa@124.com', '0');

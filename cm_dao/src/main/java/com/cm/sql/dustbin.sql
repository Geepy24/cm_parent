/*
 Navicat MySQL Data Transfer

 Source Server         : link
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : cm

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 12/01/2019 10:29:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dustbin
-- ----------------------------
DROP TABLE IF EXISTS `dustbin`;
CREATE TABLE `dustbin` (
  `dust_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `art_id` bigint(32) NOT NULL,
  `art_title` varchar(50) NOT NULL,
  `art_content` mediumtext NOT NULL,
  `del_time` date NOT NULL,
  `dust_user_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`dust_id`),
  UNIQUE KEY `dust_id` (`dust_id`) USING BTREE,
  KEY `FK5vqpsxtwcus1nly3mfkotbowh` (`dust_user_id`),
  CONSTRAINT `FK5vqpsxtwcus1nly3mfkotbowh` FOREIGN KEY (`dust_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

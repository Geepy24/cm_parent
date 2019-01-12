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

 Date: 12/01/2019 10:28:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `art_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `art_title` varchar(255) NOT NULL,
  `ads_name` varchar(255) NOT NULL,
  `pub_time` date NOT NULL,
  `last_mod` date NOT NULL,
  `art_content` mediumtext NOT NULL,
  `art_user_id` bigint(32) DEFAULT NULL COMMENT '指向user_id',
  PRIMARY KEY (`art_id`),
  UNIQUE KEY `art_id` (`art_id`) USING BTREE,
  KEY `FKlfqpt3lgvd0r6smr71m4a008a` (`art_user_id`),
  CONSTRAINT `FKlfqpt3lgvd0r6smr71m4a008a` FOREIGN KEY (`art_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

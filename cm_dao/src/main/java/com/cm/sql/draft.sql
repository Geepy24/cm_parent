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

 Date: 12/01/2019 10:28:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for draft
-- ----------------------------
DROP TABLE IF EXISTS `draft`;
CREATE TABLE `draft` (
  `dra_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `art_title` varchar(255) NOT NULL,
  `last_mod` date NOT NULL,
  `art_content` mediumtext NOT NULL,
  `dra_user_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`dra_id`),
  UNIQUE KEY `dra_id` (`dra_id`) USING BTREE,
  KEY `FK9hplo3e3e73oih1q1dl4geecm` (`dra_user_id`),
  CONSTRAINT `FK9hplo3e3e73oih1q1dl4geecm` FOREIGN KEY (`dra_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

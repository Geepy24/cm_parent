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

 Date: 12/01/2019 10:29:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for media_preview
-- ----------------------------
DROP TABLE IF EXISTS `media_preview`;
CREATE TABLE `media_preview` (
  `mp_id` int(11) NOT NULL AUTO_INCREMENT,
  `mp_name` varchar(255) DEFAULT NULL,
  `mp_uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

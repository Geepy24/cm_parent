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

 Date: 12/01/2019 10:25:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ads
-- ----------------------------
DROP TABLE IF EXISTS `ads`;
CREATE TABLE `ads` (
  `ads_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `ads_name` varchar(255) NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ads_id`),
  UNIQUE KEY `ads_id` (`ads_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ads
-- ----------------------------
BEGIN;
INSERT INTO `ads` VALUES (1, 'Geepy', 1, 'Geepy');
INSERT INTO `ads` VALUES (2, 'Lam', 2, 'Lam');
INSERT INTO `ads` VALUES (3, 'user', 4, 'user');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

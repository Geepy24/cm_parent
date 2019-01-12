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

 Date: 12/01/2019 10:30:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `gender` varchar(4) NOT NULL,
  `tel` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'Geepy', NULL, '123', 'm', NULL);
INSERT INTO `user` VALUES (2, 'Lam', NULL, '123', 'f', NULL);
INSERT INTO `user` VALUES (4, 'user', 'user', '12345678', 'm', NULL);
INSERT INTO `user` VALUES (5, 'test', 'test', '123', 'f', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

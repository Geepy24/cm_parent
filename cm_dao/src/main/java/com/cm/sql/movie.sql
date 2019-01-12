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

 Date: 12/01/2019 10:29:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `mov_id` int(11) NOT NULL AUTO_INCREMENT,
  `mov_name` varchar(255) DEFAULT NULL,
  `mov_uri` varchar(255) DEFAULT NULL,
  `mp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`mov_id`),
  UNIQUE KEY `UK_qucwjihovjkyiohv4i1gg6x18` (`mp_id`),
  CONSTRAINT `FKgyibhpovrs7qdvfeycfgw0eej` FOREIGN KEY (`mp_id`) REFERENCES `media_preview` (`mp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

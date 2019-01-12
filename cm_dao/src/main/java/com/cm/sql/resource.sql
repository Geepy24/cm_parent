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

 Date: 12/01/2019 10:29:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `res_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `res_tag` varchar(50) NOT NULL COMMENT '标记是图片还是视频',
  `res_com` varchar(50) NOT NULL,
  `pub_time` date NOT NULL,
  `ads_id` bigint(32) NOT NULL,
  `pic_id` int(11) DEFAULT NULL COMMENT '指向pic_id',
  `mov_id` int(11) DEFAULT NULL COMMENT '指向mov_id',
  `res_user_id` bigint(32) DEFAULT NULL COMMENT '指向user_id',
  PRIMARY KEY (`res_id`),
  UNIQUE KEY `res_id` (`res_id`) USING BTREE,
  UNIQUE KEY `UK_2um4iso68rr11oscyp98riifw` (`pic_id`),
  UNIQUE KEY `UK_7vntqvut1lehhq2egy7fqjg5c` (`mov_id`),
  KEY `FKpfymf36xg9ddk5i69k5skv23x` (`res_user_id`),
  CONSTRAINT `FK350myd3qlbg1kp26ysks5rayu` FOREIGN KEY (`mov_id`) REFERENCES `movie` (`mov_id`),
  CONSTRAINT `FK5kd1voldn947jmu6rpjaj0gcv` FOREIGN KEY (`pic_id`) REFERENCES `picture` (`pic_id`),
  CONSTRAINT `FKpfymf36xg9ddk5i69k5skv23x` FOREIGN KEY (`res_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

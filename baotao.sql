/*
 Navicat Premium Data Transfer

 Source Server         : joking
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : baotao

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 17/05/2024 22:39:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `commodity_ID` int(0) NOT NULL,
  `commodity_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `price` int(10) UNSIGNED ZEROFILL NOT NULL,
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`commodity_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (1, 'Bruce Wayne\'s Life', 0000000123, '123');
INSERT INTO `commodity` VALUES (3, '1', 0000000123, 'testtest');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `client_ID` int(0) NOT NULL,
  `commodity_ID` int(0) NOT NULL,
  `order_ID` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `finish` int(0) NOT NULL,
  `count` int(0) NOT NULL,
  `price` int(10) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`order_ID`) USING BTREE,
  INDEX `client_ID`(`client_ID`) USING BTREE,
  INDEX `newspaper_ID`(`commodity_ID`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`client_ID`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`commodity_ID`) REFERENCES `commodity` (`commodity_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (17, 1, 00000000013, 0, 1, 0000000000);
INSERT INTO `orders` VALUES (17, 1, 00000000014, 0, 1, 0000000000);
INSERT INTO `orders` VALUES (17, 1, 00000000015, 1, 2, 0000000132);
INSERT INTO `orders` VALUES (17, 1, 00000000016, 0, 12, 0000000033);
INSERT INTO `orders` VALUES (17, 1, 00000000018, 1, 12, 0000455786);
INSERT INTO `orders` VALUES (17, 3, 00000000020, 0, 1, 0000000123);
INSERT INTO `orders` VALUES (17, 3, 00000000021, 1, 1, 0000000123);
INSERT INTO `orders` VALUES (17, 1, 00000000022, 0, 3, 0000000123);
INSERT INTO `orders` VALUES (53, 1, 00000000023, 0, 1, 0000000123);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `admin` int(1) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_nameOnly`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'root', 1);
INSERT INTO `user` VALUES (2, 'admin', 'secret', 1);
INSERT INTO `user` VALUES (10, '测试1', '测试', 0);
INSERT INTO `user` VALUES (16, '3', '3', 0);
INSERT INTO `user` VALUES (17, '4', '4', 0);
INSERT INTO `user` VALUES (18, '5', '5', 0);
INSERT INTO `user` VALUES (19, '6', '6', 0);
INSERT INTO `user` VALUES (53, 'test', 'test', 0);

SET FOREIGN_KEY_CHECKS = 1;

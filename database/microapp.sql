/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : microapp

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-02-03 14:39:23
*/
CREATE DATABASE microapp;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `C_ID`   INT(12)      NOT NULL,
  `C_NAME` VARCHAR(255) NOT NULL
  COMMENT '活动大类别 1 为手游 2为PC游戏 3为户外活动 4为其他',
  PRIMARY KEY (`C_ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '手游');
INSERT INTO `t_category` VALUES ('2', 'PC游戏');
INSERT INTO `t_category` VALUES ('3', '户外活动');
INSERT INTO `t_category` VALUES ('4', '其他');

-- ----------------------------
-- Table structure for t_category_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_category_detail`;
CREATE TABLE `t_category_detail` (
  `C_ID`          INT(12) NOT NULL,
  `C_CATEGORY_ID` INT(12)      DEFAULT NULL
  COMMENT '所属的大类别',
  `C_NAME`        VARCHAR(255) DEFAULT NULL
  COMMENT '活动小类别名称，例如：王者荣耀',
  PRIMARY KEY (`C_ID`),
  KEY `foreign_category_id` (`C_CATEGORY_ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_category_detail
-- ----------------------------
INSERT INTO `t_category_detail` VALUES ('1', '1', '王者荣耀');
INSERT INTO `t_category_detail` VALUES ('2', '1', 'QQ飞车');
INSERT INTO `t_category_detail` VALUES ('3', '2', '绝地求生');
INSERT INTO `t_category_detail` VALUES ('4', '3', '羽毛球');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `C_ID`           INT(12)   NOT NULL,
  `C_PUBLISHER_ID` INT(12)   NOT NULL
  COMMENT '发布评论用户的id',
  `C_CONTENT`      VARCHAR(255)   DEFAULT NULL
  COMMENT '评论内容',
  `C_EVENT_ID`     INT(12)   NOT NULL
  COMMENT '评论的活动id',
  `C_CREATE_TIME`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`C_ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', '2', '很棒，想参加！', '1', '2018-02-01 23:07:51');
INSERT INTO `t_comment` VALUES ('2', '2', '加油！', '1', '2018-02-03 14:17:17');

-- ----------------------------
-- Table structure for t_event
-- ----------------------------
DROP TABLE IF EXISTS `t_event`;
CREATE TABLE `t_event` (
  `C_ID`             INT(12)       NOT NULL AUTO_INCREMENT,
  `C_TITLE`          VARCHAR(255)  NOT NULL DEFAULT ''
  COMMENT '活动名字',
  `C_TYPE`           INT(1)        NOT NULL DEFAULT '4'
  COMMENT '活动类型，1为手游 2为PC游戏 3为户外活动 4为其他',
  `C_LOCATION`       VARCHAR(255)  NOT NULL
  COMMENT '活动详细地址',
  `C_LATITUDE`       DOUBLE(16, 2) NOT NULL DEFAULT '0.00'
  COMMENT '地址的纬度',
  `C_LONGITUDE`      DOUBLE(16, 2) NOT NULL DEFAULT '0.00'
  COMMENT '地址的经度',
  `C_EXPIRE_TIME`    TIMESTAMP     NULL     DEFAULT NULL
  COMMENT '活动过期时间',
  `C_LIMIT_NUMBER`   INT(12)       NOT NULL DEFAULT '0'
  COMMENT '活动限制人数，0为不限制',
  `C_CONTENT`        VARCHAR(255)  NOT NULL
  COMMENT '活动详细内容',
  `C_PICTURE_URL`    VARCHAR(255)  NOT NULL
  COMMENT '活动图片url',
  `C_PUBLISHER_ID`   INT(12)       NOT NULL
  COMMENT '活动所属用户id',
  `C_ATTENDED_COUNT` INT(12)                DEFAULT '0'
  COMMENT '活动已加入人数',
  `C_LIKE_COUNT`     INT(12)                DEFAULT '0'
  COMMENT '点赞人数',
  `C_COLLECT_COUNT`  INT(12)                DEFAULT '0'
  COMMENT '收藏人数',
  `C_CREATE_TIME`    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '活动创建时间',
  PRIMARY KEY (`C_ID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_event
-- ----------------------------
INSERT INTO `t_event` VALUES ('1', '王者荣耀开黑', '1', '齐友佳苑', '0.00', '0.00', '2018-02-10 23:04:13', '4', '王者荣耀开黑',
                                   'https://pic3.zhimg.com/90/v2-7387bc0d1209a76798888ad035cc5d6e_250x0.jpg', '1', '1',
                              '0', '0', '2018-02-01 23:05:44');

-- ----------------------------
-- Table structure for t_event_att
-- ----------------------------
DROP TABLE IF EXISTS `t_event_att`;
CREATE TABLE `t_event_att` (
  `C_ID`             INT(12)      NOT NULL AUTO_INCREMENT,
  `C_EVENT_ID`       INT(12)      NOT NULL
  COMMENT '参加的活动id',
  `C_ATTEND_USER_ID` INT(12)      NOT NULL
  COMMENT '参加的用户id',
  `C_CREATE_TIME`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `C_CONTRACT_TYPE`  INT(1)       NOT NULL DEFAULT '1'
  COMMENT '联系方式 1 为手机号 2 为微信号 3 为QQ号',
  `C_CONTRACT_NUM`   VARCHAR(255) NOT NULL
  COMMENT '联系方式号码',
  PRIMARY KEY (`C_ID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COMMENT ='活动参加表';

-- ----------------------------
-- Records of t_event_att
-- ----------------------------
INSERT INTO `t_event_att` VALUES ('1', '1', '2', '2018-02-01 23:07:25', '1', '15640879351');

-- ----------------------------
-- Table structure for t_like
-- ----------------------------
DROP TABLE IF EXISTS `t_like`;
CREATE TABLE `t_like` (
  `C_ID`       INT(12) NOT NULL,
  `C_EVENT_ID` INT(12) NOT NULL,
  `C_USER_ID`  INT(12) NOT NULL,
  PRIMARY KEY (`C_ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_like
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `C_ID`          INT(12)   NOT NULL AUTO_INCREMENT,
  `C_USERNAME`    VARCHAR(255)       DEFAULT NULL,
  `C_IMAGE_URL`   VARCHAR(255)       DEFAULT NULL,
  `C_POINT`       INT(12)            DEFAULT '0',
  `C_OPEN_ID`     VARCHAR(255)       DEFAULT NULL,
  `C_CITY`        VARCHAR(255)       DEFAULT NULL,
  `C_COUNTRY`     VARCHAR(255)       DEFAULT NULL,
  `C_PROVINCE`    VARCHAR(255)       DEFAULT NULL,
  `C_GENDER`      INT(1)    NOT NULL DEFAULT '0'
  COMMENT '0为未知 1 为男 2为女',
  `C_CREATE_TIME` TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '用户创建时间',
  PRIMARY KEY (`C_ID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES
  ('1', 'test', 'https://pic3.zhimg.com/90/v2-7387bc0d1209a76798888ad035cc5d6e_250x0.jpg', '0', '', '上海市', '中国', '上海',
   '0', '2018-02-01 23:03:32');
INSERT INTO `t_user` VALUES
  ('2', 'raj', 'https://pic2.zhimg.com/90/v2-c3be227f9ef2b5328b4babfc6d015287_250x0.jpg', '0', '', '上海市', '中国', '上海',
   '0', '2018-02-01 23:06:15');

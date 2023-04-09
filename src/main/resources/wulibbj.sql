/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云1.117.158.138的3307
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : 1.117.158.138:3307
 Source Schema         : wulibbj

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 09/04/2023 17:50:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `article_title` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `article_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `article_view_count` int(11) NULL DEFAULT NULL COMMENT '文章浏览量',
  `article_like_count` int(11) NULL DEFAULT NULL COMMENT '文章点赞量',
  `article_comment_count` int(11) NULL DEFAULT NULL COMMENT '文章评论数量',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `article_img` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章图片',
  `is_deleted` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除0表示未删除，1表示删除',
  `article_category_id` int(11) NULL DEFAULT NULL COMMENT '外键，对应category_id',
  `article_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外键，对应user_id',
  `article_category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类表中对应category_name',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `article_category_id`(`article_category_id`) USING BTREE,
  INDEX `article_user_id`(`article_user_id`) USING BTREE,
  INDEX `created_time`(`created_time`) USING BTREE,
  INDEX `categoryAndCreateTime`(`article_category_id`, `created_time`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`article_category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `article_ibfk_2` FOREIGN KEY (`article_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (15, '有人需要参加2022届ACM吗，求组队？', '大家好，我是黄鑫，想参加2022届ACM，目前一人，语言使用C++，熟练掌握基本的数据结构和算法，只是英文能力稍差，现在还差2个人，求组队', 34, 0, 0, '2021-04-14 00:00:00', '2021-04-14 00:00:00', '', 0, 8, '290059@whut.edu.cn', '竞赛组队');
INSERT INTO `article` VALUES (37, '吴亦凡强奸、聚众淫乱一案宣判「获刑 13 年，附加驱逐出境」，哪些信息值得关注？', '2022年11月25日上午，北京市朝阳区人民法院一审公开宣判被告人吴亦凡强奸、聚众淫乱案，对被告人吴亦凡以强奸罪判处有期徒刑十一年六个月，附加驱逐出境；以聚众淫乱罪判处有期徒刑一年十个月，数罪并罚，决定执行有期徒刑十三年，附加驱逐出境。', 21, 1, 1, '2022-12-07 14:00:12', '2022-12-07 14:00:12', '', 0, 7, '290059@whut.edu.cn', '生活趣事');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，分类表id',
  `category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '考研交流');
INSERT INTO `category` VALUES (2, '找工作交流');
INSERT INTO `category` VALUES (3, '日常学习');
INSERT INTO `category` VALUES (4, '寻物启事');
INSERT INTO `category` VALUES (5, '拼单拼车');
INSERT INTO `category` VALUES (6, '表白墙');
INSERT INTO `category` VALUES (7, '生活趣事');
INSERT INTO `category` VALUES (8, '竞赛组队');

-- ----------------------------
-- Table structure for firstComment
-- ----------------------------
DROP TABLE IF EXISTS `firstComment`;
CREATE TABLE `firstComment`  (
  `first_comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，评论主键',
  `first_comment_article_id` int(11) NOT NULL COMMENT '外键，对应article_id',
  `first_comment_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键，对应user_id',
  `first_comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `first_comment_like_count` int(11) NOT NULL DEFAULT 0 COMMENT '评论点赞数',
  `first_comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '评论的评论数',
  `first_comment_created_time` datetime NULL DEFAULT NULL COMMENT '评论的创建时间',
  PRIMARY KEY (`first_comment_id`) USING BTREE,
  INDEX `first_comment_article_id`(`first_comment_article_id`) USING BTREE,
  INDEX `first_comment_user_id`(`first_comment_user_id`) USING BTREE,
  CONSTRAINT `first_comment_ibfk_1` FOREIGN KEY (`first_comment_article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `first_comment_ibfk_2` FOREIGN KEY (`first_comment_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of firstComment
-- ----------------------------

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，关注主键',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `follow_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关注的用户id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `follow_user_id`(`follow_user_id`) USING BTREE,
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`follow_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of follow
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，关注主键',
  `from_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送消息用户id',
  `to_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收消息用户id',
  `conversion_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会话id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `status` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '状态，0：未读 1:已读 2：撤回',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `from_id`(`from_id`) USING BTREE,
  INDEX `to_id`(`to_id`) USING BTREE,
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 345 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (341, '1', '290059@whut.edu.cn', 'like', '{\"entityType\":\"article\",\"entityId\":\"37\",\"userId\":\"290059@whut.edu.cn\"}', 0, '2023-04-06 14:17:24');
INSERT INTO `message` VALUES (342, '1', '290059@whut.edu.cn', 'like', '{\"entityType\":\"article\",\"entityId\":\"37\",\"userId\":\"290059@whut.edu.cn\"}', 0, '2023-04-06 22:25:04');
INSERT INTO `message` VALUES (343, '1', '290059@whut.edu.cn', 'like', '{\"entityType\":\"article\",\"entityId\":\"15\",\"userId\":\"290059@whut.edu.cn\"}', 0, '2023-04-06 14:25:06');
INSERT INTO `message` VALUES (344, '1', '290059@whut.edu.cn', 'like', '{\"entityType\":\"article\",\"entityId\":\"37\",\"userId\":\"290059@whut.edu.cn\"}', 0, '2023-04-06 22:25:11');

-- ----------------------------
-- Table structure for sonComment
-- ----------------------------
DROP TABLE IF EXISTS `sonComment`;
CREATE TABLE `sonComment`  (
  `son_comment_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键，评论主键',
  `son_comment_parent_id` bigint(20) NULL DEFAULT NULL COMMENT '评论的父级评论id，默认为null',
  `son_comment_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键，对应user_id',
  `son_comment_reply_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外键，对应回复用户的id',
  `son_comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `son_comment_like_count` int(11) NOT NULL DEFAULT 0 COMMENT '评论点赞数',
  `son_comment_created_time` datetime NULL DEFAULT NULL COMMENT '评论的创建时间',
  PRIMARY KEY (`son_comment_id`) USING BTREE,
  INDEX `son_comment_parent_id`(`son_comment_parent_id`) USING BTREE,
  INDEX `son_comment_user_id`(`son_comment_user_id`) USING BTREE,
  INDEX `son_comment_reply_user_id`(`son_comment_reply_user_id`) USING BTREE,
  CONSTRAINT `son_comment_ibfk_1` FOREIGN KEY (`son_comment_parent_id`) REFERENCES `firstComment` (`first_comment_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `son_comment_ibfk_2` FOREIGN KEY (`son_comment_reply_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `son_comment_ibfk_3` FOREIGN KEY (`son_comment_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sonComment
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学校邮箱',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加强密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `sex` int(1) NULL DEFAULT 1 COMMENT '用户性别',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '系统', '116165161631wdad', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES ('123456@whut.edu.cn', 'user_123456', '123456', NULL, 'http://qiniu.yangxiaobai.top/avatar/defaultAvatar.webp', '2023-02-22 14:39:02', 1);
INSERT INTO `user` VALUES ('290059@whut.edu.cn', '杨小白', 'yangxiaozhuo123', '40b1ddae93b4569110a7bf2bd80e75b1', 'http://qiniu.yangxiaobai.top/avatar/2023/04/sknir8c8k7.jpg', '2022-11-25 21:01:39', 0);

-- ----------------------------
-- Table structure for uvCount
-- ----------------------------
DROP TABLE IF EXISTS `uvCount`;
CREATE TABLE `uvCount`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键，关注主键',
  `count` int(20) NOT NULL DEFAULT 0 COMMENT '当日访问量',
  `day` date NOT NULL COMMENT '每日更新时间',
  `remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of uvCount
-- ----------------------------
INSERT INTO `uvCount` VALUES (1, 1969, '1111-11-11', '总访问量');
INSERT INTO `uvCount` VALUES (31, 213, '2023-01-12', '');
INSERT INTO `uvCount` VALUES (53, 116, '2023-04-06', '');
INSERT INTO `uvCount` VALUES (54, 2, '2023-04-07', '');
INSERT INTO `uvCount` VALUES (55, 239, '2023-04-08', '');

SET FOREIGN_KEY_CHECKS = 1;

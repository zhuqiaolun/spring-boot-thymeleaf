/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : spring-boot-thymeleaf

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-07-28 10:21:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `menu_name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) DEFAULT NULL COMMENT '菜单url（Controller 请求路径）',
  `menu_type` int(4) DEFAULT '1' COMMENT '1-模块，2-菜单，3-操作',
  `menu_pid` int(11) NOT NULL DEFAULT '1' COMMENT '父ID',
  `menu_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `menu_createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `menu_sort` int(11) DEFAULT '1' COMMENT '排序',
  `menu_enable` tinyint(4) DEFAULT '0' COMMENT '可用',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', '系统首页', '/main', '2', '0', '系统首页', '2020-07-23 13:42:46', '1', '1');
INSERT INTO `system_menu` VALUES ('3', '用户列表', '/user', '2', '0', '用户管理下的菜单', '2020-07-23 13:42:49', '1', '1');
INSERT INTO `system_menu` VALUES ('5', '角色列表', '/role1', '2', '0', '用户管理下的菜单', '2020-07-23 13:46:08', '1', '1');
INSERT INTO `system_menu` VALUES ('6', '新增', '/user/add', '3', '3', '用户列表下的操作', '2020-07-23 13:47:56', '1', '1');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', 'ADMIN');
INSERT INTO `system_role` VALUES ('2', 'USER');

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu` (
  `rm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  `menu_id` int(11) DEFAULT NULL COMMENT '模块表id',
  PRIMARY KEY (`rm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='权限菜单表';

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES ('1', '1', '1');
INSERT INTO `system_role_menu` VALUES ('3', '1', '3');
INSERT INTO `system_role_menu` VALUES ('5', '1', '5');
INSERT INTO `system_role_menu` VALUES ('6', '1', '6');
INSERT INTO `system_role_menu` VALUES ('7', '2', '1');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', 'bdfe376073c04d768c9191e2633bebb5', '超级管理员', '2020-07-20 13:37:05');
INSERT INTO `system_user` VALUES ('2', 'test', 'bdfe376073c04d768c9191e2633bebb5', '测试员1', '2020-07-20 13:37:05');

-- ----------------------------
-- Table structure for system_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `system_user_roles`;
CREATE TABLE `system_user_roles` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户表id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of system_user_roles
-- ----------------------------
INSERT INTO `system_user_roles` VALUES ('1', '1', '1');
INSERT INTO `system_user_roles` VALUES ('2', '2', '2');

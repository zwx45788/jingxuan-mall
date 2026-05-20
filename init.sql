
CREATE DATABASE IF NOT EXISTS `shopping_mall`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

USE `shopping_mall`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL COMMENT '用户ID' AUTO_INCREMENT, 
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `user_address` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `province` varchar(255) NOT NULL COMMENT '省份',
    `city` varchar(255) NOT NULL COMMENT '城市',
    `district` varchar(255) NOT NULL COMMENT '区域',
    `detail` varchar(255) NOT NULL COMMENT '详细地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户地址表';

CREATE TABLE IF NOT EXISTS `merchant` (
  `id` bigint NOT NULL COMMENT '商户ID' AUTO_INCREMENT, 
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商户表';


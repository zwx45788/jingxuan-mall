
CREATE DATABASE IF NOT EXISTS `shopping_mall`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

USE `shopping_mall`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL COMMENT '用户ID' AUTO_INCREMENT, 
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `user_address` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `province` varchar(255) NOT NULL COMMENT '省份',
    `city` varchar(255) NOT NULL COMMENT '城市',
    `district` varchar(255) NOT NULL COMMENT '区域',
    `detail` varchar(255) NOT NULL COMMENT '详细地址',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户地址表';

CREATE TABLE IF NOT EXISTS `merchant` (
  `id` bigint NOT NULL COMMENT '商户ID' AUTO_INCREMENT, 
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商户表';

CREATE TABLE IF NOT EXISTS `shop` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `merchant_id` BIGINT NOT NULL COMMENT '所属商户ID',
  `shop_name` VARCHAR(128) NOT NULL COMMENT '店铺名称',
  `description` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '店铺描述',
  `logo` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '店铺Logo',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '店铺状态',
  `score` DECIMAL(5,2) NOT NULL DEFAULT 5.00 COMMENT '店铺评分',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='店铺';

CREATE TABLE IF NOT EXISTS `product_spu` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'SPU ID',
  `shop_id` BIGINT NOT NULL COMMENT '店铺ID',
  `category_id` BIGINT NOT NULL COMMENT '类目ID',
  `title` VARCHAR(255) NOT NULL COMMENT '标题',
  `main_image` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '主图',
  `status` TINYINT NOT NULL DEFAULT 2 COMMENT '商品状态（2=已上架）',
  `audit_status` TINYINT NOT NULL DEFAULT 1 COMMENT '审核状态（1=已通过）',
  `min_price` BIGINT NOT NULL DEFAULT 0 COMMENT '最低价（分）',
  `max_price` BIGINT NOT NULL DEFAULT 0 COMMENT '最高价（分）',
  `total_stock` BIGINT NOT NULL DEFAULT 0 COMMENT '总库存',
  `sales_volume` BIGINT NOT NULL DEFAULT 0 COMMENT '销量',
  `comment_count` BIGINT NOT NULL DEFAULT 0 COMMENT '评论数',
  `positive_rate` DECIMAL(5,2) NOT NULL DEFAULT 100.00 COMMENT '好评率',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SPU';

-- 商品 SKU
CREATE TABLE IF NOT EXISTS `product_sku` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'SKU ID',
  `product_id` BIGINT NOT NULL COMMENT '所属SPU ID',
  #`sku_code` VARCHAR(64) NOT NULL COMMENT 'SKU编码',
  `spec_json` JSON NOT NULL COMMENT '规格属性JSON',
  `price` BIGINT NOT NULL COMMENT '售价（分）',
  `image` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '图片',
  `stock` BIGINT NOT NULL DEFAULT 0 COMMENT '库存',
  `locked_stock` BIGINT NOT NULL DEFAULT 0 COMMENT '锁定库存',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  #UNIQUE KEY `uk_sku_code` (`sku_code`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SKU';

CREATE TABLE IF NOT EXISTS `category` (
  `id` BIGINT NOT NULL COMMENT '类目ID（雪花）',
  `parent_id` BIGINT NOT NULL DEFAULT 0 COMMENT '父类目ID，0表示顶级',
  `name` VARCHAR(64) NOT NULL COMMENT '类目名称',
  `level` TINYINT NOT NULL DEFAULT 1 COMMENT '层级',
  `sort_no` INT NOT NULL DEFAULT 0 COMMENT '排序号',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品类目';

-- 购物车项
CREATE TABLE IF NOT EXISTS `cart_item` (
  `id` BIGINT NOT NULL COMMENT '主键（雪花）',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
  `quantity` INT NOT NULL DEFAULT 1 COMMENT '数量',
  `checked` TINYINT NOT NULL DEFAULT 1 COMMENT '是否勾选',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_user_sku` (`user_id`, `sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='购物车';


-- 订单主表
CREATE TABLE IF NOT EXISTS `order_info` (
  `id` BIGINT NOT NULL COMMENT '订单ID（雪花）',
  `order_no` VARCHAR(64) NOT NULL COMMENT '订单号',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `shop_id` BIGINT NOT NULL COMMENT '店铺ID',
  `order_status` TINYINT NOT NULL COMMENT '订单状态',
  `source` VARCHAR(32) NOT NULL DEFAULT 'APP' COMMENT '订单来源',
  `total_amount` BIGINT NOT NULL COMMENT '订单总额（分）',
  `discount_amount` BIGINT NOT NULL DEFAULT 0 COMMENT '优惠金额（分）',
  `pay_amount` BIGINT NOT NULL COMMENT '实付金额（分）',
  `pay_status` TINYINT NOT NULL DEFAULT 0 COMMENT '支付状态',
  `consignee_name` VARCHAR(64) NOT NULL COMMENT '收货人',
  `consignee_mobile` VARCHAR(20) NOT NULL COMMENT '收货电话',
  `address_snapshot` VARCHAR(512) NOT NULL COMMENT '地址快照',
  `remark` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
  `pay_time` DATETIME DEFAULT NULL COMMENT '支付时间',
  `delivery_time` DATETIME DEFAULT NULL COMMENT '发货时间',
  `receive_time` DATETIME DEFAULT NULL COMMENT '收货时间',
  `finished_time` DATETIME DEFAULT NULL COMMENT '完成时间',
  `cancel_time` DATETIME DEFAULT NULL COMMENT '取消时间',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单';

-- 订单明细
CREATE TABLE IF NOT EXISTS `order_item` (
  `id` BIGINT NOT NULL COMMENT '子单ID（雪花）',
  `order_id` BIGINT NOT NULL COMMENT '订单ID',
  `order_no` VARCHAR(64) NOT NULL COMMENT '订单号冗余',
  `product_id` BIGINT NOT NULL COMMENT '商品SPU ID',
  `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
  `product_title` VARCHAR(255) NOT NULL COMMENT '商品标题快照',
  `sku_spec_json` JSON NOT NULL COMMENT '规格快照',
  `unit_price` BIGINT NOT NULL COMMENT '单价（分）',
  `quantity` INT NOT NULL COMMENT '数量',
  `total_amount` BIGINT NOT NULL COMMENT '小计金额（分）',
  `refund_status` TINYINT NOT NULL DEFAULT 0 COMMENT '退款状态',
  `comment_status` TINYINT NOT NULL DEFAULT 0 COMMENT '评论状态',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单明细';
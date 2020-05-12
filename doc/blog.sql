-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 blog.t_blog 结构
CREATE TABLE IF NOT EXISTS `t_blog`
(
    `id`           bigint(20)    NOT NULL AUTO_INCREMENT COMMENT '博客id',
    `title`        varchar(200)  NOT NULL COMMENT '博客标题',
    `description`  varchar(1024) NOT NULL COMMENT '描述',
    `image`        varchar(1024)          DEFAULT NULL COMMENT '图片',
    `content`      text          NOT NULL COMMENT '博客内容',
    `raw_data`     text          NOT NULL COMMENT '原始输入数据',
    `class_id`     bigint(20)    NOT NULL COMMENT '分类id',
    `status`       tinyint(1)    NOT NULL DEFAULT '0' COMMENT '是否发布(1:是, 0:否)',
    `read_count`   int(11)       NOT NULL DEFAULT '0' COMMENT '阅读次数',
    `release_time` datetime               DEFAULT NULL COMMENT '发布日期',
    `create_time`  datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8 COMMENT ='博客表';

-- 正在导出表  blog.t_blog 的数据：~13 rows (大约)
DELETE
FROM `t_blog`;
/*!40000 ALTER TABLE `t_blog`
    DISABLE KEYS */;
INSERT INTO `t_blog` (`id`, `title`, `description`, `image`, `content`, `raw_data`, `class_id`, `status`, `read_count`,
                      `release_time`, `create_time`, `update_time`)
VALUES (14, '测试',
        'REmote DIctionary Server(Redis) 是一个由Salvatore Sanfilippo写的key-value存储系统。\nRedis是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。\n\n它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Hash), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型。',
        NULL,
        '<h1><a id="Redis_String_0"></a>Redis 字符串(String)</h1>\n<p>Redis 字符串数据类型的相关命令用于管理 redis 字符串值，基本语法如下：</p>\n<h2><a id="_3"></a>语法</h2>\n<p><code>redis 127.0.0.1:6379&gt; COMMAND KEY_NAME</code></p>\n<h2><a id="_5"></a>实例</h2>\n<pre><code class="lang-">redis 127.0.0.1:6379&gt; SET runoobkey redis\nOK\nredis 127.0.0.1:6379&gt; GET runoobkey\n&quot;redis&quot;\n</code></pre>\n<p>在以上实例中我们使用了 SET 和 GET 命令，键为 runoobkey。</p>\n',
        '# Redis 字符串(String)\nRedis 字符串数据类型的相关命令用于管理 redis 字符串值，基本语法如下：\n\n## 语法\n```redis 127.0.0.1:6379> COMMAND KEY_NAME```\n## 实例\n```\nredis 127.0.0.1:6379> SET runoobkey redis\nOK\nredis 127.0.0.1:6379> GET runoobkey\n"redis"\n```\n在以上实例中我们使用了 SET 和 GET 命令，键为 runoobkey。',
        3, 1, 2, '2020-05-12 18:47:10', '2020-05-12 18:27:56', '2020-05-12 18:48:12');
/*!40000 ALTER TABLE `t_blog`
    ENABLE KEYS */;

-- 导出  表 blog.t_class 结构
CREATE TABLE IF NOT EXISTS `t_class`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `parent_id`   bigint(20)   NOT NULL DEFAULT '0' COMMENT '父分类id',
    `class_name`  varchar(200) NOT NULL COMMENT '分类名称',
    `status`      int(11)      NOT NULL DEFAULT '0' COMMENT '状态(1:启用，0:停用)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8 COMMENT ='分类表';

-- 正在导出表  blog.t_class 的数据：~5 rows (大约)
DELETE
FROM `t_class`;
/*!40000 ALTER TABLE `t_class`
    DISABLE KEYS */;
INSERT INTO `t_class` (`id`, `parent_id`, `class_name`, `status`, `create_time`, `update_time`)
VALUES (1, 0, 'Spring', 1, '2020-01-14 16:16:07', '2020-05-12 11:52:15'),
       (2, 1, 'Spring Aop', 1, '2020-01-14 16:16:21', '2020-05-12 11:52:21'),
       (3, 0, 'Redis', 1, '2020-01-14 17:17:37', '2020-05-12 11:52:07'),
       (4, 0, 'Mysql', 0, '2020-05-12 11:52:29', '2020-05-12 14:10:50'),
       (5, 4, 'Mysql索引', 1, '2020-05-12 14:05:36', '2020-05-12 14:10:58');
/*!40000 ALTER TABLE `t_class`
    ENABLE KEYS */;

-- 导出  表 blog.t_comment 结构
CREATE TABLE IF NOT EXISTS `t_comment`
(
    `id`           bigint(20)    NOT NULL AUTO_INCREMENT,
    `blog_id`      bigint(20)    NOT NULL DEFAULT '-1' COMMENT '博客id',
    `comment_type` tinyint(1)    NOT NULL COMMENT '评论类型（1：博客评论 2：友情链接 3：留言板）',
    `content`      varchar(1000) NOT NULL COMMENT '评论内容',
    `user_id`      bigint(20)    NOT NULL COMMENT '评论用户id',
    `create_time`  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `index_blog_id` (`blog_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 37
  DEFAULT CHARSET = utf8 COMMENT ='评论表';

-- 正在导出表  blog.t_comment 的数据：~33 rows (大约)
DELETE
FROM `t_comment`;
/*!40000 ALTER TABLE `t_comment`
    DISABLE KEYS */;
INSERT INTO `t_comment` (`id`, `blog_id`, `comment_type`, `content`, `user_id`, `create_time`, `update_time`)
VALUES (1, 5, 1, '111111', 19, '2020-01-15 17:22:44', '2020-01-15 17:28:34'),
       (2, 5, 1, '111111', 19, '2020-01-15 17:22:44', '2020-01-15 17:28:35'),
       (3, 5, 1, '111111', 19, '2020-01-15 17:23:15', '2020-01-15 17:28:38'),
       (4, 5, 1, '2222', 19, '2020-01-15 17:26:51', '2020-01-15 17:28:40'),
       (5, 5, 1, '3333', 19, '2020-01-15 17:28:47', '2020-01-15 17:28:47'),
       (6, 5, 1, '22222', 19, '2020-01-15 17:29:34', '2020-01-15 17:29:34'),
       (7, 3, 1, '11111', 19, '2020-01-16 09:15:24', '2020-01-16 09:15:24'),
       (8, 13, 1, '1111', 19, '2020-01-16 10:06:29', '2020-01-16 10:06:29'),
       (9, 13, 1, '2321321321321321SSS', 19, '2020-01-16 11:40:20', '2020-01-16 11:40:20'),
       (10, 13, 1, '121212', 19, '2020-01-16 13:56:52', '2020-01-16 13:56:52'),
       (11, 13, 1, '121212', 19, '2020-01-16 13:56:59', '2020-01-16 13:56:59'),
       (12, 13, 1, '3', 19, '2020-01-16 13:57:02', '2020-01-16 13:57:02'),
       (13, 13, 1, '4', 19, '2020-01-16 13:57:04', '2020-01-16 13:57:04'),
       (14, 13, 1, '5', 19, '2020-01-16 13:57:05', '2020-01-16 13:57:05'),
       (15, 13, 1, '6', 19, '2020-01-16 13:57:07', '2020-01-16 13:57:07'),
       (16, 13, 1, '7', 19, '2020-01-16 13:57:08', '2020-01-16 13:57:08'),
       (17, 13, 1, '8', 19, '2020-01-16 13:57:10', '2020-01-16 13:57:10'),
       (18, 13, 1, '9', 19, '2020-01-16 13:57:12', '2020-01-16 13:57:12'),
       (19, 13, 1, '10[可爱][可怜][挖鼻][吃惊]', 19, '2020-01-16 13:59:03', '2020-01-16 13:59:03'),
       (20, 13, 1, '11', 19, '2020-01-16 13:59:26', '2020-01-16 13:59:26'),
       (21, 13, 1, '12', 19, '2020-01-16 13:59:38', '2020-01-16 13:59:38'),
       (22, 4, 1, '1111', 19, '2020-01-16 14:37:12', '2020-01-16 14:37:12'),
       (23, 10, 1, '111', 19, '2020-01-16 14:49:50', '2020-01-16 14:49:50'),
       (24, 10, 1, '222222', -1, '2020-01-16 14:54:24', '2020-01-16 14:54:24'),
       (25, -1, 3, '111', -1, '2020-01-16 15:02:29', '2020-01-16 15:02:29'),
       (26, -1, 4, '222', -1, '2020-01-16 15:03:55', '2020-01-16 15:03:55'),
       (27, 5, 1, '[嘻嘻]', -1, '2020-01-16 16:51:55', '2020-01-16 16:51:55'),
       (28, 5, 1, '[可怜]', -1, '2020-01-16 17:08:34', '2020-01-16 17:08:34'),
       (29, 5, 1, '[害羞]', -1, '2020-01-16 17:08:40', '2020-01-16 17:08:40'),
       (30, 5, 1, '[吃惊]', -1, '2020-01-16 17:08:51', '2020-01-16 17:08:51'),
       (31, 5, 1, '555', -1, '2020-01-16 18:01:55', '2020-01-16 18:01:55'),
       (32, 5, 1, '6666', -1, '2020-01-16 18:01:57', '2020-01-16 18:01:57'),
       (33, 5, 1, '777', -1, '2020-01-16 18:01:58', '2020-01-16 18:01:58'),
       (34, -1, 3, '[左哼哼][嘘][衰][吐]', -1, '2020-01-17 11:25:47', '2020-01-17 11:25:47'),
       (35, 6, 1, '[嘻嘻]', 19, '2020-01-17 11:40:37', '2020-01-17 11:40:37'),
       (36, 10, 1, '[微笑]', -1, '2020-01-17 11:41:15', '2020-01-17 11:41:15');
/*!40000 ALTER TABLE `t_comment`
    ENABLE KEYS */;

-- 导出  表 blog.t_like 结构
CREATE TABLE IF NOT EXISTS `t_like`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `blog_id`        bigint(20) NOT NULL COMMENT '博客id',
    `user_id`        bigint(20) NOT NULL COMMENT '用户id',
    `like_status`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '点赞状态(1:有效赞；0：取消赞)',
    `collect_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '收藏状态(1:已收藏；0：为收藏)',
    `create_time`    timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `index_blog_id` (`blog_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8 COMMENT ='点赞表';

-- 正在导出表  blog.t_like 的数据：~4 rows (大约)
DELETE
FROM `t_like`;
/*!40000 ALTER TABLE `t_like`
    DISABLE KEYS */;
INSERT INTO `t_like` (`id`, `blog_id`, `user_id`, `like_status`, `collect_status`, `create_time`, `update_time`)
VALUES (1, 2, 19, 1, 0, '2020-01-15 14:34:47', '2020-01-15 14:34:47'),
       (2, 1, 19, 0, 0, '2020-01-16 18:23:01', '2020-01-17 10:44:57'),
       (3, 5, 19, 1, 1, '2020-01-17 11:24:43', '2020-01-17 11:24:44'),
       (4, 4, 19, 1, 0, '2020-01-17 11:36:51', '2020-01-17 11:40:19'),
       (5, 6, 19, 0, 1, '2020-01-17 11:40:29', '2020-01-17 11:40:29');
/*!40000 ALTER TABLE `t_like`
    ENABLE KEYS */;

-- 导出  表 blog.t_reply 结构
CREATE TABLE IF NOT EXISTS `t_reply`
(
    `id`           bigint(20)    NOT NULL AUTO_INCREMENT,
    `comment_id`   bigint(20)    NOT NULL COMMENT '评论id',
    `reply_type`   tinyint(1)    NOT NULL COMMENT '回复类型(1:评论的回复，2: 回复的回复)',
    `content`      varchar(1000) NOT NULL COMMENT '回复内容',
    `reply_id`     bigint(20)    NOT NULL COMMENT '回复目标id',
    `from_user_id` bigint(20)    NOT NULL COMMENT '回复用户id',
    `to_user_id`   bigint(20)    NOT NULL COMMENT '目标用户id',
    `create_time`  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8 COMMENT ='回复表';

-- 正在导出表  blog.t_reply 的数据：~6 rows (大约)
DELETE
FROM `t_reply`;
/*!40000 ALTER TABLE `t_reply`
    DISABLE KEYS */;
INSERT INTO `t_reply` (`id`, `comment_id`, `reply_type`, `content`, `reply_id`, `from_user_id`, `to_user_id`,
                       `create_time`, `update_time`)
VALUES (1, 8, 1, '22222', 8, 19, 19, '2020-01-16 10:14:07', '2020-01-16 10:14:07'),
       (2, 8, 2, '3333', 1, 19, 19, '2020-01-16 10:18:55', '2020-01-16 10:18:55'),
       (3, 8, 1, 'TEST', 8, 19, 19, '2020-01-16 11:41:09', '2020-01-16 11:41:09'),
       (4, 8, 2, '[嘻嘻]', 1, 19, 19, '2020-01-16 11:44:35', '2020-01-16 11:44:35'),
       (5, 8, 2, '[可爱]', 2, 19, 19, '2020-01-16 11:51:59', '2020-01-16 11:51:59'),
       (6, 1, 1, '1111', 1, 19, 19, '2020-01-16 14:05:17', '2020-01-16 14:05:17');
/*!40000 ALTER TABLE `t_reply`
    ENABLE KEYS */;

-- 导出  表 blog.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user`
(
    `id`                   bigint(20)   NOT NULL AUTO_INCREMENT,
    `username`             varchar(50)  NOT NULL COMMENT '用户名/昵称',
    `email`                varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
    `password`             varchar(100) NOT NULL COMMENT '密码',
    `sex`                  tinyint(1)   NOT NULL DEFAULT '1' COMMENT '性别  0：女   1：男',
    `tags`                 varchar(200) NOT NULL DEFAULT '' COMMENT '标签',
    `status`               tinyint(1)   NOT NULL DEFAULT '0' COMMENT '状态(0:未激活;1:已激活)',
    `show_flag`            tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否展示友联(0:否;1:是)',
    `head_photo`           varchar(500) NOT NULL DEFAULT '' COMMENT '头像',
    `website_name`         varchar(200) NOT NULL DEFAULT '' COMMENT '网站名称',
    `website_address`      varchar(300) NOT NULL DEFAULT '' COMMENT '网站地址',
    `website_introduction` varchar(500) NOT NULL DEFAULT '' COMMENT '网站简介',
    `website_logo`         varchar(500) NOT NULL DEFAULT '' COMMENT '网站logo',
    `activation_code`      varchar(200)          DEFAULT NULL COMMENT '激活码',
    `create_time`          timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`          timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `index_email` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

-- 正在导出表  blog.t_user 的数据：~1 rows (大约)
DELETE
FROM `t_user`;
/*!40000 ALTER TABLE `t_user`
    DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `username`, `email`, `password`, `sex`, `tags`, `status`, `show_flag`, `head_photo`,
                      `website_name`, `website_address`, `website_introduction`, `website_logo`, `activation_code`,
                      `create_time`, `update_time`)
VALUES (19, 'liuzw', 'liuzewei@zhilingsd.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '码农,萌萌哒,技术宅', 1, 1, '', 'liuzw',
        'www.liuzw.com', 'www.liuzw.com', '', '0b1c1958cf144c07a3219c02ab9b6ce1', '2020-01-14 15:20:04',
        '2020-01-20 15:52:52');
/*!40000 ALTER TABLE `t_user`
    ENABLE KEYS */;

/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;

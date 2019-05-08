-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 blog 的数据库结构
CREATE DATABASE IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blog`;

-- 导出  表 blog.t_blog 结构
CREATE TABLE IF NOT EXISTS `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `title` varchar(200) NOT NULL COMMENT '博客标题',
  `content` text NOT NULL COMMENT '博客内容',
  `raw_data` text NOT NULL COMMENT '原始输入数据',
  `class_id` bigint(20) NOT NULL COMMENT '分类id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否发布(1:是, 0:否)',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布日期',
  `read_count` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客表';

-- 正在导出表  blog.t_blog 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;

-- 导出  表 blog.t_blog_class 结构
CREATE TABLE IF NOT EXISTS `t_blog_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `class_name` varchar(200) NOT NULL COMMENT '分类名称',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态(1:启用，0:停用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客分类表';

-- 正在导出表  blog.t_blog_class 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_blog_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_blog_class` ENABLE KEYS */;

-- 导出  表 blog.t_comment 结构
CREATE TABLE IF NOT EXISTS `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `comment_type` tinyint(1) NOT NULL COMMENT '评论类型（1：博客评论 2：友情链接 3：留言板）',
  `content` varchar(1000) NOT NULL COMMENT '评论内容',
  `user_id` bigint(20) NOT NULL COMMENT '评论用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_blog_id` (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- 正在导出表  blog.t_comment 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;

-- 导出  表 blog.t_like 结构
CREATE TABLE IF NOT EXISTS `t_like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `like_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '点赞状态(1:有效赞；0：取消赞)',
  `collect_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '收藏状态(1:已收藏；0：为收藏)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_blog_id` (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞表';

-- 正在导出表  blog.t_like 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_like` ENABLE KEYS */;

-- 导出  表 blog.t_reply 结构
CREATE TABLE IF NOT EXISTS `t_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL COMMENT '评论id',
  `reply_type` tinyint(1) NOT NULL COMMENT '回复类型(1:评论的回复，2: 回复的回复)',
  `content` varchar(1000) NOT NULL COMMENT '回复内容',
  `reply_id` bigint(20) NOT NULL COMMENT '回复目标id',
  `from_user_id` bigint(20) NOT NULL COMMENT '回复用户id',
  `to_user_id` bigint(20) NOT NULL COMMENT '目标用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

-- 正在导出表  blog.t_reply 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_reply` ENABLE KEYS */;

-- 导出  表 blog.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名/昵称',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `sex` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别  0：女   1：男',
  `tags` varchar(200) NOT NULL DEFAULT '' COMMENT '标签',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0:未激活;1:已激活)',
  `show_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否展示友联(0:否;1:是)',
  `head_photo` varchar(500) NOT NULL DEFAULT '' COMMENT '头像',
  `website_name` varchar(200) NOT NULL DEFAULT '' COMMENT '网站名称',
  `website_address` varchar(300) NOT NULL DEFAULT '' COMMENT '网站地址',
  `website_introduction` varchar(500) NOT NULL DEFAULT '' COMMENT '网站简介',
  `website_logo` varchar(500) NOT NULL DEFAULT '' COMMENT '网站logo',
  `activation_code` varchar(200) DEFAULT NULL COMMENT '激活码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  blog.t_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `username`, `email`, `password`, `sex`, `tags`, `status`, `show_flag`, `head_photo`, `website_name`, `website_address`, `website_introduction`, `website_logo`, `activation_code`, `create_time`, `update_time`) VALUES
	(1, 'liuzw', '670115782@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '', 1, 0, '  ', '', '', '', '', NULL, '2019-04-29 15:32:24', '2019-04-29 17:49:22'),
	(13, '123132', 'liuzewei@zhilingsd.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '', 0, 0, '', '', '', '', '', '30c388a329e641bb804c5e460daf0401', '2019-04-30 18:37:33', '2019-05-06 03:27:01');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

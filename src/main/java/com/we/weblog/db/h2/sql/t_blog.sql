CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客类型',
  `title` varchar(200) NOT NULL COMMENT '博客题目',
  `summary` varchar(400) DEFAULT NULL COMMENT '博客摘要',
  `releaseDate` datetime DEFAULT NULL COMMENT '发布日期',
  `clickHit` int(11) DEFAULT NULL COMMENT '评论次数',
  `replyHit` int(11) DEFAULT NULL COMMENT '回复次数',
  `content` text COMMENT '博客内容',
  `keyWord` varchar(200) DEFAULT NULL COMMENT '关键字',
  `type_id` int(11) DEFAULT NULL COMMENT '外键关联博客类别',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `t_blog_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `t_blogtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;


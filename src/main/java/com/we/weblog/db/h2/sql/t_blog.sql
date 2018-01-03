CREATE TABLE `t_blog` (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `date` date DEFAULT NULL,
  `article` text NOT NULL,
  `md` text NOT NULL,
  `tags` varchar(80) NOT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

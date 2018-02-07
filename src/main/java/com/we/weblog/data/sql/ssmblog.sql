/*
Navicat MySQL Data Transfer

Source Server         : blogSQL
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : ssmblog

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-02-01 15:20:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date` date DEFAULT NULL,
  `article` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `md` text NOT NULL,
  `tags` varchar(80) NOT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('3', '2016博客1', '2016-09-09', '111111', '2016博客1', '');
INSERT INTO `t_blog` VALUES ('4', '2016博客4', '2016-08-16', '33333', '2016博客一', '');
INSERT INTO `t_blog` VALUES ('5', '2016博客3', '2016-09-09', '222222', '2016博客2', '');
INSERT INTO `t_blog` VALUES ('6', 'title', '2005-01-28', 'article', 'md', 'tags');
INSERT INTO `t_blog` VALUES ('7', '12312', '2018-01-04', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><h2 id=\"h2-thx-for-my-first-test\"><a name=\"thx for my first test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>thx for my first test</h2>', '#High this is a test\r\n\r\n##thx for my first test', '1111');
INSERT INTO `t_blog` VALUES ('8', 'tst tiet', '2018-01-23', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><h2 id=\"h2-again-i-m-coming\"><a name=\"Again I’m coming\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Again I’m coming</h2><ul>\r\n<li>1111</li><li>2222</li><li>3333</li></ul>\r\n', '#High this is a test\r\n\r\n\r\n##Again I\'m coming\r\n- 1111\r\n- 2222\r\n- 3333', '23');
INSERT INTO `t_blog` VALUES ('18', '中文', '2018-01-09', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><h2 id=\"h2--again\"><a name=\"中文again\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>中文again</h2>', '#High this is a test\r\n##中文again', '中文');
INSERT INTO `t_blog` VALUES ('19', '博客系统', '2018-01-09', '<h1 id=\"h1-u8FD9u6B21u6765u4E2Au5E05u6C14u7684u663Eu793A\"><a name=\"这次来个帅气的显示\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>这次来个帅气的显示</h1><h3 id=\"h3-u6807u9898u4E8C\"><a name=\"标题二\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题二</h3><pre><code>\r\nalert(1)\r\nprint(\"you are so handsom\")\r\n\r\nvoid main (){\r\nsadasdasd\r\n}\r\n</code></pre><table>\r\n<thead>\r\n<tr>\r\n<th>4</th>\r\n<th>2</th>\r\n<th>3</th>\r\n<th>5</th>\r\n</tr>\r\n</thead>\r\n<tbody>\r\n<tr>\r\n<td>1</td>\r\n<td>3</td>\r\n<td>2</td>\r\n<td>7 6</td>\r\n</tr>\r\n<tr>\r\n<td>2</td>\r\n<td>2</td>\r\n<td>3</td>\r\n<td>7</td>\r\n</tr>\r\n<tr>\r\n<td>1</td>\r\n<td>2</td>\r\n<td>2</td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n', '#这次来个帅气的显示\r\n\r\n\r\n### 标题二\r\n\r\n\r\n```\r\n\r\nalert(1)\r\nprint(\"you are so handsom\")\r\n\r\nvoid main (){\r\nsadasdasd\r\n}\r\n```\r\n|  4 |  2 | 3  |  5 |\r\n| ------------ | ------------ | ------------ | ------------ |\r\n|   1| 3  | 2  | 7 6|\r\n|  2 |  2 |   3|  7 |\r\n| 1  |  2 |  2 |   |\r\n', '输入Data');
INSERT INTO `t_blog` VALUES ('20', 'Javaweb', '2018-01-09', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><p>2018-01-09 16&#58;00&#58;20 星期二<i class=\"fa fa-step-backward fa-emoji\" title=\"step-backward\"></i></p>\r\n<p>[========]<br>   <i class=\"fa fa-btc fa-emoji\" title=\"btc\"></i> <i class=\"fa fa-smile-o fa-emoji\" title=\"smile-o\"></i></p>\r\n<pre><code class=\"lang-java\">public void messageReceived(ChannelHandlerContext ctx, Object msg) {\r\n      if (msg instanceof FullHttpRequest) {//如果是HTTP请求，进行HTTP操作\r\n          handleHttpRequest(ctx, (FullHttpRequest) msg);\r\n      } else if (msg instanceof WebSocketFrame) {//如果是Websocket请求，则进行websocket操作\r\n          handleWebSocketFrame(ctx, (WebSocketFrame) msg);\r\n      }\r\n  }\r\n</code></pre>\r\n', '#High this is a test\r\n\r\n2018-01-09 16:00:20 星期二:fa-step-backward:\r\n\r\n[========]\r\n   :fa-btc: :fa-smile-o:\r\n   \r\n   \r\n   \r\n   ```java\r\npublic void messageReceived(ChannelHandlerContext ctx, Object msg) {\r\n      if (msg instanceof FullHttpRequest) {//如果是HTTP请求，进行HTTP操作\r\n          handleHttpRequest(ctx, (FullHttpRequest) msg);\r\n      } else if (msg instanceof WebSocketFrame) {//如果是Websocket请求，则进行websocket操作\r\n          handleWebSocketFrame(ctx, (WebSocketFrame) msg);\r\n      }\r\n  }\r\n```\r\n', 'Javaweb');
INSERT INTO `t_blog` VALUES ('22', 'test title', '2018-01-18', '', '', 'JAVA');
INSERT INTO `t_blog` VALUES ('24', 'Javaweb11', '2018-01-18', '', '', 'Javaweb');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `tag_name` varchar(255) DEFAULT NULL,
  `blog_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('java', '1');
INSERT INTO `t_tag` VALUES ('javaweb', '2');
INSERT INTO `t_tag` VALUES ('mybatis', '3');
INSERT INTO `t_tag` VALUES ('javaEE', '20');
INSERT INTO `t_tag` VALUES ('34234', '13');
INSERT INTO `t_tag` VALUES ('Javaweb', '14');
INSERT INTO `t_tag` VALUES ('??', '15');
INSERT INTO `t_tag` VALUES ('11', '16');
INSERT INTO `t_tag` VALUES ('????11', '17');
INSERT INTO `t_tag` VALUES ('中文', '18');
INSERT INTO `t_tag` VALUES ('输入Data', '19');
INSERT INTO `t_tag` VALUES ('Javaweb', '20');
INSERT INTO `t_tag` VALUES ('JAVA', '22');
INSERT INTO `t_tag` VALUES ('Javaweb', '24');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) CHARACTER SET utf8 NOT NULL,
  `password` varchar(80) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3');

/*
Navicat MySQL Data Transfer

Source Server         : blogSQL
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : ssmblog

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-02-08 11:34:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_context
-- ----------------------------
DROP TABLE IF EXISTS `t_context`;
CREATE TABLE `t_context` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created` date DEFAULT NULL,
  `article` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `md` text NOT NULL,
  `tags` varchar(80) NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `slug` varchar(30) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_context
-- ----------------------------
INSERT INTO `t_context` VALUES ('5', '2016博客3', '2016-09-09', '222222', '2016博客2', '', 'post', null, null);
INSERT INTO `t_context` VALUES ('6', 'title', '2005-01-28', 'article', 'md', 'tags', 'post', null, null);
INSERT INTO `t_context` VALUES ('7', '12312', '2018-01-04', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><h2 id=\"h2-thx-for-my-first-test\"><a name=\"thx for my first test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>thx for my first test</h2>', '#High this is a test\r\n\r\n##thx for my first test', '1111', 'post', null, null);
INSERT INTO `t_context` VALUES ('8', 'tst tiet', '2018-01-23', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><h2 id=\"h2-again-i-m-coming\"><a name=\"Again I’m coming\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Again I’m coming</h2><ul>\r\n<li>1111</li><li>2222</li><li>3333</li></ul>\r\n', '#High this is a test\r\n\r\n\r\n##Again I\'m coming\r\n- 1111\r\n- 2222\r\n- 3333', '23', 'post', null, null);
INSERT INTO `t_context` VALUES ('18', '中文', '2018-01-09', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><h2 id=\"h2--again\"><a name=\"中文again\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>中文again</h2>', '#High this is a test\r\n##中文again', '中文', 'post', null, null);
INSERT INTO `t_context` VALUES ('19', '博客系统', '2018-01-09', '<h1 id=\"h1-u8FD9u6B21u6765u4E2Au5E05u6C14u7684u663Eu793A\"><a name=\"这次来个帅气的显示\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>这次来个帅气的显示</h1><h3 id=\"h3-u6807u9898u4E8C\"><a name=\"标题二\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题二</h3><pre><code>\r\nalert(1)\r\nprint(\"you are so handsom\")\r\n\r\nvoid main (){\r\nsadasdasd\r\n}\r\n</code></pre><table>\r\n<thead>\r\n<tr>\r\n<th>4</th>\r\n<th>2</th>\r\n<th>3</th>\r\n<th>5</th>\r\n</tr>\r\n</thead>\r\n<tbody>\r\n<tr>\r\n<td>1</td>\r\n<td>3</td>\r\n<td>2</td>\r\n<td>7 6</td>\r\n</tr>\r\n<tr>\r\n<td>2</td>\r\n<td>2</td>\r\n<td>3</td>\r\n<td>7</td>\r\n</tr>\r\n<tr>\r\n<td>1</td>\r\n<td>2</td>\r\n<td>2</td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n', '#这次来个帅气的显示\r\n\r\n\r\n### 标题二\r\n\r\n\r\n```\r\n\r\nalert(1)\r\nprint(\"you are so handsom\")\r\n\r\nvoid main (){\r\nsadasdasd\r\n}\r\n```\r\n|  4 |  2 | 3  |  5 |\r\n| ------------ | ------------ | ------------ | ------------ |\r\n|   1| 3  | 2  | 7 6|\r\n|  2 |  2 |   3|  7 |\r\n| 1  |  2 |  2 |   |\r\n', '输入Data', 'post', null, null);
INSERT INTO `t_context` VALUES ('20', 'Javaweb', '2018-01-09', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><p>2018-01-09 16&#58;00&#58;20 星期二<i class=\"fa fa-step-backward fa-emoji\" title=\"step-backward\"></i></p>\r\n<p>[========]<br>   <i class=\"fa fa-btc fa-emoji\" title=\"btc\"></i> <i class=\"fa fa-smile-o fa-emoji\" title=\"smile-o\"></i></p>\r\n<pre><code class=\"lang-java\">public void messageReceived(ChannelHandlerContext ctx, Object msg) {\r\n      if (msg instanceof FullHttpRequest) {//如果是HTTP请求，进行HTTP操作\r\n          handleHttpRequest(ctx, (FullHttpRequest) msg);\r\n      } else if (msg instanceof WebSocketFrame) {//如果是Websocket请求，则进行websocket操作\r\n          handleWebSocketFrame(ctx, (WebSocketFrame) msg);\r\n      }\r\n  }\r\n</code></pre>\r\n', '#High this is a test\r\n\r\n2018-01-09 16:00:20 星期二:fa-step-backward:\r\n\r\n[========]\r\n   :fa-btc: :fa-smile-o:\r\n   \r\n   \r\n   \r\n   ```java\r\npublic void messageReceived(ChannelHandlerContext ctx, Object msg) {\r\n      if (msg instanceof FullHttpRequest) {//如果是HTTP请求，进行HTTP操作\r\n          handleHttpRequest(ctx, (FullHttpRequest) msg);\r\n      } else if (msg instanceof WebSocketFrame) {//如果是Websocket请求，则进行websocket操作\r\n          handleWebSocketFrame(ctx, (WebSocketFrame) msg);\r\n      }\r\n  }\r\n```\r\n', 'Javaweb', 'post', null, null);
INSERT INTO `t_context` VALUES ('22', 'test title', '2018-01-18', '', '', '', 'page', null, null);
INSERT INTO `t_context` VALUES ('24', 'Javaweb11', '2018-01-18', '', '', 'about', 'page', null, null);

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(100) NOT NULL,
  `data` varchar(2000) DEFAULT NULL,
  `author_id` int(10) NOT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logs
-- ----------------------------
INSERT INTO `t_logs` VALUES ('1', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517909538');
INSERT INTO `t_logs` VALUES ('2', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517909693');
INSERT INTO `t_logs` VALUES ('3', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517912692');
INSERT INTO `t_logs` VALUES ('4', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517972101');
INSERT INTO `t_logs` VALUES ('5', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517972131');
INSERT INTO `t_logs` VALUES ('6', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517972193');
INSERT INTO `t_logs` VALUES ('7', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517972385');
INSERT INTO `t_logs` VALUES ('8', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517972463');
INSERT INTO `t_logs` VALUES ('9', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517972564');
INSERT INTO `t_logs` VALUES ('10', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517973100');
INSERT INTO `t_logs` VALUES ('11', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517973221');
INSERT INTO `t_logs` VALUES ('12', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517977856');
INSERT INTO `t_logs` VALUES ('13', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517977893');
INSERT INTO `t_logs` VALUES ('14', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517977938');
INSERT INTO `t_logs` VALUES ('15', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1517977945');
INSERT INTO `t_logs` VALUES ('16', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518053679');
INSERT INTO `t_logs` VALUES ('17', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518054613');
INSERT INTO `t_logs` VALUES ('18', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518055139');
INSERT INTO `t_logs` VALUES ('19', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518055763');
INSERT INTO `t_logs` VALUES ('20', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518056480');
INSERT INTO `t_logs` VALUES ('21', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518057811');
INSERT INTO `t_logs` VALUES ('22', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518058045');
INSERT INTO `t_logs` VALUES ('23', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518058238');
INSERT INTO `t_logs` VALUES ('24', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518058513');
INSERT INTO `t_logs` VALUES ('25', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '1518059970');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `tag_name` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL
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
INSERT INTO `t_tag` VALUES ('123', '27');
INSERT INTO `t_tag` VALUES ('das', '30');
INSERT INTO `t_tag` VALUES ('123', '32');
INSERT INTO `t_tag` VALUES ('qwe', '33');

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

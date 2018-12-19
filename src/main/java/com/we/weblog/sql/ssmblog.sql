/*
Navicat MySQL Data Transfer

Source Server         : blogSQL
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : ssmblog

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-12-19 15:05:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hexo_attach
-- ----------------------------
DROP TABLE IF EXISTS `hexo_attach`;
CREATE TABLE `hexo_attach` (
  `attachId` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 附件编号',
  `attachName` varchar(60) DEFAULT NULL COMMENT '附件名',
  `attachPath` varchar(255) DEFAULT NULL COMMENT '附件路径',
  `attachSmallPath` varchar(255) DEFAULT NULL COMMENT '附件缩略图路径',
  `attachType` varchar(255) DEFAULT NULL COMMENT '附件类型',
  `attachSuffix` varchar(255) DEFAULT NULL COMMENT '附件后缀',
  `attachCreated` date DEFAULT NULL COMMENT '上传时间',
  `attachSize` double DEFAULT NULL COMMENT '附件大小',
  `attachWidth` double DEFAULT NULL COMMENT '附件长宽',
  PRIMARY KEY (`attachId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hexo_attach
-- ----------------------------

-- ----------------------------
-- Table structure for hexo_comment
-- ----------------------------
DROP TABLE IF EXISTS `hexo_comment`;
CREATE TABLE `hexo_comment` (
  `cid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'comment主键',
  `article_id` int(10) NOT NULL COMMENT '评论文章ID',
  `created` datetime NOT NULL COMMENT '创建时间',
  `author` varchar(30) NOT NULL COMMENT '评论作者',
  `email` varchar(30) NOT NULL COMMENT '评论者邮箱',
  `ip` varchar(20) DEFAULT NULL COMMENT '评论者IP',
  `content` text NOT NULL COMMENT '评论内容',
  `uid` int(10) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hexo_comment
-- ----------------------------
INSERT INTO `hexo_comment` VALUES ('26', '3', '2018-05-16 15:24:14', '11', '222@qq.com', '127.0.0.1', 'sdsdsds', null, null);
INSERT INTO `hexo_comment` VALUES ('27', '3', '2018-05-16 15:24:43', '22', '33@qq.com', '127.0.0.1', 'sdsd', null, null);
INSERT INTO `hexo_comment` VALUES ('28', '3', '2018-05-16 15:26:20', '22', '33@qq.com', '127.0.0.1', 'sdsd', null, null);
INSERT INTO `hexo_comment` VALUES ('29', '3', '2018-05-16 15:26:39', '22', '33@qq.com', '127.0.0.1', 'sdsd', null, null);
INSERT INTO `hexo_comment` VALUES ('30', '3', '2018-05-16 15:27:25', '22', '222@qq.com', '127.0.0.1', 'dsdsddd', null, null);
INSERT INTO `hexo_comment` VALUES ('34', '42', '2018-06-11 21:47:49', '22', '222@qq.com', '0:0:0:0:0:0:0:1', '1', null, null);
INSERT INTO `hexo_comment` VALUES ('35', '63', '2018-06-12 11:15:40', '11', '222@qq.com', '0:0:0:0:0:0:0:1', '12323', null, null);
INSERT INTO `hexo_comment` VALUES ('39', '3', '2018-06-12 13:05:52', 'admin', 'admin@qq.com', null, '@11  sdsd=', null, '26');
INSERT INTO `hexo_comment` VALUES ('40', '44', '2018-06-12 13:37:45', '22', '222@qq.com', '0:0:0:0:0:0:0:1', 'sdsdsd', null, '0');
INSERT INTO `hexo_comment` VALUES ('41', '42', '2018-06-12 16:30:44', '11', '222@qq.com', '0:0:0:0:0:0:0:1', 'aaddssds', null, '0');
INSERT INTO `hexo_comment` VALUES ('42', '42', '2018-06-12 16:30:46', '11', '222@qq.com', '0:0:0:0:0:0:0:1', 'aaddssds', null, '0');
INSERT INTO `hexo_comment` VALUES ('43', '3', '2018-06-12 16:35:56', 'admin', 'admin@qq.com', null, '回复@11  sdad+=', null, '26');
INSERT INTO `hexo_comment` VALUES ('44', '3', '2018-07-27 11:08:21', '11', '22@qq.com', '0:0:0:0:0:0:0:1', 'sdsdsdsds ds', null, '0');
INSERT INTO `hexo_comment` VALUES ('45', '3', '2018-07-27 11:08:22', '11', '22@qq.com', '0:0:0:0:0:0:0:1', 'sdsdsdsds ds', null, '0');
INSERT INTO `hexo_comment` VALUES ('46', '3', '2018-07-27 11:08:40', 'sdsd', '22@qq.com', '0:0:0:0:0:0:0:1', 'weqweqweqwe', null, '0');
INSERT INTO `hexo_comment` VALUES ('47', '3', '2018-09-25 23:48:11', 'admin', 'admin@qq.com', null, '回复@11  11=', null, '26');

-- ----------------------------
-- Table structure for hexo_logs
-- ----------------------------
DROP TABLE IF EXISTS `hexo_logs`;
CREATE TABLE `hexo_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(100) NOT NULL,
  `data` varchar(2000) DEFAULT NULL,
  `author_id` int(10) NOT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=424 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hexo_logs
-- ----------------------------
INSERT INTO `hexo_logs` VALUES ('396', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 11:00:39');
INSERT INTO `hexo_logs` VALUES ('397', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 11:01:57');
INSERT INTO `hexo_logs` VALUES ('398', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 11:03:03');
INSERT INTO `hexo_logs` VALUES ('399', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 11:03:05');
INSERT INTO `hexo_logs` VALUES ('400', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 11:04:18');
INSERT INTO `hexo_logs` VALUES ('401', '退出登录', null, '1', '0:0:0:0:0:0:0:1', '2018-12-14 11:13:14');
INSERT INTO `hexo_logs` VALUES ('402', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 11:32:56');
INSERT INTO `hexo_logs` VALUES ('403', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 13:49:20');
INSERT INTO `hexo_logs` VALUES ('404', '退出登录', null, '1', '0:0:0:0:0:0:0:1', '2018-12-14 14:08:43');
INSERT INTO `hexo_logs` VALUES ('405', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 14:08:52');
INSERT INTO `hexo_logs` VALUES ('406', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 14:21:47');
INSERT INTO `hexo_logs` VALUES ('407', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-14 14:38:37');
INSERT INTO `hexo_logs` VALUES ('408', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-18 14:36:10');
INSERT INTO `hexo_logs` VALUES ('409', '添加新博客', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-18 14:50:01');
INSERT INTO `hexo_logs` VALUES ('410', '添加新博客', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-18 14:50:01');
INSERT INTO `hexo_logs` VALUES ('411', '添加新博客', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-18 14:51:06');
INSERT INTO `hexo_logs` VALUES ('412', '退出登录', null, '1', '0:0:0:0:0:0:0:1', '2018-12-18 14:54:38');
INSERT INTO `hexo_logs` VALUES ('413', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-18 14:54:46');
INSERT INTO `hexo_logs` VALUES ('414', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-18 15:31:05');
INSERT INTO `hexo_logs` VALUES ('415', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-19 09:33:48');
INSERT INTO `hexo_logs` VALUES ('416', '添加新博客', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-19 09:34:10');
INSERT INTO `hexo_logs` VALUES ('417', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-19 14:04:28');
INSERT INTO `hexo_logs` VALUES ('418', '删除博客', '78 ', '1', '0:0:0:0:0:0:0:1', '2018-12-19 14:28:19');
INSERT INTO `hexo_logs` VALUES ('419', '删除博客', '77 ', '1', '0:0:0:0:0:0:0:1', '2018-12-19 14:28:31');
INSERT INTO `hexo_logs` VALUES ('420', '删除博客', '76 ', '1', '0:0:0:0:0:0:0:1', '2018-12-19 14:32:52');
INSERT INTO `hexo_logs` VALUES ('421', '删除博客', '75 ', '1', '0:0:0:0:0:0:0:1', '2018-12-19 14:32:55');
INSERT INTO `hexo_logs` VALUES ('422', '删除博客', '70 ', '1', '0:0:0:0:0:0:0:1', '2018-12-19 14:32:57');
INSERT INTO `hexo_logs` VALUES ('423', '登陆后台', 'admin', '1', '0:0:0:0:0:0:0:1', '2018-12-19 14:53:19');

-- ----------------------------
-- Table structure for hexo_metas
-- ----------------------------
DROP TABLE IF EXISTS `hexo_metas`;
CREATE TABLE `hexo_metas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '存储名字',
  `type` varchar(20) NOT NULL COMMENT '存储类型',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hexo_metas
-- ----------------------------
INSERT INTO `hexo_metas` VALUES ('1', '默认分类', 'category');
INSERT INTO `hexo_metas` VALUES ('2', 'name', 'category');
INSERT INTO `hexo_metas` VALUES ('6', 'hello', 'category');
INSERT INTO `hexo_metas` VALUES ('7', 'wsewe', 'category');
INSERT INTO `hexo_metas` VALUES ('10', 'asda', 'category');
INSERT INTO `hexo_metas` VALUES ('9', '2323', 'category');
INSERT INTO `hexo_metas` VALUES ('11', '但是', 'category');

-- ----------------------------
-- Table structure for hexo_post
-- ----------------------------
DROP TABLE IF EXISTS `hexo_post`;
CREATE TABLE `hexo_post` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created` datetime DEFAULT NULL,
  `article` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `md` text NOT NULL,
  `tags` varchar(80) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `slug` varchar(30) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  `categories` varchar(20) DEFAULT NULL,
  `status` int(10) DEFAULT '0' COMMENT '文章状态',
  `hits` int(11) DEFAULT NULL COMMENT '评论数',
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hexo_post
-- ----------------------------
INSERT INTO `hexo_post` VALUES ('3', '中文', '2017-01-10 00:00:00', '<h2 id=\"h2-u4E2Du6587u8FD9u91CCu6D4Bu8BD5u6700u9891u7E41\"><a name=\"中文这里测试最频繁\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>中文这里测试最频繁</h2>', '##中文这里测试最频繁', '标签', 'post', null, '默认分类', null, '0', '68');
INSERT INTO `hexo_post` VALUES ('44', '博客问题 ', '2018-03-01 00:00:00', '<h2 id=\"h2-u9700u6C42\"><a name=\"需求\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>需求</h2><ol>\n<li>前后台标签管理一致</li><li>页面管理一直显示<h2 id=\"h2-u95EEu9898\"><a name=\"问题\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>问题</h2></li><li><p>博客日期的解析与显示  年月日 时间显示</p>\n</li><li><p>博客的标签显示和处理</p>\n</li><li><p>博客的分类显示和处理</p>\n</li><li><p>修改博客存在问题 修改保存不了</p>\n</li><li><p>保存是否确认</p>\n<h2 id=\"h2-u5F85u4F18u5316\"><a name=\"待优化\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>待优化</h2></li><li><p>前端改为模板</p>\n</li><li><p>博客的 ajax 改为axios</p>\n</li><li><p>后台Service 函数功能的重复</p>\n</li><li><p>还有神马呢？</p>\n</li></ol>\n<p>5.切换页面提示是否保存</p>\n', '##需求\n1.  前后台标签管理一致\n2.  页面管理一直显示\n## 问题\n1.  博客日期的解析与显示  年月日 时间显示\n\n2.  博客的标签显示和处理\n\n3. 博客的分类显示和处理\n\n4.  修改博客存在问题 修改保存不了\n\n5.  保存是否确认\n## 待优化\n\n1. 前端改为模板\n\n2. 博客的 ajax 改为axios\n\n3. 后台Service 函数功能的重复\n\n4. 还有神马呢？\n\n5.切换页面提示是否保存\n', '', 'post', null, '默认分类', null, '0', '39');
INSERT INTO `hexo_post` VALUES ('5', 'Javaweb', '2018-01-09 00:00:00', '<h1 id=\"h1-high-this-is-a-test\"><a name=\"High this is a test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>High this is a test</h1><p>2018-01-09 16&#58;00&#58;20 星期二<i class=\"fa fa-step-backward fa-emoji\" title=\"step-backward\"></i></p>\r\n<p>[========]<br>   <i class=\"fa fa-btc fa-emoji\" title=\"btc\"></i> <i class=\"fa fa-smile-o fa-emoji\" title=\"smile-o\"></i></p>\r\n<pre><code class=\"lang-java\">public void messageReceived(ChannelHandlerContext ctx, Object msg) {\r\n      if (msg instanceof FullHttpRequest) {//如果是HTTP请求，进行HTTP操作\r\n          handleHttpRequest(ctx, (FullHttpRequest) msg);\r\n      } else if (msg instanceof WebSocketFrame) {//如果是Websocket请求，则进行websocket操作\r\n          handleWebSocketFrame(ctx, (WebSocketFrame) msg);\r\n      }\r\n  }\r\n</code></pre>\r\n', '#High this is a test\r\n\r\n2018-01-09 16:00:20 星期二:fa-step-backward:\r\n\r\n[========]\r\n   :fa-btc: :fa-smile-o:\r\n   \r\n   \r\n   \r\n   ```java\r\npublic void messageReceived(ChannelHandlerContext ctx, Object msg) {\r\n      if (msg instanceof FullHttpRequest) {//如果是HTTP请求，进行HTTP操作\r\n          handleHttpRequest(ctx, (FullHttpRequest) msg);\r\n      } else if (msg instanceof WebSocketFrame) {//如果是Websocket请求，则进行websocket操作\r\n          handleWebSocketFrame(ctx, (WebSocketFrame) msg);\r\n      }\r\n  }\r\n```\r\n', 'Javaweb', 'post', null, '默认分类', null, '0', '11');
INSERT INTO `hexo_post` VALUES ('43', '博客问题处理顺序', '2018-03-01 00:00:00', '<h3 id=\"h3--\"><a name=\"博客日期的解析与显示  年月日 时间显示\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>博客日期的解析与显示  年月日 时间显示</h3><h3 id=\"h3-u535Au5BA2u7684u6807u7B7Eu663Eu793Au548Cu5904u7406\"><a name=\"博客的标签显示和处理\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>博客的标签显示和处理</h3><h3 id=\"h3-u535Au5BA2u7684u5206u7C7Bu663Eu793Au548Cu5904u7406\"><a name=\"博客的分类显示和处理\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>博客的分类显示和处理</h3>', '### 博客日期的解析与显示  年月日 时间显示\r\n\r\n###  博客的标签显示和处理\r\n\r\n### 博客的分类显示和处理', '', 'post', null, '默认分类', 'hello', '0', '8');
INSERT INTO `hexo_post` VALUES ('45', '友链', '2018-03-01 00:00:00', '<h3 id=\"h3--\"><a name=\"友情链接 巴啦啦\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>友情链接 巴啦啦</h3>', '### 友情链接 巴啦啦', 'null', 'page', '3333', '', 'hello', '0', '0');
INSERT INTO `hexo_post` VALUES ('46', '关于我', '2018-03-02 00:00:00', '<h2 id=\"h2--\"><a name=\"首先 我是一个好人\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>首先 我是一个好人</h2><h2 id=\"h2-about-me\"><a name=\"About me\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>About me</h2><h2 id=\"h2-this-is-a-test\"><a name=\"This is a Test\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>This is a Test</h2><pre><code class=\"lang-c\">int main\n{\n   printf(\"c test\");\n}\n</code></pre>\n<pre><code class=\"lang-java\"> System.out.println(\"java test\");\n</code></pre>\n', '## 首先 我是一个好人\n\n## About me\n\n\n## This is a Test\n\n\n```c\nint main\n{\n   printf(\"c test\");\n}\n\n```\n\n\n```java\n System.out.println(\"java test\");\n\n```', 'about', 'page', null, '', 'hello', '0', '0');
INSERT INTO `hexo_post` VALUES ('40', 'pages', '2019-02-06 00:00:00', '<p>this is a pages test</p>\n', 'this is a pages test', '', 'post', null, '默认分类', null, '0', '7');
INSERT INTO `hexo_post` VALUES ('42', ' shodan学习笔记', '2018-03-01 00:00:00', '<hr>\r\n<p>title: shodan学习笔记<br>date: 2018-02-06 19&#58;22&#58;02<br>tags: web安全<br>copyright: true\r\n<h2 id=\"h2-categories-web-\"><a name=\"categories: web安全\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>categories: web安全</h2><h2 id=\"h2--1-elasticsearch-\"><a name=\"场景1：想搜索美国所有的elasticsearch服务器\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>场景1：想搜索美国所有的elasticsearch服务器</h2><p>ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。为什么要搜索他，因为他有多个漏洞，未授权访问、目录遍历、任意命令执行等，搜索wooyun镜像也可以看到有不少结果。</p>\r\n<p>我们可以了解到elasticsearch的默认端口是9200，这时我们可以先在Shodan进行相应搜索，看会返回什么。在Shodan中输入“port:9200”来进行搜索，<br>shodan 命令<br>1 .  <code>port:9200</code><br><img src=\"https://i.imgur.com/MbgpPYJ.png\" alt=\"\">\r\n<ol>\r\n<li>我们想要的是美国的elasticsearch服务器，可以看到左边的统计中已经对一些常用字段进行了分类统计，有国家、组织、操作系统及产品，这里已经看到了我们所需要的字段，只需要点击相应的标签，即可进行进一步的细化搜索。</li></ol>\r\n<p>点击即可细化搜索</p>\r\n<p>注意观察每一次点击标签后搜索语句的变化，到这里我们可以看到已经实现了我们的目的，找到了美国的elasticsearch服务器。当搜索结果细化到一定程度时，左边的统计也会有相应的变化，现在可以看到已经变成了对城市、组织、操作系统、和elasticsearch版本的一个统计。至此我们已经学会一些基本语法：<br><code>“port:” 按照指定端口进行搜索\r\n“product:” 对某一产品进行搜索\r\n“country:” 指定国家，</code><br>      当我们需要多个词同时搜索时，只需要在每个关键词间加空格隔开就行，当不需要某个词时，可以用”-”加上关键词来进行去除，比如不想要Amazon的elasticsearch服务器，就可以”-org:amazon”，如何知道org这个关键词的？同样是左边的统计，当我们鼠标移动到Amazon时，可以看到他相应的链接，已经给出了关键词。\r\n<h2 id=\"h2--2-supervisor-\"><a name=\"场景2：搜索所有的搭建了Supervisor的服务器\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>场景2：搜索所有的搭建了Supervisor的服务器</h2><pre><code>  Supervisor是一个用Python写的进程管理工具，可以很方便的用来启动、重启、关闭进程。我们为什么要搜索这个东西呢，当然是因为他有问题。今年9月份的时候Supervisor爆出了一个远程代码执行漏洞CVE-2017-11610，我们想要复现一下漏洞（滑稽）。按照上一个场景的经验，当我们想要通过端口来搜索Supervisor时，我们发现他并没有默认端口，所以就需要用的另一种更常用的方法。\r\n</code></pre><p> 打开后发现这个就是我们需要的Supervisor服务器，那么我们用什么来定位这类服务器呢，这里就要用到一些新的语法：<br><code>“http.title:”</code> 根据网站title搜索</p>\r\n<h3 id=\"h3--3-\"><a name=\"场景3：搜索一个公司相关的服务器\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>场景3：搜索一个公司相关的服务器</h3><p>首先我们都知道，一般的网站有自己icon，比如百度、阿里、腾讯等，而一般公司会对其所有网站的title中都加上这个icon，来增加统一性，也方便用户识别，如下如所示：</p>\r\n<p>   首先要知道在Shodan结果中在哪里寻找这个标记，如下如所示，<br><img src=\"https://i.imgur.com/iXLazQM.png\" alt=\"\">\r\n<p>   Shodan搜索icon时会出现在箭头所示的位置。<br>      因为这个icon只能算是对公司资产的一种标识，所以有些网站可能并没有带有icon，但是有些常用手段收集的服务器可能会漏掉这些，所以这个方法还是有一定的用途，这里拿支付宝举例。<br>      我们尝试性的输入一些与支付宝相关的内容来进行搜索，如Alipay，支付宝，www.alipay.com等，当我们搜索到www.alipay.com时，可以看到结果内已经有携带支付宝icon的网站了\r\n<p><code>”http.favicon.hash:”</code></p>\r\n<p>最后在列举一些比较常用的搜索语句来供大家参考，什么时候用什么样的语句，完全取决于你怎么去想。</p>\r\n<p><code>asn</code>     区域自治编号<br><code>port</code>           端口<br><code>org</code>        ip所属组织机构<br><code>os</code>            操作系统类型<br><code>http.html</code>    网页内容<br><code>html.title</code>     网页标题<br><code>http.server</code>    http请求返回中server的类型<br><code>http.status</code>      http请求返回响应码的状态<br><code>city</code>       城市<br><code>country</code>       国家<br><code>product</code>       所使用的软件或产品</p>\r\n<p> 其实Shodan的关键词还有很多，经纬度、ssl信息、smb版本号、区号、返回码状态等等，只要发挥你的想象力，从结果中寻找结果，就总能得到你想要的。<br>以上就是我使用Shodan过程中的一点心得，分享给大家。安全的路还很长，学习的路还很远，不忘初心，方得始终。\r\n<h3 id=\"h3-u4F7Fu7528u641Cu7D22u8FC7u6EE4\"><a name=\"使用搜索过滤\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>使用搜索过滤</h3><p>如果像前面单纯只使用关键字直接进行搜索，搜索结果可能不尽人意，那么此时我们就需要使用一些特定的命令对搜索结果进行过滤，常见用的过滤命令如下所示：</p>\r\n<p><code>hostname：</code>搜索指定的主机或域名，例如 <code>hostname:\"google\"</code><br><code>port：</code>搜索指定的端口或服务，例如 <code>port:\"21\"</code><br><code>country：</code>搜索指定的国家，例如 <code>country:\"CN\"</code><br><code>city：</code>搜索指定的城市，例如 <code>city:\"Hefei\"</code><br><code>org：</code>搜索指定的组织或公司，例如 <code>org:\"google\"</code><br><code>isp：</code>搜索指定的ISP供应商，例如<code>isp:\"China Telecom\"</code><br><code>product：</code>搜索指定的操作系统/软件/平台，例如<code>product:\"Apache httpd\"</code><br><code>version：</code>搜索指定的软件版本，例如 <code>version:\"1.6.2\"</code><br><code>geo：</code>搜索指定的地理位置，参数为经纬度，例如 <code>geo:\"31.8639, 117.2808\"</code><br><code>before/after：</code>搜索指定收录时间前后的数据，格式为dd-mm-yy，例如 <code>before:\"11-11-15\"</code><br><code>net：</code>搜索指定的IP地址或子网，例如<code>net:\"210.45.240.0/24\"</code><br>```<br>搜索实例\r\n<p>查找位于合肥的 Apache 服务器：</p>\r\n<p><code>apache city:\"Hefei\"</code><br>查找位于国内的 Nginx 服务器：\r\n<p><code>nginx country:\"CN\"</code><br>查找 GWS(Google Web Server) 服务器：\r\n<p>“Server: gws”<br><code>hostname:\"google\"</code><br>查找指定网段的华为设备：\r\n<p>huawei <code>net:\"61.191.146.0/24\"</code><br>net： 使用IP/CIDR标记法（eg：127.0.0.1/24）来设定IP地址范围，从而查询所有设备是否匹配正确，是否有存在漏洞的主机或者服务器，是否可以从外部访问等\r\n', '---\r\ntitle: shodan学习笔记\r\ndate: 2018-02-06 19:22:02\r\ntags: web安全\r\ncopyright: true\r\ncategories: web安全\r\n---\r\n\r\n## 场景1：想搜索美国所有的elasticsearch服务器\r\n\r\n\r\nElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。为什么要搜索他，因为他有多个漏洞，未授权访问、目录遍历、任意命令执行等，搜索wooyun镜像也可以看到有不少结果。\r\n\r\n我们可以了解到elasticsearch的默认端口是9200，这时我们可以先在Shodan进行相应搜索，看会返回什么。在Shodan中输入“port:9200”来进行搜索，\r\nshodan 命令\r\n1 .  `port:9200`\r\n![](https://i.imgur.com/MbgpPYJ.png)\r\n2. 我们想要的是美国的elasticsearch服务器，可以看到左边的统计中已经对一些常用字段进行了分类统计，有国家、组织、操作系统及产品，这里已经看到了我们所需要的字段，只需要点击相应的标签，即可进行进一步的细化搜索。\r\n\r\n点击即可细化搜索\r\n\r\n注意观察每一次点击标签后搜索语句的变化，到这里我们可以看到已经实现了我们的目的，找到了美国的elasticsearch服务器。当搜索结果细化到一定程度时，左边的统计也会有相应的变化，现在可以看到已经变成了对城市、组织、操作系统、和elasticsearch版本的一个统计。至此我们已经学会一些基本语法：\r\n`\r\n“port:” 按照指定端口进行搜索\r\n“product:” 对某一产品进行搜索\r\n“country:” 指定国家，\r\n`\r\n      当我们需要多个词同时搜索时，只需要在每个关键词间加空格隔开就行，当不需要某个词时，可以用”-”加上关键词来进行去除，比如不想要Amazon的elasticsearch服务器，就可以”-org:amazon”，如何知道org这个关键词的？同样是左边的统计，当我们鼠标移动到Amazon时，可以看到他相应的链接，已经给出了关键词。\r\n##  场景2：搜索所有的搭建了Supervisor的服务器\r\n\r\n      Supervisor是一个用Python写的进程管理工具，可以很方便的用来启动、重启、关闭进程。我们为什么要搜索这个东西呢，当然是因为他有问题。今年9月份的时候Supervisor爆出了一个远程代码执行漏洞CVE-2017-11610，我们想要复现一下漏洞（滑稽）。按照上一个场景的经验，当我们想要通过端口来搜索Supervisor时，我们发现他并没有默认端口，所以就需要用的另一种更常用的方法。\r\n\r\n\r\n 打开后发现这个就是我们需要的Supervisor服务器，那么我们用什么来定位这类服务器呢，这里就要用到一些新的语法： \r\n`“http.title:” ` 根据网站title搜索\r\n\r\n###       场景3：搜索一个公司相关的服务器\r\n\r\n首先我们都知道，一般的网站有自己icon，比如百度、阿里、腾讯等，而一般公司会对其所有网站的title中都加上这个icon，来增加统一性，也方便用户识别，如下如所示：\r\n\r\n   首先要知道在Shodan结果中在哪里寻找这个标记，如下如所示，\r\n![](https://i.imgur.com/iXLazQM.png)\r\n\r\n   Shodan搜索icon时会出现在箭头所示的位置。\r\n      因为这个icon只能算是对公司资产的一种标识，所以有些网站可能并没有带有icon，但是有些常用手段收集的服务器可能会漏掉这些，所以这个方法还是有一定的用途，这里拿支付宝举例。\r\n      我们尝试性的输入一些与支付宝相关的内容来进行搜索，如Alipay，支付宝，www.alipay.com等，当我们搜索到www.alipay.com时，可以看到结果内已经有携带支付宝icon的网站了\r\n\r\n`”http.favicon.hash:”`\r\n\r\n\r\n最后在列举一些比较常用的搜索语句来供大家参考，什么时候用什么样的语句，完全取决于你怎么去想。\r\n\r\n`asn`     区域自治编号      \r\n`port `           端口                           \r\n`org`        ip所属组织机构\r\n`os `            操作系统类型\r\n`http.html `    网页内容\r\n`html.title`     网页标题\r\n`http.server`    http请求返回中server的类型\r\n`http.status `      http请求返回响应码的状态\r\n`city `       城市\r\n`country`       国家\r\n`product `       所使用的软件或产品\r\n\r\n\r\n 其实Shodan的关键词还有很多，经纬度、ssl信息、smb版本号、区号、返回码状态等等，只要发挥你的想象力，从结果中寻找结果，就总能得到你想要的。\r\n以上就是我使用Shodan过程中的一点心得，分享给大家。安全的路还很长，学习的路还很远，不忘初心，方得始终。\r\n\r\n### 使用搜索过滤\r\n\r\n如果像前面单纯只使用关键字直接进行搜索，搜索结果可能不尽人意，那么此时我们就需要使用一些特定的命令对搜索结果进行过滤，常见用的过滤命令如下所示：\r\n\r\n`hostname：`搜索指定的主机或域名，例如 `hostname:\"google\"`\r\n`port：`搜索指定的端口或服务，例如 `port:\"21\"`\r\n`country：`搜索指定的国家，例如 `country:\"CN\"`\r\n`city：`搜索指定的城市，例如 `city:\"Hefei\"`\r\n`org：`搜索指定的组织或公司，例如 `org:\"google\"`\r\n`isp：`搜索指定的ISP供应商，例如` isp:\"China Telecom\"`\r\n`product：`搜索指定的操作系统/软件/平台，例如` product:\"Apache httpd\"`\r\n`version：`搜索指定的软件版本，例如 `version:\"1.6.2\"`\r\n`geo：`搜索指定的地理位置，参数为经纬度，例如 `geo:\"31.8639, 117.2808\"`\r\n`before/after：`搜索指定收录时间前后的数据，格式为dd-mm-yy，例如 `before:\"11-11-15\"`\r\n`net：`搜索指定的IP地址或子网，例如` net:\"210.45.240.0/24\"`\r\n```\r\n搜索实例\r\n\r\n\r\n\r\n查找位于合肥的 Apache 服务器：\r\n\r\n`apache city:\"Hefei\"`\r\n查找位于国内的 Nginx 服务器：\r\n\r\n`nginx country:\"CN\"`\r\n查找 GWS(Google Web Server) 服务器：\r\n\r\n\"Server: gws\" \r\n`hostname:\"google\"`\r\n查找指定网段的华为设备：\r\n\r\nhuawei `net:\"61.191.146.0/24\"`\r\nnet： 使用IP/CIDR标记法（eg：127.0.0.1/24）来设定IP地址范围，从而查询所有设备是否匹配正确，是否有存在漏洞的主机或者服务器，是否可以从外部访问等\r\n\r\n', 'web安全', 'post', null, '默认分类', null, '0', '23');
INSERT INTO `hexo_post` VALUES ('63', 'xampp + phpstorm的简单配置', '2018-03-20 00:00:00', '<h1 id=\"h1-u524Du671Fu51C6u5907\"><a name=\"前期准备\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>前期准备</h1><p>安装以下东东：</p>\n<p>XAMPP<br>PhpStorm(如果有学生邮箱，请到jetbrains官网下载正版，可以免费使用正版，如果没有，百度下载，并下载注册机)\n<h1 id=\"h1--xampp\"><a name=\"配置XAMPP\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>配置XAMPP</h1><p>如下图，安装了apache之后，可以在httpd.conf文件配置监听端口:</p>\n<p>打开httpd.conf</p>\n<p>修改httpd.conf</p>\n<p>在Actions那里可以点击启动或者停止服务，这样的话用XAMPP无脑搭建环境的方式就成功了。</p>\n<p>配置PhpStorm<br>先给PhpStorm设置好php interpreter，在File->Settings里面，搜索php,设置好php语言版本,CLI Interpreter的路径选择你安装的php或者xampp自带的php，如图所示，我选择的是xampp自带的php，PhpStorm自动显示出了php版本：\n<p>设置PhpStorm的php支持</p>\n<p>我们上面配置了Apache，随便建立一个工程，创建一个php文件，写下echo 1;之类的，然后run，但是此时在PhpStorm上run的时候是无效的，这是因为我们还没配置好PhpStorm server, 在Settings里面搜索server,然后添加一个配置，name的话自定义，Host设置为localhost，Port设置为XAMPP Apache配置的端口(比如我的是8080，那么这里就填8080),Debugger选择Xdebug，具体配置后面再讲，配置图如下所示：</p>\n<p>配置PhpStorm Server</p>\n<p>然后在Tools-&gt;Deployment-&gt;Configuration里面进行Apache路径的指向：</p>\n<p>Apache指向</p>\n<p>然后是配置Run/Debug Configurations,点击右上角绿箭头左边的下拉框，有个Edit Configurations，点击这个可以配置Run/Debug的支持,点进去后，点击左上角的“+”符号,选择PHP Web Application,名字自定，Server选择之前配置的Server,Start Url指定了你点击Run的时候默认的路径，配置如下图所示：</p>\n<p>配置Run/Debug Configurations</p>\n<p>确保Apache服务已经开启，然后就可以测试是否连接成功了。</p>\n<p>Xdebug的配置<br>在PhpStorm中，进入File->Settings,然后搜索Debug，如下图所示配置：\n<p>配置DBGp</p>\n<p>然后点击Debug,确定Debug Port为上面配置的端口,其他默认:</p>\n<p>确定Debug</p>\n<p>然后是php方面的Xdebug配置，首先你要确保PhpStorm引用的php解析有Xdebug的支持，如下图所示(我这里用的是XAMPP的php)：</p>\n<p>$ php -v<br>PHP 5.5.19 (cli) (built: Nov 12 2014 12&#58;35&#58;44)<br>Copyright (c) 1997-2014 The PHP Group<br>Zend Engine v2.5.0, Copyright (c) 1998-2014 Zend Technologies<br>with Xdebug v2.2.5, Copyright (c) 2002-2014, by Derick Rethans\n<p>$ php -m<br>[PHP Modules]<br>bcmath<br>bz2<br>calendar<br>Core<br>ctype<br>curl<br>date<br>dom<br>ereg<br>exif<br>filter<br>ftp<br>gd<br>gettext<br>hash<br>iconv<br>json<br>libxml<br>mbstring<br>mcrypt<br>mhash<br>mysql<br>mysqli<br>mysqlnd<br>odbc<br>openssl<br>pcre<br>PDO<br>pdo_mysql<br>pdo_sqlite<br>Phar<br>Reflection<br>session<br>SimpleXML<br>soap<br>sockets<br>SPL<br>sqlite3<br>standard<br>tokenizer<br>wddx<br>xdebug<br>xml<br>xmlreader<br>xmlrpc<br>xmlwriter<br>xsl<br>zip<br>zlib\n<p>[Zend Modules]<br>Xdebug<br>如果没有，那么百度下载Xdebug.dll，然后在php.ini进行如下配置(在php.ini中搜索Xdebug)：\n<p>zend_extension = “C:\\xampp\\php\\ext\\php_xdebug.dll”    //这里的路径替换成你下载的xdebug.dll的路径，版本貌似要2.2.5以上<br>然后在php.ini进行如下配置：\n<p>xdebug.profiler_append = 0<br>xdebug.profiler_enable = 1<br>xdebug.profiler_enable_trigger = 0<br>xdebug.profiler_output_dir = “C:\\xampp\\tmp”   //注意路径替换成你的Xampp路径<br>xdebug.profiler_output_name = “cachegrind.out.%t-%s”<br>xdebug.trace_output_dir = “C:\\xampp\\tmp”    //上同\n<p>xdebug.remote_enable = on<br>xdebug.remote_handler = dbgp<br>xdebug.remote_host = localhost    //下面三行为我们之前在PhpStorm配置的参数<br>xdebug.idekey= PhpStorm<br>xdebug.remote_port = 9000<br>给浏览器安装Xdebug helper，Xdebug helper在这里下载，下载后直接拖到浏览器就行了，然后在管理扩展那里设置Xdebug helper:\n<p>Xdebug helper设置</p>\n<p>然后，在PhpStorm中的Run选项，点击Start Listening for PHP Debug Connections。</p>\n<p>至此，Xdebug设置完成，下面是Debug测试的画面：</p>\n<p>测试Debug</p>\n<p>差不多就这样了。</p>\n', '#前期准备\n安装以下东东：\n\nXAMPP\nPhpStorm(如果有学生邮箱，请到jetbrains官网下载正版，可以免费使用正版，如果没有，百度下载，并下载注册机)\n#配置XAMPP\n如下图，安装了apache之后，可以在httpd.conf文件配置监听端口:\n\n打开httpd.conf\n\n修改httpd.conf\n\n在Actions那里可以点击启动或者停止服务，这样的话用XAMPP无脑搭建环境的方式就成功了。\n\n配置PhpStorm\n先给PhpStorm设置好php interpreter，在File->Settings里面，搜索php,设置好php语言版本,CLI Interpreter的路径选择你安装的php或者xampp自带的php，如图所示，我选择的是xampp自带的php，PhpStorm自动显示出了php版本：\n\n设置PhpStorm的php支持\n\n我们上面配置了Apache，随便建立一个工程，创建一个php文件，写下echo 1;之类的，然后run，但是此时在PhpStorm上run的时候是无效的，这是因为我们还没配置好PhpStorm server, 在Settings里面搜索server,然后添加一个配置，name的话自定义，Host设置为localhost，Port设置为XAMPP Apache配置的端口(比如我的是8080，那么这里就填8080),Debugger选择Xdebug，具体配置后面再讲，配置图如下所示：\n\n配置PhpStorm Server\n\n然后在Tools->Deployment->Configuration里面进行Apache路径的指向：\n\nApache指向\n\n然后是配置Run/Debug Configurations,点击右上角绿箭头左边的下拉框，有个Edit Configurations，点击这个可以配置Run/Debug的支持,点进去后，点击左上角的“+”符号,选择PHP Web Application,名字自定，Server选择之前配置的Server,Start Url指定了你点击Run的时候默认的路径，配置如下图所示：\n\n配置Run/Debug Configurations\n\n确保Apache服务已经开启，然后就可以测试是否连接成功了。\n\nXdebug的配置\n在PhpStorm中，进入File->Settings,然后搜索Debug，如下图所示配置：\n\n配置DBGp\n\n然后点击Debug,确定Debug Port为上面配置的端口,其他默认:\n\n确定Debug\n\n然后是php方面的Xdebug配置，首先你要确保PhpStorm引用的php解析有Xdebug的支持，如下图所示(我这里用的是XAMPP的php)：\n\n$ php -v\nPHP 5.5.19 (cli) (built: Nov 12 2014 12:35:44)\nCopyright (c) 1997-2014 The PHP Group\nZend Engine v2.5.0, Copyright (c) 1998-2014 Zend Technologies\nwith Xdebug v2.2.5, Copyright (c) 2002-2014, by Derick Rethans\n\n$ php -m\n[PHP Modules]\nbcmath\nbz2\ncalendar\nCore\nctype\ncurl\ndate\ndom\nereg\nexif\nfilter\nftp\ngd\ngettext\nhash\niconv\njson\nlibxml\nmbstring\nmcrypt\nmhash\nmysql\nmysqli\nmysqlnd\nodbc\nopenssl\npcre\nPDO\npdo_mysql\npdo_sqlite\nPhar\nReflection\nsession\nSimpleXML\nsoap\nsockets\nSPL\nsqlite3\nstandard\ntokenizer\nwddx\nxdebug\nxml\nxmlreader\nxmlrpc\nxmlwriter\nxsl\nzip\nzlib\n\n[Zend Modules]\nXdebug\n如果没有，那么百度下载Xdebug.dll，然后在php.ini进行如下配置(在php.ini中搜索Xdebug)：\n\nzend_extension = \"C:\\xampp\\php\\ext\\php_xdebug.dll\"    //这里的路径替换成你下载的xdebug.dll的路径，版本貌似要2.2.5以上\n然后在php.ini进行如下配置：\n\nxdebug.profiler_append = 0\nxdebug.profiler_enable = 1\nxdebug.profiler_enable_trigger = 0\nxdebug.profiler_output_dir = \"C:\\xampp\\tmp\"   //注意路径替换成你的Xampp路径\nxdebug.profiler_output_name = \"cachegrind.out.%t-%s\"\nxdebug.trace_output_dir = \"C:\\xampp\\tmp\"    //上同\n\nxdebug.remote_enable = on\nxdebug.remote_handler = dbgp\nxdebug.remote_host = localhost    //下面三行为我们之前在PhpStorm配置的参数\nxdebug.idekey= PhpStorm\nxdebug.remote_port = 9000\n给浏览器安装Xdebug helper，Xdebug helper在这里下载，下载后直接拖到浏览器就行了，然后在管理扩展那里设置Xdebug helper:\n\nXdebug helper设置\n\n然后，在PhpStorm中的Run选项，点击Start Listening for PHP Debug Connections。\n\n至此，Xdebug设置完成，下面是Debug测试的画面：\n\n测试Debug\n\n差不多就这样了。', null, 'post', '', 'publish', '默认分类', '0', '25');
INSERT INTO `hexo_post` VALUES ('68', 'admin', '2018-04-03 22:42:49', '<h3 id=\"h3-admin\"><a name=\"admin\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>admin</h3>', '###admin', null, 'post', '', 'publish', '默认分类', '0', '9');

-- ----------------------------
-- Table structure for hexo_tag
-- ----------------------------
DROP TABLE IF EXISTS `hexo_tag`;
CREATE TABLE `hexo_tag` (
  `tag_name` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hexo_tag
-- ----------------------------
INSERT INTO `hexo_tag` VALUES ('Javaweb', '20');
INSERT INTO `hexo_tag` VALUES ('about', '46');
INSERT INTO `hexo_tag` VALUES ('标签', '3');

-- ----------------------------
-- Table structure for hexo_user
-- ----------------------------
DROP TABLE IF EXISTS `hexo_user`;
CREATE TABLE `hexo_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) CHARACTER SET utf8 NOT NULL,
  `password` varchar(80) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `userDisplayName` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `userEmail` varchar(50) DEFAULT NULL,
  `loginEnable` varchar(10) DEFAULT NULL,
  `loginLast` datetime DEFAULT NULL,
  `loginError` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hexo_user
-- ----------------------------
INSERT INTO `hexo_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, '22@qq.com', 'false', '2018-12-14 00:00:00', '0');

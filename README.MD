
## 基本技术

环境: `Springboot+mysql+admintle+vue.js+mybatis`环境搭建的个博客

### 版本控制

- 1月3日  v1.0
## 前台 白天版本
- 1月10日 v1.1   加入kisso单点登录 删除自己拦截器
- 1.11   v1.2   前台能解析mdtext文本，后台的editor.md加入到VUE框架中，后面editor界面BUG修改成功
![](https://i.imgur.com/lL7KoN8.jpg)
## 前台 夜晚版本

![](https://i.imgur.com/Dhk0Mvl.jpg)
## 后台登录界面
 ![](https://i.imgur.com/axudahd.png)

## 后台管理界面
![](https://i.imgur.com/A7dJ0IM.png)

### 博客列表显示
![](https://i.imgur.com/mQKfZms.png)

### 博客编辑器 采用开源的editor.md
![](https://i.imgur.com/PqxEg1Y.png)

## 本地引入jar包
- 安装命令


mvn install:install-file -Dfile=D:\code\context\ssmBlog\src\vuejar\vue-adminlte4j-1.0.1-SNAPSHOT.jar -DgroupId=com.vue.adminlte4j -DartifactId=vue-adminlte4j  -Dversion=1.0.1-SNAPSHOT -Dpackaging=jar

引入:
	<dependency>
			<groupId>com.vue.adminlte4j</groupId>
			<artifactId>vue-adminlte4j</artifactId>
			<version>1.0.1-SNAPSHOT</version>
		</dependency>

## 后台完成日志
12-19日<br>
1.前期环境选择搭建和大致情况设计完毕

12-20日<br>
1. 后台登录页面检查用户登录
2. 后台管理页面前端

12-21日<br>
1. 后台登录简单效验<br>
		用户名:`admin`  
		密码:`admin`

12-24日<br>
1.  把h2数据库更改成mysql
2.  增加mybatis 使用注解查询更改数据库
3.  使用


12-26日<br>
1.  完后后端对博客的和博客标签的各种管理功能
2.  

12-27日<br>
1.  搭建前端 连接前端login和后台
2.  测试好editor.md文本编辑器 加入成功 在/admin/testeditor.html


12- 28日<br>
1. 登陆校验在前端，并前端js加密密码
2. 增加session验证和拦截器拦截 以免访问不该访问的界面

12-29r<br>
1. 前后端完全分离，拦截器的bug修复了
2. 编写前端的博客按年显示后台功能
3. 存在问题 有bug 能查出ID 但是不能显示 改js

18 年1-3日<br>
1. 基本前后台功能全部完善
2. 调改出更改bolg md 编辑器传值问题

18 年 1-10日<br>
1. 删除自己的拦截器 
2.删除冗余代码
3. 加入Kisso单点登录
## 预计改进
1. 登录在前端效验 前后端完全分离<br>
2. 目前是h2数据库 改为mysql，mybatis管理使用注解更快更方便
3. 使用.yml格式配置文件代替apptioncation.properties
4. 在Service增加事务管理





1.前台根据日期 标签显示博客基本完成
2.后台添加博客完成 粗糙v1.0完成
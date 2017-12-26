
## 基本技术

环境: `Springboot+mysql+admintle+vue.js+mybatis`环境搭建的个人博客


## 后台实现
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
## 预计改进
1. 登录在前端效验 前后端完全分离<br>
2. 目前是h2数据库 改为mysql，mybatis管理使用注解更快更方便
3. 使用.yml格式配置文件代替apptioncation.properties
4. 在Service增加事务管理
配置更方便
##后台登录界面
 ![](https://i.imgur.com/axudahd.png)

##后台管理界面
![](https://i.imgur.com/A7dJ0IM.png)


## 本地引入jar包
- 安装命令


mvn install:install-file -Dfile=D:\code\blog\ssmBlog\src\vuejar\vue-test-1.0.1-SNAPSHOT.jar -DgroupId=com.blog.vue -DartifactId=vue-test  -Dversion=1.0.1-SNAPSHOT -Dpackaging=jar

<groupId>com.blog.vue</groupId>
    <artifactId>vue-test</artifactId>
    <version>1.0.1-SNAPSHOT</version>

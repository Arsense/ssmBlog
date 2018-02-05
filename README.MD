
## 基本技术

环境: `Springboot+mysql+admintle+vue.js+mybatis`环境搭建的个博客

### 搭建方法在下面



- 1月3日  v1.0
- 1月10日 v1.1   加入kisso单点登录 删除自己拦截器
- 1.11   v1.2   前台能解析mdtext文本，后台的editor.md加入到VUE框架中，后面editor界面BUG修改成功
- 2.5 后台大更新
## 前台 白天版本


![](https://i.imgur.com/lL7KoN8.jpg)
## 前台 夜晚版本

![](https://i.imgur.com/Dhk0Mvl.jpg)
## 后台登录界面
 ![](https://i.imgur.com/axudahd.png)

## 后台首页
![](https://i.imgur.com/H3bdd0E.png)

### 博客列表显示
![](https://i.imgur.com/y5rueJm.jpg)

### 博客编辑器 采用开源的editor.md
![](https://i.imgur.com/zlX2im9.png)

### 开发中 页面管理 标签管理
![](https://i.imgur.com/naoKzqd.png)
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
2. 目前是h2数据库 改为mysql，mybatis管理使用注解更快更方便
4. 在Service增加事务管理



### 本地搭建
1. 使用IDEA编辑器 打开文件中pom.xml文件
2. maven运行编辑 
在编辑器按 `alt+12` 打开终端 输入下面命令  记得路径要改为自己工程里面vue-adminlte4j-1.0.1-SNAPSHOT.jar文件包的路径
```

mvn install:install-file -Dfile=D:\code\context\ssmBlog\src\vuejar\vue-adminlte4j-1.0.1-SNAPSHOT.jar -DgroupId=com.vue.adminlte4j -DartifactId=vue-adminlte4j  -Dversion=1.0.1-SNAPSHOT -Dpackaging=jar
```
然后依次按下图点击 clean 清除原先的包 编译导入包 安装依赖到工程中
![](https://i.imgur.com/p2XL4kj.jpg)
3. 运行 在自己mysql服务上创建名为`ssmBlog`数据库
4. 把下图sql语句在`ssmBlog`中运行 自动生成数据和表
5. 在 application.yml文件中 更改自己的数据库密码和用户名
6. 点击下图Applcation类运行  访问 `http://localhost:8002 `即可
端口配置在 application.yml文件中 
![](https://i.imgur.com/HWemR8y.jpg)
### 远程搭建
待更新....

项目maven package打包成war包 放到相应tomcat工作目录 运行tomcat


### 版本控制  

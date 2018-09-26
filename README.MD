

## 基本技术

环境: `Springboot+mysql+admintle+vue.js+mybatis`环境搭建的个博客

### 搭建方法在下面


- 1月3日  v1.0
- 1月10日 v1.1   加入kisso单点登录 删除自己拦截器
- 1.11   v1.2   前台能解析mdtext文本，后台的editor.md加入到VUE框架中，后面editor界面BUG修改成功
- 2.5 后台大更新
- 2018 7月27日 代码排版优化 增加阅读性
- 9.25 依赖的vue-admintle工程性能各种界面大更新 jar包依賴不斷更新中


## 前台首页
![](doc/9.png)
## 首页右边分类浏览
![](doc/10.png)
## 文章预览 访问量和评论 分享

![](doc/11.png)

![](doc/12.png)


## 后台登录界面
 ![](https://i.imgur.com/axudahd.png)

## 后台首页
![](doc/1.png)

### 博客列表显示
![](doc/3.png)

### 博客编辑器 采用开源的editor.md
![](doc/2.png)
## 后台评论管理页面
![](doc/4.png)

### 页面管理 标签管理
![](doc/6.png)
## 后台配置管理
### 配置页面信息
![](doc/7.png)
### 添加菜单和子菜单
![](doc/8.png)


### 本地搭建 
运行成功后访问:http://localhost:8002
后台是http://localhost:8002/login1.html
1. 使用IDEA编辑器 打开文件中pom.xml文件
2. 引入本地jar包依赖
maven运行编辑 
方法一
在编辑器按 `alt+12` 打开终端 输入下面命令  记得路径要改为自己工程里面vue-adminlte4j-1.0.1-SNAPSHOT.jar文件包的路径
```
mvn install:install-file -Dfile=D:\code\blog\ssmBlog\src\vuejar\vue-adminlte4j-1.0.1-SNAPSHOT.jar -DgroupId=com.vue.adminlte4j -DartifactId=vue-adminlte4j  -Dversion=1.0.1-SNAPSHOT -Dpackaging=jar
```
然后依次按下图点击 clean 清除原先的包 编译导入包 安装依赖到工程中

方法2 其实就是引入该jar包原工程
安装辅助工程
https://github.com/Arsense/vue-adminlte4j
安装过程见其README
运行test 下面ApplicationStarter 访问地址 http://localhost:8080/index.html
有页面即为安装成功,然后用maven工具打包安装到本地

pom.xml引入:
	<dependency>
			<groupId>com.vue.adminlte4j</groupId>
			<artifactId>vue-adminlte4j</artifactId>
			<version>1.0.1-SNAPSHOT</version>
	</dependency>
![](https://i.imgur.com/p2XL4kj.jpg)
3. 运行 在自己mysql服务上创建名为`ssmBlog`数据库
4. 把下图sql语句在`ssmBlog`中运行 自动生成数据和表
5. 在 application.yml文件中 更改自己的数据库密码和用户名
6. 点击下图Applcation类运行  访问 `http://localhost:8002 `即可
端口配置在 application.yml文件中 
7.后台路径 加 "login1.html"
![](https://i.imgur.com/HWemR8y.jpg)
### 远程搭建
待更新....

项目maven package打包成war包 放到相应tomcat工作目录 运行tomcat
### 默认账号名密码都是 admin admin  路劲是login1/html 如果想使用请数据库直接修改
目前还没增加此功能

### 联系方式

QQ：1984562796 欢迎交流学习

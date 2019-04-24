# 简介

这是一个nutzboot+gradle+kotlin工程,idea可直接导入

MainLauncher是入口,启动即可

本项目是基于从[https://get.nutz.io](https://get.nutz.io)导出的demo工程改造而来

添加的依赖如下：

- Jetty容器
- Nutz.Mvc
- Jdbc 传统数据源
- Nutz.Dao

## 环境要求

* 必须JDK8+
* idea开发工具

## 配置信息位置

数据库配置信息,jetty端口等配置信息,均位于src/main/resources/application.properties

## 项目打包成FatJar

Linux
```
./gradlew release
```

Windows
```
./gradlew.bat release
```

### 运行FatJar

```
java -jar build/libs/nutz-demo-1.0.jar
```

如果启动失败，请尝试重新打包FatJar


## 相关资源

* 论坛: https://nutz.cn
* 官网: https://nutz.io
* 一键生成NB的项目: https://get.nutz.io

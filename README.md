# Spring Boot 用户数据展示项目

这是一个 Spring Boot 项目，用于从文本文件读取用户数据并在网页上展示。

## 项目结构

```
javaspringboot/
├── pom.xml                                    # Maven 配置文件
├── README.md                                  # 项目说明文档
└── src/
    └── main/
        ├── java/
        │   └── com/example/
        │       ├── SpringBootApplication.java # Spring Boot 主启动类
        │       ├── controller/
        │       │   └── UserController.java    # 用户控制器
        │       ├── entity/
        │       │   └── User.java              # 用户实体类
        │       └── service/
        │           └── UserService.java       # 用户服务类
        └── resources/
            ├── application.properties         # 应用配置文件
            ├── user.txt                       # 用户数据文件
            └── static/
                └── user.html                  # 前端静态页面
```

## 功能特性

- ✅ Spring Boot Web 依赖
- ✅ Lombok 支持
- ✅ 从文本文件读取用户数据
- ✅ RESTful API 接口 (`/list`)
- ✅ 前端页面展示用户数据表格

## 运行步骤

### 1. 编译项目

```bash
cd javaspringboot
mvn clean compile
```

### 2. 运行项目

```bash
mvn spring-boot:run
```

或者打包后运行：

```bash
mvn clean package
java -jar target/javaspringboot-1.0-SNAPSHOT.jar
```

### 3. 访问应用

- 前端页面：http://localhost:8080/user.html
- API 接口：http://localhost:8080/list

## API 接口说明

### GET /list

获取用户列表数据

**响应示例：**
```json
[
  {
    "id": 1,
    "username": "daqiao",
    "password": "1234567890",
    "name": "大乔",
    "age": 22,
    "updateTime": "2024-07-15T15:05:45"
  },
  ...
]
```

## 技术栈

- Java 17
- Spring Boot 3.2.0
- Lombok
- Maven

## 注意事项

1. 确保已安装 Java 17 或更高版本
2. 确保已安装 Maven
3. 用户数据文件 `user.txt` 位于 `src/main/resources/` 目录下
4. 前端页面会自动调用 `/list` 接口获取数据并展示



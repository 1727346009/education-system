实体：
- 学生：学号（id），学生名字（name），密码（password），班级号（class_id）
- 班级：班级号（id），导师工号（teacher_id），所在系(dept）
- 导师：工号（id），密码（password）

功能；
- 学生登录：
    - request body：账号、密码
    - URL： /student/login
    - return：认证令牌
- 导师登录：账号、密码
    - request body：账号、密码
    - URL: /teacher/login
    - return：认证令牌
- 学生个人信息查询：
    - request params：认证令牌
    - URL：/student
- 班级信息查询：
    - request params：认证令牌
    - URL：/students
- 自定义异常处理
- 数据库密码密文加密
  
认证令牌生成：
- 拦截器解析获取 ip 地址
- 登录成功缓存 id，ip 键值对
- 生成 jwt 令牌

项目结构：    
- com.buko.education
    - annotation (自定义注解)
    - config (配置类)
    - controller (拦截器)
    - dao (持久层)
    - dto (数据传输对象)
    - enumerate (枚举类)
    - exception (异常类)
    - filter (过滤器)
    - interceptor (拦截器)
    - po (实体类)
    - service (服务)
    - vo (视图对象)
    - EducationApplication.class
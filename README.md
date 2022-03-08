# nowcoder-community
# 项目介绍

一个仿照牛客网实现的讨论社区，不仅实现了基本的注册，登录，发帖，评论，点赞，回复功能，同时使用前缀树实现敏感词过滤。

# 功能简介

* 通过对用户颁发登录凭证，记录用户登录状态，使用拦截器对用户状态判断，解决Http无状态的缺陷
* 使用Redis的set实现点赞，zset实现关注，并使用Redis存储登录ticket和验证码，解决分布式session问题。
* 使用Kafka处理发送评论、点赞和关注等系统通知，并使用事件进行封装，构建了强大的异步消息系统。
* 使用前缀树数据结构实现了对于帖子内容，用户评论的关键字过滤。
* 使用布隆过滤器数据结构对请求进行过滤，避免了缓存穿透。 



# 开发环境

| 工具  | 版本号 | 下载                               |
| ----- | ------ | ---------------------------------- |
| JDK   | 11     | https://openjdk.java.net/install/  |
| Mysql | 5.7    | https://www.mysql.com/             |
| Redis | 3.2    | https://redis.io/download          |
| Kafka | 2.3.0  | https://kafka.apache.org/downloads |



# 运行效果展示

* 首页

![image-20220308211806836](https://raw.githubusercontent.com/Pyhita/picture/main/2022/1/image-20220308211806836.png)

* 消息

![image-20220308211846206](https://raw.githubusercontent.com/Pyhita/picture/main/2022/1/image-20220308211846206.png)

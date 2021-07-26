# proxy-debug
用于有代理机的java项目远程调试（只支持SOCKS5）

### 项目实现的逻辑大致如下

#### project architecture

![Project Architecture](./architecture.png)

代理机（堡垒机，跳板机）SOCKS代理的是主机A，proxy-debug相当于代理的主机B，两者互通，就可以了。

#### 注：

1. proxy-debug相当于在本地映射了一个端口
2. 项目中虽然加了h2，但没有使用，用了一个Map来存储的映射
3. 如果代理机使用了http代理，可以使用IDEA插件Alibaba Cloud Toolkit

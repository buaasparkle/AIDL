AIDL
====

### 项目说明
一个简单的学习 aidl 的项目

定义一个 IMath.aidl 文件，其中提供两个 int 型求和的接口。
server 端通过 MathService 返回的 binder 实现该接口。
client 端通过 bindService 获取接口能力。

server/client 代表不同的 app，运行在不同的进程中。
因为 server 端只有一个 service，所以运行起来时无界面。
client 端启动后页面展示一个 bind/unbind 的 button。

运行过程 UI 上无状态变化，通过 Log 日志输出，TAG 为 Tag.me("--sure--")，可依此检索查看。

项目结构:
- app：作为 service 的项目，service 所在
- client：顾名思义，作为客户端调用远程 service
- mylib：app/client 的公共库，定义 aidl 接口


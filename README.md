###  组件化项目

Android 组件化项目，该项目由两部分组成，这是主要架子部分（简称：宿主）

#### 特性
1. [mvp-clean](https://github.com/Leeii/mvp-clean-sdk)架构，充分解耦
2. [插件](https://github.com/Leeii/MVP-plugin-project)生成mvp模板代码，省时省力
3. Dagger 依赖注入，充分解耦
4. [模块](https://github.com/Leeii/LModuleProject)独立运行，利于调试
5. 利用gradle配置，自动依赖aar
6. 通过ARouter自动注册模块，免去每集成一个模块需要修改代码
7. [路由统一管理](https://github.com/Leeii/Router-Manager)，清晰明了
8. 支持项目多baseUrl
9. ...

#### 项目结构
- app
    - modules
        - debug 存放调试版本的modules module-*-debug.aar
        - other 其他三方aar
        - release 存放正式版本的modules module-*-release.arr
    
#### 

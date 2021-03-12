# 基于JetPack搭建的MVVM开发架构

## app

app 业务代码模块

业务模块采用 MVVM 架构，基于 [Android JetPack架构组件](https://developer.android.com/topic/libraries/architecture/)


按照业务划分文件包目录，每个业务包下面区分ui、viewModel、Model。
使用databinding viewmodel livadata Androidx依赖库让你的开发简单、高效

## base

公共基础模块，网络
第三方类库
- [paho mqtt client](https://github.com/eclipse/paho.mqtt.android)
- [Logger](https://github.com/orhanobut/logger)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit](https://github.com/square/retrofit)
- [Gson](https://github.com/google/gson)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [EventBus](https://github.com/greenrobot/eventbus)
- [RxPermissions](https://github.com/tbruyelle/RxPermissions)

## uicom

公共UI组件
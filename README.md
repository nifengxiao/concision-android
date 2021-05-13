# Concision

### 快速创建mvvm相关功能
[concision-template](https://github.com/nifengxiao/concision-template)

### 宗旨

打造简洁、易用、高效的快速开发模板！

### 优势

- 封装各种实际开发过程中重复、繁杂的操作。
- 配置集中化，方便配置。
- 集成易用框架并实现完整逻辑，只需一次调用，即可实现大量代码。

### 架构说明

#### 语言 

- Kotlin
- java

#### 分包说明
  三层
  - concision 核心框架 定义基础框架与三方
  - config 配置 用于项目公共配置使用 如：项目主题配置、app缓存信息、用户缓存信息
  - project 项目相关

#### 架构与核心

- MVVM

- 基础
  - Lifecycle （管理生命周期）

  - Databinding （页面与逻辑之间的交互使用）

  - LiveData （数据处理）

  - Navigation（主要的页面构建）

   - 网络相关
     - retrofit+okhttp3 （网络库）
     - retrofit-url-manager（动态替换BaseUrl库）
     - logging-interceptor(日志拦截器)

#### 三方

- 多媒体
  - Fresco (图片库)
  - Luban (图片压缩)
  - PictureSelector (图片选择)
- 工具相关
  - utilcodex (工具集)
  - permissionx (动态权限)
  - update-app-kotlin(app更新)
- UI相关
  - autosize(ui适配)
  - BaseRecyclerViewAdapterHelper(好用的recyclerview适配器)
  - SmartRefreshLayout(简单易用刷新加载框架)
  - loadsir（状态管理）
  - material-dailog(基于材料设计下的dialog)
  - CustomPopwindow(简单易用popwindow)
  - immersionbar(状态栏)
- H5相关
  - AgentWeb

#### 使用方法

- Activity（全局只有一个MainActivity）
    - 继承 BaseActivity
    - 配置一个ViewModel
- Fragment
    - 继承 BaseFragment
    - 配置一个ViewModel
    - 配置导航的位置在navigation中的navigation.xml里面
- viewModel
    - 继承 BaseViewModel
    - 一个是界面数据相关的viewModel，一个是获取数据所需要的viewModel
    - 这里的设计是把页面数据的viewModel和数据相关的viewModel分开写了，有一个好处，可灵活调配数据的viewModel，一是可以减少重复代码，一是为了让结构更加清晰
- 数据请求
    - 域名配置 config->assets->urls.json 
    - 域名切换 具体配置已在config->utils->UrlUtils写好
    - 网络请求构建  config->network->NetworkApi
    - 网络请求公共参数配置 config->network->ConfigInterceptor
    - 网络请求基类 config->data->model->base->BaseApiBean、BaseListBean(请根据自己项目结构进行定制)
    - Api配置 app->network->ApiService
    - 网络请求管理器 app->network->HttpRequestManger
    - 网络请求封装扩展函数 concision->ext->BaseViewModelExt
    -  request ( 请在viewmodel中调用)
- 其余公共配置
    - 页面状态配置 config->res->layout->layout_empty、layout_error、layout_loading
    - 加载框配置   config->res->layout->layout_custom_progress_dialog_view
    - 标题 config->res->layout->include_toolbar
    - 确认弹框  config->utils->MessageExt
    - 条件选择器 config->utils->PickerUtils
    - 上拉加载下拉刷新 config->app->App
    - 版本控制 config.gradle
    - 三方包管理 config.gradle
    - 环境配置 config->build.gradle
- next ：app更新、权限控制、文件上传下载、图片预览、视频播放

#### 感谢
[JetpackMvvm](https://github.com/hegaojian/JetpackMvvm)

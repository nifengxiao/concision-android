# Concision

### 宗旨

打造简洁、易用、高效的快速开发框架！

### 优势

- 封装各种实际开发过程中重复、繁杂的操作，把复杂的问题交给框架处理。
- 配置集中化，免去寻找配置所造成的时间成本浪费。
- 集成易用框架并实现完整逻辑，只需一次调用，即可实现大量代码。

### 架构说明

#### 语言 

- Kotlin
- java

#### 架构

- MVVM

#### 三方

- 基础
  - Lifecycle （管理生命周期）
  - Databinding （页面与逻辑之间的交互使用）
  - LiveData （数据处理）
  - Navigation（主要的页面构建）
- 网络相关
  - retrofit+okhttp3 （网络库）
  - retrofit-url-manager（动态替换BaseUrl库）
  - logging-interceptor(日志拦截器)
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
- H5相关
  - AgentWeb
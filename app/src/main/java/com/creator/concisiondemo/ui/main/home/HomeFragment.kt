package com.creator.concisiondemo.ui.main.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.ext.nav
import com.creator.concision.ext.navigateAction
import com.creator.concisiondemo.R
import com.creator.config.app.base.BaseFragment
import com.creator.concisiondemo.data.model.bean.ArticleBean
import com.creator.concisiondemo.databinding.FragmentHomeBinding
import com.creator.concisiondemo.ui.adapter.ArticleAdapter
import com.creator.config.utils.showLoading
import com.creator.concisiondemo.viewmodel.request.RequestHomeViewModel
import com.creator.concision.weight.recyclerview.SpaceItemDecoration
import com.creator.concisiondemo.data.model.bean.WebBean
import com.creator.concisiondemo.utils.openStatusBar
import com.creator.config.utils.loadListData
import com.creator.config.utils.showMessage
import com.gyf.immersionbar.ImmersionBar
import com.kingja.loadsir.callback.Callback
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    主页
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    //请求数据ViewModel
    private val requestHomeViewModel: RequestHomeViewModel by viewModels()

    //文章
    private val articleAdapter: ArticleAdapter by lazy { ArticleAdapter() }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {

        //初始化状态页
        loadSirInit(refresh, onReloadListener = Callback.OnReloadListener {
            requestHomeViewModel.getHomeData(true)
        })

        //配置Recyclerview
        rc.let {
            it.adapter = articleAdapter
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f), true))
        }

        //初始化刷新
        refresh.setOnRefreshListener {
            requestHomeViewModel.getHomeData(true)
        }

        //初始化加载
        refresh.setOnLoadMoreListener {
            requestHomeViewModel.getHomeData(false)
        }

        //点击事件
        articleAdapter.setOnItemClickListener { adapter, view, position ->
            val articleBean = adapter.data[position] as ArticleBean
            val bundle = Bundle()
            bundle.putParcelable("web", WebBean(articleBean.chapterName, articleBean.link))
            nav().navigateAction(R.id.action_to_web, bundle)
        }
    }


    override fun lazyLoadData() {
        super.lazyLoadData()
        //第一次进入需要开启loading,加载完成后翻转则不需要
        if (!requestHomeViewModel.isLoadingSuccess) {
            loadSir.showLoading()
        }

        requestHomeViewModel.getHomeData(isRefresh = true, isInit = true)
    }

    override fun createObserver() {
        requestHomeViewModel.run {
            articleData.observe(viewLifecycleOwner, Observer {
                LogUtils.i("baseListBean$it")
                loadListData(it, articleAdapter, loadSir, rc, refresh)
            })
        }
    }

    override fun openDefaultImmersionBar(): Boolean {
        this.openStatusBar(refresh)
        return false
    }
}
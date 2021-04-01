package com.creator.concisiondemo.ui.main.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ConvertUtils
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
import com.kingja.loadsir.callback.Callback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    主页
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    //请求数据ViewModel
    private val requestHomeViewModel: RequestHomeViewModel by viewModels()

    //文章
    @Inject
    lateinit var articleAdapter: ArticleAdapter

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        //初始化状态页
        loadSirInit(refresh, onReloadListener = Callback.OnReloadListener {
            refresh()
        })

        //配置Recyclerview
        rc.let {
            it.adapter = articleAdapter
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f), true))
        }

        //初始化刷新
        refresh.setOnRefreshListener {
            refresh()
        }

        //初始化加载
        refresh.setOnLoadMoreListener {
            requestHomeViewModel.getHomeData(false)
        }

        //点击事件
        articleAdapter.setOnItemClickListener { adapter, _, position ->
            val articleBean = adapter.data[position] as ArticleBean
            val bundle = Bundle()
            bundle.putParcelable("web", WebBean(articleBean.chapterName, articleBean.link))
            nav().navigateAction(R.id.action_to_web, bundle)
        }

    }

    override fun refresh() {
        super.refresh()
        requestHomeViewModel.getHomeData(true)
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        loadSir.showLoading()
        refresh()
    }

    override fun createObserver() {
        requestHomeViewModel.run {
            articleData.observe(viewLifecycleOwner, Observer {
                loadListData(it, articleAdapter, loadSir, refresh)
            })
        }
    }

    override fun openDefaultImmersionBar(): Boolean {
        this.openStatusBar(refresh)
        return false
    }
}
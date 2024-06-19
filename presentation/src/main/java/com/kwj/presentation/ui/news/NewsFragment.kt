package com.kwj.presentation.ui.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwj.domain.model.NewsItem
import com.kwj.presentation.R
import com.kwj.presentation.base.BaseFragment
import com.kwj.presentation.databinding.FragmentNewsBinding
import com.kwj.presentation.ui.detail.WebViewActivity
import com.kwj.presentation.ui.news.adapter.NewsListAdpater
import com.kwj.presentation.ui.news.adapter.util.LastItemPaddingDecoration
import com.kwj.presentation.util.ext.gone
import com.kwj.presentation.util.ext.text
import com.kwj.presentation.util.ext.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * 뉴스 리스트 화면을 표시하기 위한 Fragment 클래스입니다.
 * 이 클래스는 기본적으로 BaseFragment를 상속받고, FragmentListBinding 이용하여 레이아웃을 바인딩합니다.
 *
 * @author (김위진)
 * @since (2024-06-17)
 */
@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModels()

    private lateinit var newsListAdpater: NewsListAdpater

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsBinding {
        return FragmentNewsBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initListView()
        initRetryView()
        observeViewState()
    }

    private fun initListView() {
        newsListAdpater = NewsListAdpater { newsItem ->
            val intent = Intent(context, WebViewActivity::class.java).apply {
                putExtra("url", newsItem.url)
            }
            requireActivity().startActivity(intent)

            viewModel.saveClickedItem(newsItem)
        }

        binding.rvNews.apply {
            setHasFixedSize(true)
            val spanCount = if (resources.configuration.screenWidthDp >= 600) 3 else 1
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            adapter = newsListAdpater

            val paddingInPixels = resources.getDimensionPixelSize(R.dimen.recyclerview_last_item_padding)
            addItemDecoration(LastItemPaddingDecoration(paddingInPixels))
        }

        binding.rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val fab = binding.btFab
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val currentPosition = layoutManager.findFirstVisibleItemPosition()

                if (currentPosition < 5) {
                    fab.hide()
                    return
                }

                if (dy > 5 && fab.isShown) {
                    fab.hide()
                }

                if (dy < -5 && !fab.isShown) {
                    fab.show()
                }
            }
        })

        binding.btFab.hide()
        binding.btFab.setOnClickListener {
            if (newsListAdpater.itemCount > 10 ) {
                binding.rvNews.scrollToPosition(0)
            } else {
                binding.rvNews.smoothScrollToPosition(0)
            }
        }
    }

    private fun initRetryView() {
        binding.btRetry.setOnClickListener {
            loadData()
        }
    }

    private fun observeViewState() {
        viewModel.state.onEach { state ->
            when (state) {
                NewsViewState.Empty -> showError(requireContext().text(R.string.msg_result_empty_text))
                is NewsViewState.Loading -> showLoading()
                is NewsViewState.GetNewsList -> showData(state.newsList)
                is NewsViewState.Error -> showError(state.message)
            }
        }.launchIn(lifecycleScope)
    }

    private fun showLoading() {
        binding.apply {
            progress.show()
            rvNews.gone()
            tvMessage.gone()
            btRetry.gone()
        }
    }

    private fun showData(imageInfos: List<NewsItem>) {
        binding.apply {
            progress.hide()
            rvNews.visible()
            tvMessage.gone()
            btRetry.gone()
        }
        newsListAdpater.submitList(imageInfos)
    }

    private fun showError(message: String?) {
        binding.apply {
            progress.hide()
            rvNews.gone()
            tvMessage.visible()
            btRetry.visible()
            tvMessage.text = message
        }
    }

    override fun loadData() {
        viewModel.getNews()
    }
}
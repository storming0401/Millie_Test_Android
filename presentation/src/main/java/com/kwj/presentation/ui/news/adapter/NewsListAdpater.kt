package com.kwj.presentation.ui.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kwj.domain.model.NewsItem

/**
 * News RecyclerView를 표시하기 위한 어댑터 클래스 입니다.
 *
 * @author (김위진)
 * @since (2024-06-17)
 */
class NewsListAdpater constructor(
    private val itemClickListener: (NewsItem) -> Unit
): ListAdapter<NewsItem, NewsItemViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem.filePath == newItem.filePath
            }

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
                oldItem == newItem
        }
    }
}
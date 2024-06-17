package com.kwj.presentation.ui.main.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kwj.domain.model.NewsItem
import com.kwj.presentation.R
import com.kwj.presentation.databinding.ItemNewsBinding

/**
 * News RecyclerView의 Item 항목을 나타내기 위한 ViewHolder 클래스 입니다.
 *
 * @author (김위진)
 * @since (2024-06-17)
 */
class NewsItemViewHolder (
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(newsItem: NewsItem?) {
        if (newsItem == null) return

        binding.newsItem = newsItem
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): NewsItemViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            val binding = ItemNewsBinding.bind(view)
            return NewsItemViewHolder(binding)
        }
    }
}
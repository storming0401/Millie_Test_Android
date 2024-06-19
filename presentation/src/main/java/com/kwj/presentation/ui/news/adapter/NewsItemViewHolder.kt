package com.kwj.presentation.ui.news.adapter

import android.graphics.Color
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

    fun bind(newsItem: NewsItem?, itemClickListener: (NewsItem) -> Unit) {
        if (newsItem == null) return

        binding.newsItem = newsItem
        binding.executePendingBindings()

        binding.tvTitle.setTextColor(Color.BLACK)
        binding.rootLayout.setOnClickListener {
            binding.tvTitle.setTextColor(Color.RED)
            itemClickListener(newsItem)
        }
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
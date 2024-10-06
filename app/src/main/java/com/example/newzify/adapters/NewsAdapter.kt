package com.example.newzify.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newzify.R
import com.example.newzify.modelclass.NewsArticle

class NewsAdapter (private val context: Context, private val articles: List<NewsArticle>) : BaseAdapter() {

    override fun getCount(): Int = articles.size
    override fun getItem(position: Int): NewsArticle = articles[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.display_news_row, parent, false)



        val article = articles[position]
        val imageView: ImageView = view.findViewById(R.id.newsImage)
        val authorTextView: TextView = view.findViewById(R.id.author)
        val titleTextView: TextView = view.findViewById(R.id.title)
        val descriptionTextView: TextView = view.findViewById(R.id.discription)

        authorTextView.text = article.author
        titleTextView.text = article.title
        descriptionTextView.text = article.description

        Glide.with(context).load(article.urlToImage).into(imageView)  // Load image with Glide

        return view
    }
}
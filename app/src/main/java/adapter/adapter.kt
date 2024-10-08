package adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newzify.R
import models.Article

class adapter(private val context: Context, private val articles: List<Article>) : ArrayAdapter<Article>(context, 0, articles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.newslayout, parent, false)
        }

        val article = articles[position]

        val titleTextView = listItemView?.findViewById<TextView>(R.id.newsTitle)
        val descriptionTextView = listItemView?.findViewById<TextView>(R.id.newsDescription)
        val imageView = listItemView?.findViewById<ImageView>(R.id.newsImage)

        titleTextView?.text = article.title
        descriptionTextView?.text = article.description


        if (imageView != null && !article.urlToImage.isNullOrEmpty()) {
            Glide.with(context)
                .load(article.urlToImage)
                .thumbnail(0.1f)
                .into(imageView)
        }
        return listItemView!!
    }


}
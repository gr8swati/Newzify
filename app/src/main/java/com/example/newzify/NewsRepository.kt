package com.example.newzify
import com.example.newzify.modelclass.NewsArticle
import com.example.newzify.modelclass.NewsResponse
import okhttp3.*
import com.google.gson.Gson
import java.io.IOException

class NewsRepository {
    private val client = OkHttpClient()
    private val apiKey = "2528346ad1c84279a4099c8a53b6709a"
    private val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=$apiKey"

    fun fetchNews(callback: (List<NewsArticle>?) -> Unit) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonResponse ->
                    val newsResponse = Gson().fromJson(jsonResponse, NewsResponse::class.java)
                    callback(newsResponse.articles)
                }
            }
        })
    }
}
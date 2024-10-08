package com.example.newzify.activities
import adapter.adapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import com.example.newzify.R

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import models.NewsResponse

import org.json.JSONArray
import retrofit.Call
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit
import retrofitInstance

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var progressBar:ProgressBar
    private val apiKey = "80d514e546fa4f38a32e965345df7ea2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var chipPolitics = findViewById<Chip>(R.id.politics_chip)
        var chipTechnologies = findViewById<Chip>(R.id.technologies_chip)
        var chipSports = findViewById<Chip>(R.id.sports_chip)
        var chipBusiness = findViewById<Chip>(R.id.bussiness_chip)

        listView = findViewById(R.id.news_feed_frame)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        chipPolitics.setOnClickListener({
            fetchNews("health")
        })
        chipSports.setOnClickListener({
            fetchNews("Sports")
        })
        chipBusiness.setOnClickListener({
            fetchNews("business")

        })
        chipTechnologies.setOnClickListener({
            fetchNews("technology")
        })

        // Load initial news
        fetchNews("business")

        // Change category based on Chip selection

    }

    private fun fetchNews(category: String) {
        retrofitInstance.api.getTopHeadlines(
            country = "us",
            category = category,
            apiKey = apiKey
        ).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(response: Response<NewsResponse>?, retrofit: Retrofit?) {
                if (response != null) {
                    if (response.isSuccess) {
                        val newsResponse = response.body()
                        newsResponse?.articles?.let {
                            // Set the news articles in the ListView
                            val adapter = adapter(this@MainActivity, it)
                            listView.adapter = adapter
                        }
                    } else {
                        Log.e("API Error", "Response Code: ${response.code()}")
                    }
                }
            }

            override fun onFailure(t: Throwable?) {
                progressBar.visibility = View.GONE
                if (t != null) {
                    Log.e("API Failure", "Error: ${t.message}")
                }
            }
        })
    }

}

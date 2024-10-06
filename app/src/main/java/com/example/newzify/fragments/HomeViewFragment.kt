package com.example.newzify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.example.newzify.NewsRepository
import com.example.newzify.R
import com.example.newzify.adapters.NewsAdapter

class HomeViewFragment : Fragment() {
    private lateinit var newsListView: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ListView
        newsListView = view.findViewById(R.id.newsListView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_view, container, false)
        fetchNews()
        return view
    }

    private fun fetchNews() {
        val repository = NewsRepository()
        repository.fetchNews { articles ->
            articles?.let {
                val adapter = NewsAdapter(requireContext(), it)
                activity?.runOnUiThread {
                    newsListView.adapter = adapter
                }
            }
        }
    }



}
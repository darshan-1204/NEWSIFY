package com.example.newsify.Fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsify.Adapter.SearchNewsAdapter
import com.example.newsify.Api.ApiClient
import com.example.newsify.Api.ApiInterface
import com.example.newsify.Model.NewsResponse
import com.example.newsify.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.svSearchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String?): Boolean {
                loadNews(search.toString())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

    }

    private fun loadNews(query: String) {
        // Implement the logic for loading news based on the selected category (similar to MainActivity)

        binding.progressBar.visibility = View.VISIBLE
        binding.rcvSearch.visibility = View.GONE


        var apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        apiInterface.searchNews("pub_29355651563b2d57519c342a9ec9590b0b109", "hi", "in", query)
            .enqueue(object :
                Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {

                    if (response.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        binding.rcvSearch.visibility = View.VISIBLE

                        var data = response.body()
                        var data2 = data?.results
                        Log.e(ContentValues.TAG, "onResponse: ===========${data2}")

                        binding.rcvSearch.layoutManager = LinearLayoutManager(context)
                        binding.rcvSearch.adapter = SearchNewsAdapter(data2)
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE

                    Log.d(ContentValues.TAG, "onFailure: No data found " + t.message)
                }

            })
    }


}
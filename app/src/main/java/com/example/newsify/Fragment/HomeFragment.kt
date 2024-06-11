package com.example.newsify.Fragment

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsify.Adapter.BreakingNewsAdapter
import com.example.newsify.Adapter.NewsAdapter
import com.example.newsify.Api.ApiClient
import com.example.newsify.Api.ApiInterface
import com.example.newsify.Model.NewsResponse
import com.example.newsify.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var colorCode: String
    private lateinit var colorCode2: String
    private lateinit var savedLanguageCode: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorCode = "#FF5300"

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                colorCode2 = "#FFFFFF"
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                colorCode2 = "#000000"
            }
        }

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", 0)
        editor = sharedPreferences.edit()

        savedLanguageCode = sharedPreferences.getString("language_code", "en").toString()


        binding.sports.setOnClickListener {
            loadNews("sports", savedLanguageCode)

            binding.sports.setTextColor(Color.parseColor(colorCode))
            binding.business.setTextColor(Color.parseColor(colorCode2))
            binding.health.setTextColor(Color.parseColor(colorCode2))
            binding.entertainment.setTextColor(Color.parseColor(colorCode2))
            binding.science.setTextColor(Color.parseColor(colorCode2))
            binding.technology.setTextColor(Color.parseColor(colorCode2))


            binding.lineSport.visibility = View.VISIBLE
            binding.lineBusiness.visibility = View.GONE
            binding.lineEntertainment.visibility = View.GONE
            binding.lineHealth.visibility = View.GONE
            binding.lineTechnology.visibility = View.GONE
            binding.lineScience.visibility = View.GONE

        }
        binding.technology.setOnClickListener {
            loadNews("technology", savedLanguageCode)

            binding.technology.setTextColor(Color.parseColor(colorCode))
            binding.business.setTextColor(Color.parseColor(colorCode2))
            binding.health.setTextColor(Color.parseColor(colorCode2))
            binding.entertainment.setTextColor(Color.parseColor(colorCode2))
            binding.science.setTextColor(Color.parseColor(colorCode2))
            binding.sports.setTextColor(Color.parseColor(colorCode2))

            binding.lineTechnology.visibility = View.VISIBLE
            binding.lineBusiness.visibility = View.GONE
            binding.lineEntertainment.visibility = View.GONE
            binding.lineHealth.visibility = View.GONE
            binding.lineSport.visibility = View.GONE
            binding.lineScience.visibility = View.GONE

        }
        binding.business.setOnClickListener {
            loadNews("business", savedLanguageCode)

            binding.business.setTextColor(Color.parseColor(colorCode))
            binding.sports.setTextColor(Color.parseColor(colorCode2))
            binding.health.setTextColor(Color.parseColor(colorCode2))
            binding.entertainment.setTextColor(Color.parseColor(colorCode2))
            binding.science.setTextColor(Color.parseColor(colorCode2))
            binding.technology.setTextColor(Color.parseColor(colorCode2))

            binding.lineBusiness.visibility = View.VISIBLE
            binding.lineSport.visibility = View.GONE
            binding.lineEntertainment.visibility = View.GONE
            binding.lineHealth.visibility = View.GONE
            binding.lineTechnology.visibility = View.GONE
            binding.lineScience.visibility = View.GONE
        }
        binding.health.setOnClickListener {
            loadNews("health", savedLanguageCode)

            binding.health.setTextColor(Color.parseColor(colorCode))
            binding.business.setTextColor(Color.parseColor(colorCode2))
            binding.sports.setTextColor(Color.parseColor(colorCode2))
            binding.entertainment.setTextColor(Color.parseColor(colorCode2))
            binding.science.setTextColor(Color.parseColor(colorCode2))
            binding.technology.setTextColor(Color.parseColor(colorCode2))

            binding.lineHealth.visibility = View.VISIBLE
            binding.lineBusiness.visibility = View.GONE
            binding.lineEntertainment.visibility = View.GONE
            binding.lineSport.visibility = View.GONE
            binding.lineTechnology.visibility = View.GONE
            binding.lineScience.visibility = View.GONE
        }
        binding.science.setOnClickListener {
            loadNews("world", savedLanguageCode)

            binding.science.setTextColor(Color.parseColor(colorCode))
            binding.business.setTextColor(Color.parseColor(colorCode2))
            binding.health.setTextColor(Color.parseColor(colorCode2))
            binding.entertainment.setTextColor(Color.parseColor(colorCode2))
            binding.sports.setTextColor(Color.parseColor(colorCode2))
            binding.technology.setTextColor(Color.parseColor(colorCode2))

            binding.lineScience.visibility = View.VISIBLE
            binding.lineBusiness.visibility = View.GONE
            binding.lineEntertainment.visibility = View.GONE
            binding.lineHealth.visibility = View.GONE
            binding.lineTechnology.visibility = View.GONE
            binding.lineSport.visibility = View.GONE
        }
        binding.entertainment.setOnClickListener {
            loadNews("entertainment", savedLanguageCode)

            binding.entertainment.setTextColor(Color.parseColor(colorCode))
            binding.business.setTextColor(Color.parseColor(colorCode2))
            binding.health.setTextColor(Color.parseColor(colorCode2))
            binding.sports.setTextColor(Color.parseColor(colorCode2))
            binding.science.setTextColor(Color.parseColor(colorCode2))
            binding.technology.setTextColor(Color.parseColor(colorCode2))

            binding.lineEntertainment.visibility = View.VISIBLE
            binding.lineBusiness.visibility = View.GONE
            binding.lineSport.visibility = View.GONE
            binding.lineHealth.visibility = View.GONE
            binding.lineTechnology.visibility = View.GONE
            binding.lineScience.visibility = View.GONE
        }

        loadNews(savedLanguageCode)
        loadNews("sports", savedLanguageCode)

        binding.imgSelectLanguage.setOnClickListener {
            showLanguageDialog()
        }


    }

    private fun showLanguageDialog() {
        val languages = arrayOf("English", "Gujarati", "Hindi", "Marathi")
        val languageCodes = arrayOf("en", "gu", "hi", "mr")
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Select Language")
        alertDialogBuilder.setItems(languages) { _, which ->
            val selectedLanguageCode = languageCodes[which]
            savedLanguageCode = languageCodes[which]
            saveLanguagePreference(selectedLanguageCode)
            loadNews(selectedLanguageCode)
            loadNews("sports", selectedLanguageCode)
            binding.sports.setTextColor(Color.parseColor(colorCode))
            binding.business.setTextColor(Color.parseColor(colorCode2))
            binding.health.setTextColor(Color.parseColor(colorCode2))
            binding.entertainment.setTextColor(Color.parseColor(colorCode2))
            binding.science.setTextColor(Color.parseColor(colorCode2))
            binding.technology.setTextColor(Color.parseColor(colorCode2))
            binding.lineSport.visibility = View.VISIBLE
            binding.lineEntertainment.visibility = View.GONE
            binding.lineBusiness.visibility = View.GONE
            binding.lineHealth.visibility = View.GONE
            binding.lineTechnology.visibility = View.GONE
            binding.lineScience.visibility = View.GONE


        }
        alertDialogBuilder.setOnCancelListener(DialogInterface.OnCancelListener {

        })
        alertDialogBuilder.show()
    }


    private fun saveLanguagePreference(languageCode: String) {
        sharedPreferences.edit().putString("language_code", languageCode).apply()
    }

    private fun loadNews(language: String) {

        var apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        apiInterface.getNews("pub_36634462dae526ef1c95a8df4fdc4c85e8617", language, "in", "top")
            .enqueue(object :
                Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {

                    if (response.isSuccessful) {
                        var data = response.body()
                        var data2 = data?.results
                        Log.e(ContentValues.TAG, "onResponse: ===========${data2}")
                        Log.e(ContentValues.TAG, "onResponse: ===="+ data?.totalResults )

                        binding.bNewsRecycler.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        binding.bNewsRecycler.adapter = BreakingNewsAdapter(data2)


                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: " + t.message)
                }

            })
    }

    private fun loadNews(category: String, language: String) {

        binding.progressBar.visibility = View.VISIBLE
        binding.recycler.visibility = View.GONE
        var apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        apiInterface.getNews("pub_36634462dae526ef1c95a8df4fdc4c85e8617", language, "in", category)
            .enqueue(object :
                Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {


                    if (response.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        binding.recycler.visibility = View.VISIBLE

                        var data = response.body()
                        var data2 = data?.results
                        Log.e(ContentValues.TAG, "onResponse: ===========${data2}")

                        binding.recycler.layoutManager = LinearLayoutManager(context)
                        binding.recycler.adapter = NewsAdapter(data2)

                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    Log.d(ContentValues.TAG, "onFailure: No data found " + t.message)
                }

            })
    }
}
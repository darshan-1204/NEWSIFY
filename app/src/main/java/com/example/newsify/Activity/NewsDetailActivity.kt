package com.example.newsify.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.newsify.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private val TAG = "NewsDetailActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var content = intent.getStringExtra("content")
        var description = intent.getStringExtra("description")
        var img = intent.getStringExtra("img")
        var title = intent.getStringExtra("title")
        var url = intent.getStringExtra("link")

        binding.webView.loadUrl(url.toString())
        binding.webView.settings.javaScriptEnabled = true


        Log.e(TAG, "onCreate: ===$content")

        binding.backNewsActivity.setOnClickListener {
            finish()
        }

        binding.fabShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url.toString())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }


//        var data = MainActivity.data
//
//        if (description!!.contains("<p")) {
//            Log.e(TAG, "onCreate: ===========")
//            binding.descNd.visibility = View.GONE
//        } else {
//            binding.descNd.text = description.toString()
//        }
//        if (content == "null") {
//
//        } else {
//
//            binding.contentNd.text = content.toString()
//        }
//
//        binding.title.text = title.toString()
//
//
//        binding.btnUrl.setOnClickListener {
//
//            var intent = Intent(Intent.ACTION_VIEW)
//            intent.setData(Uri.parse(url))
//            startActivity(intent)
//        }

//        Glide.with(this).load(img).into(binding.imgNd)

    }

}
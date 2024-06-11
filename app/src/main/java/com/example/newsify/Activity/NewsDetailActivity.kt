package com.example.newsify.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.newsify.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private val TAG = "NewsDetailActivity"
    lateinit var binding: ActivityNewsDetailBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var content = intent.getStringExtra("content")
        var description = intent.getStringExtra("description")
        var img = intent.getStringExtra("img")
        var title = intent.getStringExtra("title")
        var url = intent.getStringExtra("link")

        var client = CustomWebViewClient(this)
        binding.webView.webViewClient = client
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url.toString())


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


    }

    @Override
    public override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()){
            binding.webView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

}
class CustomWebViewClient : WebViewClient {

    private lateinit var activity: Activity

    public constructor(activity : Activity){
        this.activity = activity
    }

    @Override
    public override fun shouldOverrideUrlLoading(webView: WebView, url : String): Boolean {
        return false
    }

    @Override
    public override fun shouldOverrideUrlLoading(webView: WebView,request : WebResourceRequest): Boolean {
        return false
    }
}
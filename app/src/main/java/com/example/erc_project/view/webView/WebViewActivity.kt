package com.example.erc_project.view.webView


import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.erc_project.R


class WebViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)

        val url = intent.extras?.get(URL_KEY) as? String ?: ""

        val webView = findViewById<WebView>(R.id.web_view)
        webView.loadUrl(url)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

    companion object {
        const val URL_KEY = "WebViewActivity_URL_KEY"
    }
}
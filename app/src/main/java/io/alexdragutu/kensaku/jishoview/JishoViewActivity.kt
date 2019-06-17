package io.alexdragutu.kensaku.jishoview

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.beust.klaxon.Klaxon
import io.alexdragutu.kensaku.R
import io.alexdragutu.kensaku.config.JishoConfigModel
import io.alexdragutu.kensaku.utils.Utils
import kotlinx.android.synthetic.main.activity_jisho_view.*

class JishoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jisho_view)
        val query: String = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT) as String

        var settings = Utils.getSettings(this)
        if (settings == null) {
            settings = Utils.getDefaultJishoConfig()
        }

        val jishoConfig = Klaxon().parseArray<JishoConfigModel>(settings)

        val url: String = Utils.getSearchString(jishoConfig!![0].urlTemplate, query)

        jishoWebView.webViewClient = WebViewClient()
        jishoWebView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (jishoWebView.canGoBack()) {
            jishoWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

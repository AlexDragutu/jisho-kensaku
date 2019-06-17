package io.alexdragutu.kensaku.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.beust.klaxon.Klaxon
import io.alexdragutu.kensaku.config.JishoConfigModel
import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLEncoder

object Utils {

    const val GOO_SEARCH_TEMPLATE = "https://dictionary.goo.ne.jp/srch/all/%s/m0u/"
    const val GOO_SEARCH_NAME = "goo辞書"
    const val JISHO_SEARCH_TEMPLATE = "https://jisho.org/search/%s"
    const val JISHO_SEARCH_NAME = "jisho"

    const val PREFS = "JishoConfigSettings"

    fun openBrowser(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    fun getSearchString(template: String, query: String): String {
        return try {
            template.replace("%s", URLEncoder.encode(query, "UTF-8"))
        } catch (ignored: UnsupportedEncodingException) {
            URL(template).host
        }
    }

    fun saveSettings(context: Context, value: String) {
        val prefsEditor = context.getSharedPreferences(PREFS, 0).edit()
        prefsEditor.putString(PREFS, value)
        prefsEditor.apply()
    }

    fun getSettings(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS, 0)
        return prefs.getString(PREFS, null)
    }

    fun getDefaultJishoConfig(): String {
        return Klaxon().toJsonString(
            arrayOf(
                JishoConfigModel(GOO_SEARCH_TEMPLATE, GOO_SEARCH_NAME),
                JishoConfigModel(JISHO_SEARCH_TEMPLATE, JISHO_SEARCH_NAME)
            )
        )
    }
}

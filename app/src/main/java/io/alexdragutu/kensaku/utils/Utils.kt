package io.alexdragutu.kensaku.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLEncoder

object Utils {

    val GOO_SEARCH_TEMPLATE = "https://dictionary.goo.ne.jp/srch/all/%s/m0u/"
    val JISHO_SEARCH_TEMPLATE = "https://jisho.org/search/%s"

    val PREFS_FILE = "JishoConfigSettings"

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

    fun saveValue(context: Context, key: String, value: String) {
        val prefsEditor = context.getSharedPreferences(PREFS_FILE, 0).edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun getValue(context: Context, key: String) : String? {
        val prefs = context.getSharedPreferences(PREFS_FILE, 0)
        return prefs.getString(key, null)
    }
}

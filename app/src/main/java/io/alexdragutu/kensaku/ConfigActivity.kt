package io.alexdragutu.kensaku

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.alexdragutu.kensaku.utils.Utils
import org.json.JSONArray

class ConfigActivity : AppCompatActivity() {

    var jishoCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        var settings = Utils.getSettings(this)
        if (settings == null) {
            settings = Utils.getDefaultJishoConfig()
        }

        settings = JSONArray(settings).toString(1)

        val text: TextView = findViewById(R.id.welcome_message)
        text.text = getString(R.string.welcome_message) + settings
    }
}

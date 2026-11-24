package com.iptvglass.tv

import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val categories = listOf(
        "Hindi", "Punjabi", "Tamil", "Telugu", "Malayalam", "Kannada",
        "Bengali", "Marathi", "Gujarati", "Odia", "Assamese", "English",
        "Kids", "News", "Sports", "Movies", "Entertainment", "Music"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        buildHome()
    }

    private fun buildHome() {
        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(30), dp(18), dp(30), dp(18))
            setBackgroundColor(Color.rgb(7, 11, 20))
            isFocusable = true
        }

        val top = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        top.addView(label("IPTV  GLASS", 25f, Color.WHITE, true), LinearLayout.LayoutParams(0, dp(48), 1f))
        top.addView(label("HOME     LIVE TV     CATEGORIES     SETTINGS", 14f, Color.LTGRAY, false))
        page.addView(top)

        val clock = label("YOUR ENTERTAINMENT, IN ONE PLACE", 13f, Color.rgb(135, 177, 225), true)
        val clockParams = LinearLayout.LayoutParams(-1, dp(28))
        clockParams.topMargin = dp(8)
        page.addView(clock, clockParams)

        val banners = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }
        banners.addView(banner("LIVE TV", "Add your legal M3U / M3U8 playlist"), LinearLayout.LayoutParams(0, dp(112), 1f).apply { rightMargin = dp(10) })
        banners.addView(banner("YOUR LIBRARY", "Continue watching from where you stopped"), LinearLayout.LayoutParams(0, dp(112), 1f).apply { leftMargin = dp(10) })
        val bp = LinearLayout.LayoutParams(-1, dp(112))
        bp.topMargin = dp(12)
        page.addView(banners, bp)

        page.addView(label("EXPLORE CATEGORIES", 18f, Color.WHITE, true).apply {
            setPadding(0, dp(18), 0, dp(8))
        })

        val list = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            adapter = TileAdapter(categories) { selected ->
                android.widget.Toast.makeText(this@MainActivity, "$selected playlist feature coming next", android.widget.Toast.LENGTH_SHORT).show()
            }
            overScrollMode = View.OVER_SCROLL_NEVER
        }
        page.addView(list, LinearLayout.LayoutParams(-1, dp(94)))

        page.addView(label("CONTINUE WATCHING", 18f, Color.WHITE, true).apply {
            setPadding(0, dp(16), 0, dp(6))
        })
        page.addView(label("Nothing here yet — channels you watch will appear here.", 14f, Color.LTGRAY, false))

        setContentView(page)
        list.post { if (list.childCount > 0) list.getChildAt(0).requestFocus() }
    }

    private fun banner(title: String, subtitle: String): View {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(18), dp(10), dp(18), dp(10))
            background = getDrawable(R.drawable.banner_bg)
            addView(label(title, 22f, Color.WHITE, true))
            addView(label(subtitle, 13f, Color.LTGRAY, false))
        }
    }

    private fun label(text: String, size: Float, color: Int, bold: Boolean): TextView =
        TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            if (bold) setTypeface(typeface, android.graphics.Typeface.BOLD)
            gravity = Gravity.CENTER_VERTICAL
        }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}

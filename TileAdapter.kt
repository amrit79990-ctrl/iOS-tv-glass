package com.iptvglass.tv

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TileAdapter(
    private val items: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<TileAdapter.TileHolder>() {

    class TileHolder(val tile: TextView) : RecyclerView.ViewHolder(tile)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileHolder {
        val tile = TextView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                (116 * resources.displayMetrics.density).toInt(),
                (76 * resources.displayMetrics.density).toInt()
            )
            gravity = Gravity.CENTER
            textSize = 16f
            setTextColor(Color.WHITE)
            setTypeface(typeface, android.graphics.Typeface.BOLD)
            isFocusable = true
            isFocusableInTouchMode = true
            background = parent.context.getDrawable(R.drawable.glass_tile)
            setPadding(8, 8, 8, 8)
        }
        return TileHolder(tile)
    }

    override fun onBindViewHolder(holder: TileHolder, position: Int) {
        val name = items[position]
        holder.tile.text = name
        holder.tile.setOnClickListener { onClick(name) }
        holder.tile.contentDescription = "$name category"
    }

    override fun getItemCount(): Int = items.size
}

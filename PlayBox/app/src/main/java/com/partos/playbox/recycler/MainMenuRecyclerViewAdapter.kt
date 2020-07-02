package com.partos.playbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.partos.playbox.R
import kotlinx.android.synthetic.main.row_main_menu.view.*

class MainMenuRecyclerViewAdapter() : RecyclerView.Adapter<MainMenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.row_main_menu, parent, false)
        return MainMenuViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        val cardView = holder.view.row_menu_card_view
        val image = holder.view.menu_image_view
        val text = holder.view.menu_text_view

        when (position) {
            0 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorYellowLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorYellowDark
                    )
                )
                image.setImageResource(R.drawable.charade)
                text.setText(R.string.pictionary)
            }

            1 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorRedLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorRedDark
                    )
                )
                image.setImageResource(R.drawable.beer_pong)
                text.setText(R.string.beer_pong)
            }

            2 -> {
                cardView.setCardBackgroundColor(ContextCompat.getColor(
                    holder.view.context,
                    R.color.colorGreenLight)
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorGreenDark
                    )
                )
                image.setImageResource(R.drawable.bottles)
                text.setText(R.string.bottles)
            }
        }
    }

}


class MainMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view)
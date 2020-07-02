package com.partos.playbox.recycler

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.partos.playbox.activities.MainActivity
import com.partos.playbox.R
import com.partos.playbox.fragments.beerpong.BeerPongMenuFragment
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
                animLeft(holder)
                setColor(0, holder)
                image.setImageResource(R.drawable.charade)
                text.setText(R.string.pictionary)
            }
            1 -> {
                animRight(holder)
                setColor(1, holder)
                image.setImageResource(R.drawable.beer_pong)
                text.setText(R.string.beer_pong)
            }
            2 -> {
                animLeft(holder)
                setColor(2, holder)
                image.setImageResource(R.drawable.bottles)
                text.setText(R.string.bottles)
            }
        }

        cardView.setOnClickListener {
            lateinit var fragment: Fragment
            when (position) {
                0 -> {

                }

                1 -> {
                    fragment = BeerPongMenuFragment.newInstance()
                }

                2 -> {

                }
            }
            val manager = (holder.itemView.context as MainActivity).supportFragmentManager
            manager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack("game chosen")
                .commit()
        }
    }

    private fun setColor(color: Int, holder: MainMenuViewHolder) {
        val cardView = holder.view.row_menu_card_view
        when (color) {
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
            }
            1 -> {
                val animation =
                    AnimationUtils.loadAnimation(holder.view.context, R.anim.enter_right_to_left)
                Handler().postDelayed({
                    cardView.visibility = View.VISIBLE
                    cardView.startAnimation(animation)
                }, 900)
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
            }
            2 -> {
                val animation =
                    AnimationUtils.loadAnimation(holder.view.context, R.anim.enter_left_to_right)
                Handler().postDelayed({
                    cardView.visibility = View.VISIBLE
                    cardView.startAnimation(animation)
                }, 900)
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorGreenLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorGreenDark
                    )
                )
            }
        }
    }

    private fun animLeft(holder: MainMenuViewHolder) {
        val cardView = holder.view.row_menu_card_view
        val animation =
            AnimationUtils.loadAnimation(holder.view.context, R.anim.enter_left_to_right)
        Handler().postDelayed({
            cardView.visibility = View.VISIBLE
            cardView.startAnimation(animation)
        }, 900)
    }

    private fun animRight(holder: MainMenuViewHolder) {
        val cardView = holder.view.row_menu_card_view
        val animation =
            AnimationUtils.loadAnimation(holder.view.context, R.anim.enter_right_to_left)
        Handler().postDelayed({
            cardView.visibility = View.VISIBLE
            cardView.startAnimation(animation)
        }, 900)
    }
}


class MainMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view)
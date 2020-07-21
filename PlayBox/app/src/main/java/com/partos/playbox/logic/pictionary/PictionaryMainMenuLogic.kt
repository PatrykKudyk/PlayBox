package com.partos.playbox.logic.pictionary

import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.playbox.R
import com.partos.playbox.fragments.pictionary.PictionaryRulesFragment

class PictionaryMainMenuLogic(val rootView: View, val fragmentManager: FragmentManager) {

    private lateinit var classicButton: Button
    private lateinit var teamsButton: Button
    private lateinit var rulesButton: Button

    fun initFragment() {
        attachViews()
        makeStartAnimations()
        attachListeners()
    }

    private fun makeStartAnimations() {
        val animLeft = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_left_to_right)
        val animRight = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_right_to_left)

        Handler().postDelayed({
            classicButton.visibility = View.VISIBLE
            teamsButton.visibility = View.VISIBLE
            rulesButton.visibility = View.VISIBLE
            classicButton.startAnimation(animLeft)
            teamsButton.startAnimation(animRight)
            rulesButton.startAnimation(animLeft)
        }, 500)
    }

    private fun attachListeners() {
        classicButton.setOnClickListener {

        }
        teamsButton.setOnClickListener {

        }
        rulesButton.setOnClickListener {
            val fragment = PictionaryRulesFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(PictionaryRulesFragment.toString())
                .commit()
        }
    }

    private fun attachViews() {
        classicButton = rootView.findViewById(R.id.pictionary_button_classic)
        teamsButton = rootView.findViewById(R.id.pictionary_button_teams)
        rulesButton = rootView.findViewById(R.id.pictionary_button_rules)
    }

}
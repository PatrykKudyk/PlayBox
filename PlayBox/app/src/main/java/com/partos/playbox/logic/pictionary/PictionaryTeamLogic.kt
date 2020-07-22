package com.partos.playbox.logic.pictionary

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.playbox.R
import com.partos.playbox.activities.PictionaryActivity

class PictionaryTeamLogic(val rootView: View, val fragmentManager: FragmentManager) {

    private lateinit var backButton: Button
    private lateinit var mainCard: CardView
    private lateinit var showSloganTextView: TextView
    private lateinit var team1SloganTextView: TextView
    private lateinit var team2SloganTextView: TextView
    private lateinit var slogansLayout: LinearLayout
    private lateinit var scoreTextView: TextView

    private var scoreTeam1 = 0
    private var scoreTeam2 = 0

    fun initFragment() {
        attachViews()
        attachListeners()
    }

    private fun attachListeners() {
        backButton.setOnClickListener {
            (rootView.context as PictionaryActivity).finish()
        }
        mainCard.setOnClickListener {
            showSloganTextView.visibility = View.GONE
            slogansLayout.visibility = View.VISIBLE
            team1SloganTextView.setText("Tekst drużyny 1")
            team2SloganTextView.setText("Tekst drużyny 2")
        }
        team1SloganTextView.setOnClickListener {
            scoreTeam1++
            setScore()
            showSloganTextView.visibility = View.VISIBLE
            slogansLayout.visibility = View.GONE
        }
        team2SloganTextView.setOnClickListener {
            scoreTeam2++
            setScore()
            showSloganTextView.visibility = View.VISIBLE
            slogansLayout.visibility = View.GONE
        }
    }

    private fun setScore() {
        scoreTextView.text = "$scoreTeam1 : $scoreTeam2"
    }

    private fun attachViews() {
        backButton = rootView.findViewById(R.id.pictionary_game_teams_button_back)
        mainCard = rootView.findViewById(R.id.pictionary_teams_card)
        showSloganTextView = rootView.findViewById(R.id.pictionary_teams_text_show)
        team1SloganTextView = rootView.findViewById(R.id.pictionary_teams_text_team1)
        team2SloganTextView = rootView.findViewById(R.id.pictionary_teams_text_team2)
        slogansLayout = rootView.findViewById(R.id.pictionary_teams_linear_layout_slogans)
        scoreTextView = rootView.findViewById(R.id.pictionary_teams_text_score)
    }
}
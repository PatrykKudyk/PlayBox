package com.partos.playbox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.partos.playbox.R
import com.partos.playbox.fragments.MainMenuFragment
import com.partos.playbox.fragments.pictionary.PictionaryClassicFragment
import com.partos.playbox.fragments.pictionary.PictionaryTeamFragment

class PictionaryActivity : AppCompatActivity(),
    PictionaryClassicFragment.OnFragmentInteractionListener,
    PictionaryTeamFragment.OnFragmentInteractionListener {

    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictionary)

        val type = intent.getSerializableExtra("game")
        if (type == 1) {
            fragment = PictionaryClassicFragment.newInstance()
        } else if (type == 2) {
            fragment = PictionaryTeamFragment.newInstance()
        }

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_bottom_to_top,
                R.anim.exit_top_to_bottom,
                R.anim.enter_top_to_bottom,
                R.anim.exit_bottom_to_top
            )
            .add(R.id.pictionary_frame_layout, fragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onBackPressed() {

    }
}
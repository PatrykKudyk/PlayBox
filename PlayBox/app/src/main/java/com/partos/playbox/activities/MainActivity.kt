package com.partos.playbox.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.partos.playbox.R
import com.partos.playbox.fragments.MainMenuFragment
import com.partos.playbox.fragments.pictionary.PictionaryClassicFragment
import com.partos.playbox.fragments.pictionary.PictionaryMainMenuFragment
import com.partos.playbox.fragments.pictionary.PictionaryRulesFragment

class MainActivity : AppCompatActivity(),
    MainMenuFragment.OnFragmentInteractionListener,
    PictionaryMainMenuFragment.OnFragmentInteractionListener,
    PictionaryRulesFragment.OnFragmentInteractionListener {

    private lateinit var mainMenuFragment: MainMenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMenuFragment = MainMenuFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_bottom_to_top,
                R.anim.exit_top_to_bottom,
                R.anim.enter_top_to_bottom,
                R.anim.exit_bottom_to_top
            )
            .add(R.id.main_frame_layout, mainMenuFragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
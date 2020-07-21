package com.partos.playbox.logic.pictionary

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.playbox.R
import com.partos.playbox.threads.TimerThread

class PictionaryClassicLogic(val rootView: View, val fragmentManager: FragmentManager) {

    private lateinit var timerTextView: TextView
    private lateinit var cardView: CardView
    private lateinit var textView: TextView
    private lateinit var backButton: Button
    private lateinit var context: Context
    private lateinit var soundPool: SoundPool
    private var soundAlarm = 0
    private var soundClock = 0
    private var streamClock = -1
    private var streamAlarm = -1
    private var looperThread = TimerThread()

    fun initFragment() {
        context = rootView.context
        looperThread.start()
        attachViews()
        createSoundPools()
        attachListeners()
    }

    private fun createSoundPools() {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        soundAlarm = soundPool.load(rootView.context, R.raw.alarm, 1)
        soundClock = soundPool.load(rootView.context, R.raw.clock, 1)
    }

    private fun attachListeners() {
        cardView.setOnClickListener {
            if (streamAlarm != -1) {
                soundPool.stop(streamAlarm)
            }
            if (soundClock != -1) {
                soundPool.stop(streamClock)
            }
            if (textView.text.toString() == context.getString(R.string.show_word)) {
//                looperThread.start()
                startTimer()
                textView.setText("hasło")
            } else {
                timerTextView.visibility = View.INVISIBLE
                textView.text = context.getText(R.string.show_word)
            }
        }

        backButton.setOnClickListener {
            if (streamAlarm != -1) {
                soundPool.stop(streamAlarm)
            }
            if (soundClock != -1) {
                soundPool.stop(streamClock)
            }
            timerTextView.visibility = View.INVISIBLE
            fragmentManager.popBackStack()
        }
    }

    private fun startTimer() {
        timerTextView.visibility = View.VISIBLE
        var threadHandler = Handler(looperThread.looper)
        var timeLeft = 60
        threadHandler.post(object : Runnable {
            override fun run() {
                if (timerTextView.visibility == View.VISIBLE) {
                    setTime(timeLeft)
                    if (timeLeft % 60 == 0) {
                        if (soundClock != -1) {
                            soundPool.stop(streamClock)
                        }
                        streamClock = soundPool.play(soundClock, 1F, 1F, 0, 0, 1F)
                    }
                    timeLeft--
                    if (timeLeft < 0) {
                        streamAlarm = soundPool.play(soundAlarm, 1F, 1F, 0, 0, 1F)
                    }
                } else {
                    timeLeft = -1
//                    threadHandler.looper.quitSafely()
                }
                if (timeLeft >= 0) {
                    threadHandler.postDelayed(this, 100)
                } else {
//                    threadHandler.looper.quitSafely()
                }
            }

        })
    }

    private fun setTime(timeLeft: Int) {
        val time = timeLeft/10
        if (time == 120) {
            timerTextView.setText("2:00")
        } else if (time >= 60) {
            if (time - 60 in 0..9) {
                timerTextView.setText("1:0" + (time - 60).toString())
            } else {
                timerTextView.setText("1:" + (time - 60).toString())
            }
        } else {
            if (time in 0..9) {
                timerTextView.setText("0:0" + time.toString())
            } else {
                timerTextView.setText("0:" + time.toString())
            }
        }
    }

    private fun attachViews() {
        timerTextView = rootView.findViewById(R.id.pictionary_classic_time)
        cardView = rootView.findViewById(R.id.pictionary_classic_card)
        textView = rootView.findViewById(R.id.pictionary_classic_text)
        backButton = rootView.findViewById(R.id.pictionary_game_classic_button_back)
    }
}
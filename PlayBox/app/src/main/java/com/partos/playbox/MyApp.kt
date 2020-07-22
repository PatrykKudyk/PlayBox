package com.partos.playbox

import android.app.Application

class MyApp: Application() {
    companion object {
        val url = "https://boxserver.herokuapp.com/"
    }
}
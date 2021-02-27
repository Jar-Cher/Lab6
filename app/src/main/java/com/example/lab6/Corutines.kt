package com.example.lab6

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class Corutines : AppCompatActivity() {
    var secondsElapsed: Int = 0
    val SECONDS = "0"
    lateinit var countingJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Log.w("C","Corutines running")
        textSecondsElapsed.text = "Second elapsed: " + secondsElapsed
        countingJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                textSecondsElapsed.text = "Second elapsed: " + secondsElapsed++
            }
        }
    }

    override fun onPause() {
        countingJob.cancel()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SECONDS, secondsElapsed)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        secondsElapsed = savedInstanceState.getInt(SECONDS)
    }
}
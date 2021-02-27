package com.example.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_main.*
import android.os.AsyncTask
import android.util.Log

class AsyncTask : AppCompatActivity() {
    var secondsElapsed: Int = 0
    val SECONDS = "0"
    lateinit var countingTask: Counter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Log.w("A","AsyncTask running")
        countingTask = Counter()
        countingTask.execute()
    }

    override fun onPause() {
        countingTask.cancel(true)
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

    inner class Counter : AsyncTask<Void?, Void?, Void?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            textSecondsElapsed.text = "Second elapsed: $secondsElapsed"
        }

        override fun doInBackground(vararg voids: Void?): Void? {
            while(!isCancelled) {
                TimeUnit.SECONDS.sleep(1)
                publishProgress()
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
            textSecondsElapsed.text = "Second elapsed: " + secondsElapsed++
        }
    }
}
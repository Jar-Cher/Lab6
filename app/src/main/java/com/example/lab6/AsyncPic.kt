package com.example.lab6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.AsyncTask
import android.util.Log
import kotlinx.android.synthetic.main.activity_async_pic.*
import java.io.InputStream
import java.net.URL

class AsyncPic : AppCompatActivity() {
    lateinit var downloadingTask: Downloader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_pic)
        Log.w("AP","Async Downloading")
        button.setOnClickListener {
            downloadingTask = Downloader()
            downloadingTask.execute("https://upload.wikimedia.org/wikipedia/commons/0/0d/Reflexion_der_Augen_einer_Katze.JPG")
        }
    }

    inner class Downloader : AsyncTask<String?, Void?, Bitmap?>() {
        override fun doInBackground(vararg urls: String?): Bitmap? {
            var pic: Bitmap? = null
            try {
                val input: InputStream = URL(urls[0]).openStream()
                pic = BitmapFactory.decodeStream(input)
            } catch(e: Exception) {
                e.printStackTrace()
                Log.e("NP", "Downloading problem")
            }
            return pic
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }
    }
}
package com.example.lab6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_async_pic.*
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL

class KotlinPic : AppCompatActivity() {
    lateinit var downloadingJob: Job
    private val url = "https://upload.wikimedia.org/wikipedia/commons/0/0d/Reflexion_der_Augen_einer_Katze.JPG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_pic)
        Log.w("CP","Corutines Downloading")
        button.setOnClickListener {
            downloadingJob = CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
                try {
                    val input: InputStream = URL(url).openStream()
                    val image = BitmapFactory.decodeStream(input)
                    launch(Dispatchers.Main) {
                        imageView.setImageBitmap(image)
                    }
                } catch(e: Exception) {
                    e.printStackTrace()
                    Log.e("NP", "Downloading problem")
                }
            }
        }
    }
}
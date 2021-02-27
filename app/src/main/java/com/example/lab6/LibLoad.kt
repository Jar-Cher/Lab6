package com.example.lab6

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.activity_lib_load.*


class LibLoad : AppCompatActivity() {
    private val url = "https://upload.wikimedia.org/wikipedia/commons/0/0d/Reflexion_der_Augen_einer_Katze.JPG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_lib_load)
        Fresco.getImagePipeline().clearCaches()
        button1.setOnClickListener {
            val uri: Uri = Uri.parse(url)
            val draweeView = findViewById<SimpleDraweeView>(R.id.my_image_view)
            draweeView.setImageURI(uri)
        }
    }
}
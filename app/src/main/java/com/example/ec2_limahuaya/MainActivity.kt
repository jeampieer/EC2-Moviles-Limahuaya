package com.example.ec2_limahuaya

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ec2_limahuaya.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonMap = findViewById<View>(R.id.buttonMap)
        val buttonCamera = findViewById<View>(R.id.buttonCamera)

        buttonMap.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
            finish()
        }

        buttonCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
            finish()
        }
    }
}

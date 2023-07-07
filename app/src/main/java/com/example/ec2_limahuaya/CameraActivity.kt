package com.example.ec2_limahuaya

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ec2_limahuaya.databinding.ActivityCameraBinding


class CameraActivity : AppCompatActivity() {
    private lateinit var b: ActivityCameraBinding

    private lateinit var openCamaraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnTakePhoto.setOnClickListener {
            if (permissionValidated()){
                takePhoto()
            }
        }

        openCamaraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if (result.resultCode == RESULT_OK){
                val photoBitmap = result.data?.extras?.get("data") as Bitmap
                b.imgage.setImageBitmap(photoBitmap)
            }
        }
    }

    private fun permissionValidated(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val permissionList: MutableList<String> = mutableListOf()

        if (cameraPermission != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA)
        }
        if(permissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toTypedArray(),1000)
            return false
        }

        return true
    }

    private fun takePhoto() {
        val cameraIntent =Intent()
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        openCamaraLauncher.launch(cameraIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            1000 ->{
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA ) == PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                }
            }
        }
    }

}

package com.example.camerasample

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    /**
     * 保存された画像のURI
     */
    private var imageUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 200 && resultCode == RESULT_OK){
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
            ivCamera.setImageURI(imageUri)
//            val bitmap = data?.getParcelableExtra<Bitmap>("data")
//            ivCamera.setImageBitmap(bitmap)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun onCameraImageClick(view: View){
        // WRITE_EXTERNAL_STORAGEの許可が下りていない場合
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 2000)
            return
        }

        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val now = Date(System.currentTimeMillis())
        val nowStr = dateFormat.format(now)
        val fileName = "CameraSamplePhoto_$nowStr.jpg"

        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, 200)
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(intent, 200)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 2000 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
            onCameraImageClick(ivCamera)
        }
    }
}

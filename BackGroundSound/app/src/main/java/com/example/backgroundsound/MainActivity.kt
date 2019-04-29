package com.example.backgroundsound

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // API Level 28以上でないと使えないので未実装
        // 通知チャンネル
//        val id = "soundmanagerservice_notification_channel"
//        val name = getString(R.string.notification_channel_name)
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = NotificationChannel(id, name, importance)
//        val manager = getSystemService(context.NOTIFICATION_SERIVCE)
    }

    fun onPlayButtonClick(view: View){
        val intent = Intent(this, SoundManageService::class.java)
        startService(intent)
        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)
        btPlay.isEnabled = false
        btStop.isEnabled = true
    }

    fun onStopButtonClick(view:View){
        val intent = Intent(this, SoundManageService::class.java)
        stopService(intent)
        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)
        btPlay.isEnabled = true
        btStop.isEnabled = false
    }
}

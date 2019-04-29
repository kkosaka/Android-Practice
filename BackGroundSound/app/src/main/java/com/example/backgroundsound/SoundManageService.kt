package com.example.backgroundsound

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import java.io.IOException

class SoundManageService : Service() {

    /**
     * メディアプレーヤーフィールド
     */
    private var player: MediaPlayer? = null

    override fun onCreate() {
        player = MediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 音声ファイルのURI
        val mediaFileUriStr = "android.resource://$packageName/${R.raw.mountain_stream}"
        // URIStrをもとに、URIオブジェクトを生成
        val mediaFileUri = Uri.parse(mediaFileUriStr)
        try{
            player?.setDataSource(this, mediaFileUri)
            // 非同期でのメディア再生準備が完了した際のリスナを設定
            player?.setOnPreparedListener {
                it.start()
            }
            // メディア再生が終了した際のリスナの設定
            player?.setOnCompletionListener {
                stopSelf()
            }
            player?.prepareAsync()
        }catch (e : IOException){
            e.printStackTrace()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        if(player!!.isPlaying){
            player?.stop()
        }
        player?.release()
        player = null
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}

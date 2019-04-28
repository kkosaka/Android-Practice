package android.wings.websarva.com.mediasample

import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Switch
import java.io.IOException
import android.os.*


class MediaControlActivity : AppCompatActivity() {
    // メディアプレイヤーフィールド
    private lateinit var _player: MediaPlayer
    // 再生・一時停止ボタンフィールド
    private lateinit var _btPlay: Button
    // 戻るボタンフィールド
    private lateinit var _btBack: Button
    // 進むボタンフィールド
    private lateinit var _btForward: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_control)
        _btPlay = findViewById(R.id.btPlay)
        _btBack = findViewById(R.id.btBack)
        _btForward = findViewById(R.id.btForward)

        _player = MediaPlayer()
        // 音声ファイルのURI
        val mediaFileUriStr = "android.resource://$packageName/${R.raw.mountain_stream}"
        // URIStrをもとに、URIオブジェクトを生成
        val mediaFileUri = Uri.parse(mediaFileUriStr)
        try{
            _player.setDataSource(this, mediaFileUri)
            // 非同期でのメディア再生準備が完了した際のリスナを設定
            _player.setOnPreparedListener {
                _btPlay.isEnabled = true
                _btBack.isEnabled = true
                _btForward.isEnabled = true
            }
            // メディア再生が終了した際のリスナの設定
            _player.setOnCompletionListener {
                _btPlay.setText(R.string.bt_play_play)
            }
            _player.prepareAsync()
        }catch (e :IOException){
            e.printStackTrace()
        }

        // スイッチを取得
        val loopSwitch: Switch = findViewById(R.id.swLoop)
        loopSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            _player.isLooping = isChecked
        }
    }

    fun onPlayButtonClick(view: View){
        if(_player.isPlaying){
            _player.pause()
            _btPlay.setText(R.string.bt_play_play)
        }else{
            _player.start()
            _btPlay.setText(R.string.bt_play_pause)
        }
    }

    fun onBackButtonClick(view: View){
        _player.seekTo(0)
    }

    fun onForwardButtonClick(view: View){
        _player.seekTo(_player.duration)
        if(!_player.isPlaying){
            _player.start()
        }
    }
}

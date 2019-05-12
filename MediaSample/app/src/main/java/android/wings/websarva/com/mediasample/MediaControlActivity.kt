package android.wings.websarva.com.mediasample

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Switch
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

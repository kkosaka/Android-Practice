package android.wings.websarva.com.listviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        lvMenu.setOnItemClickListener { parent, view, position, id ->
            // タップされた定食名を取得
            val item = parent.getItemAtPosition(position) as String
            // トーストで表示する文字列を生成
            val show = "あなたが選んだ定食：${item}"
            // トーストの表示
            Toast.makeText(this, show, Toast.LENGTH_LONG).show();
        }
    }
}

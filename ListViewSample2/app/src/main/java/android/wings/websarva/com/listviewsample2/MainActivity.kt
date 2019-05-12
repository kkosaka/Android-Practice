package android.wings.websarva.com.listviewsample2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lvMenu = findViewById<ListView>(R.id.lvMenu)
        var menuList = ArrayList<String>().apply {
            this.add("唐揚げ定食")
            this.add("ハンバーグ定食")
            this.add("生姜焼き定食")
            this.add("ステーキ定食")
            this.add("野菜炒め定食")
            this.add("とんかつ定食")
            this.add("メンチカツ定食")
            this.add("チキンカツ定食")
            this.add("コロッケ定食")
            this.add("焼き魚定食")
            this.add("焼肉定食")
        }

        lvMenu.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList)
        lvMenu.setOnItemClickListener { parent, view, position, id ->
            val dialogFragment = OrderConfirmDialogFragment()
            dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
        }
    }
}

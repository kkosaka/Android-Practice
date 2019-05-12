package android.wings.websarva.com.intentsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lvMenu = findViewById<ListView>(R.id.lvMenu)
        var menuList = ArrayList<Map<String, String>>().apply {
            this.add(HashMap<String, String>(HashMap<String, String>().apply {
                this.put("name", "唐揚げ定食")
                this.put("price", "800")
            }))
            this.add(HashMap<String, String>(HashMap<String, String>().apply {
                this.put("name", "ハンバーグ定食")
                this.put("price", "900")
            }))
            this.add(HashMap<String, String>(HashMap<String, String>().apply {
                this.put("name", "サバの味噌煮定食")
                this.put("price", "600")
            }))
            this.add(HashMap<String, String>(HashMap<String, String>().apply {
                this.put("name", "生姜焼き定食")
                this.put("price", "750")
            }))
            this.add(HashMap<String, String>(HashMap<String, String>().apply {
                this.put("name", "日替わり定食")
                this.put("price", "600")
            }))
        }

        var from = arrayOf("name", "price")
        var to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter = SimpleAdapter(this, menuList, android.R.layout.simple_expandable_list_item_2, from, to)
        lvMenu.adapter = adapter

    }
}

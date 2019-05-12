package android.wings.websarva.com.menusample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val from = arrayOf("name", "price")
    private val to = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lvMenu = findViewById<ListView>(R.id.lvMenu)
        val menuList = createTeishokuList()
        val adapter = SimpleAdapter(this, menuList, R.layout.row, from, to)
        lvMenu.adapter = adapter

        lvMenu.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Map<String, Any>
            val intent = Intent(this, MenuThanksActivity::class.java).apply {
                this.putExtra("menuName", "${item["name"]}")
                this.putExtra("menuPrice", "${item["price"]}円")
            }
            startActivity(intent)
        }
    }

    private fun createTeishokuList(): ArrayList<Map<String, Any>> {
        val menuList = ArrayList<Map<String, Any>>().apply {
            this.add(HashMap<String, Any>(HashMap<String, Any>().apply {
                this["name"] = "唐揚げ定食"
                this["price"] = 800
                this["desc"] = "若鳥の唐揚げにサラダ、ご飯とみそ汁がつきます。"
            }))
            this.add(HashMap<String, Any>(HashMap<String, Any>().apply {
                this["name"] = "ハンバーグ定食"
                this["price"] = 900
                this["desc"] = "ハンバーグにサラダ、ご飯とみそ汁がつきます。"
            }))
            this.add(HashMap<String, Any>(HashMap<String, Any>().apply {
                this["name"] = "サバの味噌煮定食"
                this["price"] = 650
                this["desc"] = "サバ味噌煮にサラダ、ご飯とみそ汁がつきます。"
            }))
            this.add(HashMap<String, Any>(HashMap<String, Any>().apply {
                this["name"] = "生姜焼き定食"
                this["price"] = 700
                this["desc"] = "生姜焼きにサラダ、ご飯とみそ汁がつきます。"
            }))
            this.add(HashMap<String, Any>(HashMap<String, Any>().apply {
                this["name"] = "日替わり定食"
                this["price"] = 800
                this["desc"] = "生姜焼きにサラダ、ご飯とみそ汁がつきます。"
            }))
        }
        return menuList
    }
}

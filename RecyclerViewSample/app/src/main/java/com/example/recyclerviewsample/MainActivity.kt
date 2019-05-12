package com.example.recyclerviewsample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Toolbar>(R.id.toolbar).apply {
            this.setLogo(R.mipmap.ic_launcher_round)
            setSupportActionBar(this)
        }

        findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout).apply {
            this.title = getString(R.string.toolbar_title)
            this.setExpandedTitleColor(Color.WHITE)
            this.setCollapsedTitleTextColor(Color.LTGRAY)
        }

        findViewById<RecyclerView>(R.id.lvMenu).apply {
            val layout = LinearLayoutManager(this@MainActivity)
            this.layoutManager = layout
            this.adapter = RecyclerListAdapter(createTeishokuList())
            // 区切り線
            this.addItemDecoration(DividerItemDecoration(this@MainActivity, layout.orientation))
        }

    }

    private fun createTeishokuList(): ArrayList<Map<String, Any>> {
        return ArrayList<Map<String, Any>>().apply {
            add(HashMap(HashMap<String, Any>().apply {
                this["name"] = "唐揚げ定食"
                this["price"] = 800
                this["desc"] = "若鳥の唐揚げにサラダ、ご飯とみそ汁がつきます。"
            }))
            add(HashMap(HashMap<String, Any>().apply {
                this["name"] = "ハンバーグ定食"
                this["price"] = 900
                this["desc"] = "ハンバーグにサラダ、ご飯とみそ汁がつきます。"
            }))
            add(HashMap(HashMap<String, Any>().apply {
                this["name"] = "サバの味噌煮定食"
                this["price"] = 650
                this["desc"] = "サバ味噌煮にサラダ、ご飯とみそ汁がつきます。"
            }))
            add(HashMap(HashMap<String, Any>().apply {
                this["name"] = "生姜焼き定食"
                this["price"] = 700
                this["desc"] = "生姜焼きにサラダ、ご飯とみそ汁がつきます。"
            }))
            add(HashMap(HashMap<String, Any>().apply {
                this["name"] = "日替わり定食A"
                this["price"] = 800
                this["desc"] = "生姜焼きにサラダ、ご飯とみそ汁がつきます。"
            }))
            add(HashMap(HashMap<String, Any>().apply {
                this["name"] = "日替わり定食B"
                this["price"] = 800
                this["desc"] = "生姜焼きにサラダ、ご飯とみそ汁がつきます。"
            }))
            add(HashMap(HashMap<String, Any>().apply {
                this["name"] = "日替わり定食C"
                this["price"] = 800
                this["desc"] = "生姜焼きにサラダ、ご飯とみそ汁がつきます。"
            }))
        }
    }

    inner class RecyclerListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var tvMenuName: TextView = itemView.findViewById(R.id.tvMenuName)
        var tvMenuPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
//        var rowLayout:LinearLayout = itemView.findViewById(R.id.rowLayout)
    }

    inner class RecyclerListAdapter(private val listData: List<Map<String, Any>>): RecyclerView.Adapter<RecyclerListViewHolder>(){
        /**
         * row.xmlをインフレートしたビューホルダーオブジェクトを返す
         * クリックリスナのセット
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            val inflater = LayoutInflater.from(this@MainActivity)
            val view = inflater.inflate(R.layout.row, parent, false)
            view.setOnClickListener {
                it.findViewById<TextView>(R.id.tvMenuName).apply {
                    Toast.makeText(this@MainActivity, this.text, Toast.LENGTH_SHORT).show()
                }
            }
            return  RecyclerListViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            val item = listData[position]
            holder.tvMenuName.text = "${item["name"]}"
            holder.tvMenuPrice.text = "${item["price"]}"
//            holder.rowLayout.setBackgroundColor(Color.RED)
        }

        override fun getItemCount(): Int {
            return listData.size
        }
    }
}

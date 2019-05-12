package android.wings.websarva.com.fragmentsample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * 画面を横にすると、横用の画面に変化する
 */
class MenuListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var _parentActivity: Activity
    private var _isLayoutLand = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val menuThanksFrame = _parentActivity.findViewById<View>(R.id.menuThanksFrame)
        if(menuThanksFrame == null){
            _isLayoutLand = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _parentActivity = activity as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)

        var lvMenu = view.findViewById<ListView>(R.id.lvMenu)
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
        val adapter = SimpleAdapter(_parentActivity, menuList, android.R.layout.simple_expandable_list_item_2, from, to)
        lvMenu.adapter = adapter

        // フラグメントでは、onClickは使えないので、リスナーにイベントをセットする
        lvMenu.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Map<String, String>
            val bundle = Bundle().apply {
                this.putString("menuName", item.get("name"))
                this.putString("menuPrice", item.get("price"))
            }
            if(_isLayoutLand){
                val transaction = fragmentManager?.beginTransaction()
                val menuThanksFragment = MenuThanksFragment()
                menuThanksFragment.arguments = bundle
                transaction?.replace(R.id.menuThanksFrame, menuThanksFragment)
                transaction?.commit()
            }
            else{
                val intent = Intent(_parentActivity, MenuThanksActivity::class.java).apply {
                    this.putExtras(bundle)
                }
                startActivity(intent)
            }
        }

        return view
    }

}

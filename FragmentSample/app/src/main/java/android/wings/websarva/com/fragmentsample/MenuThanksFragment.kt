package android.wings.websarva.com.fragmentsample


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_menu_thanks.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MenuThanksFragment : Fragment() {
    private lateinit var _parentActivity: Activity
    private var _isLayoutLand = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _parentActivity = activity as Activity
        val menuListFragment = fragmentManager?.findFragmentById(R.id.fragmentMenuList)
        if(menuListFragment == null){
            _isLayoutLand = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)

         var extras = if (_isLayoutLand) arguments else _parentActivity.intent.extras

        val menuName = extras?.getString("menuName")
        val menuPrice = extras?.getString("menuPrice")

        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)

        tvMenuName.setText(menuName)
        tvMenuPrice.setText(menuPrice)

        val btBackButton = view.findViewById<Button>(R.id.btBackButton)
        btBackButton.setOnClickListener {
            if(_isLayoutLand){
                val transaction = fragmentManager?.beginTransaction()
                transaction?.remove(this)
                transaction?.commit()
            }else{
                _parentActivity.finish()
            }
        }

        // Inflate the layout for this fragment
        return view
    }


}

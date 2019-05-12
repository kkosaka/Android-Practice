package android.wings.websarva.com.menusample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        var menuName = intent.getStringExtra("menuName")
        var menuPrice = intent.getStringExtra("menuPrice")

        var tvMenuName = findViewById<TextView>(R.id.tvMenuName)
        var tvMenuPrice = findViewById<TextView>(R.id.tvMenuPrice)
        tvMenuName.text =menuName
        tvMenuPrice.text = menuPrice

    }

    public fun onBackButtonClick(view: View){
        finish();
    }
}

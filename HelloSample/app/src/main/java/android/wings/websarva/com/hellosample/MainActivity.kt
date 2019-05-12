package android.wings.websarva.com.hellosample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btClick = this.findViewById<Button>(R.id.btClick)
        btClick.setOnClickListener {
            tvOutput.text = "${etName.text}さん、こんにちは！"
        }

        var btClear = findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener {
            etName.setText("")
            tvOutput.text = ""
        }
    }
}

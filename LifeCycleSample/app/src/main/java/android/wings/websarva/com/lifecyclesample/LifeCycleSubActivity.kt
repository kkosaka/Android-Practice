package android.wings.websarva.com.lifecyclesample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class LifeCycleSubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_sub)
    }

    override fun onStart() {
        Log.i("LifeCycleSample", "Sub onStart() called")
        super.onStart()
    }

    override fun onRestart() {
        Log.i("LifeCycleSample", "Sub onRestart() called")
        super.onRestart()
    }

    override fun onResume() {
        Log.i("LifeCycleSample", "Sub onResume() called")
        super.onResume()
    }

    override fun onPause() {
        Log.i("LifeCycleSample", "Sub onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.i("LifeCycleSample", "Sub onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("LifeCycleSample", "Sub onDestroy() called")
        super.onDestroy()
    }

    fun onButtonClick(view: View){
        finish()
    }
}

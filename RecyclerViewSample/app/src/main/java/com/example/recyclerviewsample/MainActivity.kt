package com.example.recyclerviewsample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar

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

    }
}

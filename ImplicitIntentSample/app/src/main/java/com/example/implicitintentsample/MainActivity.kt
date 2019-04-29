package com.example.implicitintentsample

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    /**
     * 緯度を表示するテキストフィールド
     */
    private lateinit var _tvLatitude: TextView
    /**
     * 経度を表示するテキストフィールド
     */
    private lateinit var _tvLongitude: TextView

    /**
     * 緯度フィールド
     */
    private var _latitude:Double = 0.0
    /**
     * 経度フィールド
     */
    private var _longitude:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _tvLatitude = findViewById(R.id.tvLatitude)
        _tvLongitude = findViewById(R.id.tvLongitude)

        // 位置情報の追跡を開始
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION) , 1000)
            return
        }
        // LocationManagerオブジェクトを取得
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 位置情報が更新された際のリスナオブジェクトを生成
        val locationListener = GPSLocationListener()
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0f, locationListener)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        // ACCESS_FINE_LOCATIONに対するぱーにっしょんダイオログを許可選択したら
        if(requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // LocationManagerオブジェクトを取得
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            // 位置情報が更新された際のリスナオブジェクトを生成
            val locationListener = GPSLocationListener()
            // 再度ACCESS_FINE_LOCATIONの許可が下りてないかどうかチェックして、下りてないなら処理を中止
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return
            }
            // 位置情報の追跡を開始
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0f, locationListener)
        }
    }

    fun onMapSearchButtonClick(view: View){
        val etSearchWord = findViewById<EditText>(R.id.etSearchWord)
        val searchWord = etSearchWord.text.toString()
        try{
            val searchWord = URLEncoder.encode(searchWord, "UTF-8")
            // マップアプリと連携するURI文字列を生成
            val uriStr = "geo:0,0?q=$searchWord"
            val uri = Uri.parse(uriStr)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            // アクティビティを起動
            startActivity(intent)
        }catch (ex: UnsupportedEncodingException){
            Log.e("IntentStartActivity", "検索ワード変換失敗 $ex")
        }
    }

    fun onMapShowCurrentButtonClick(view:View){
        val uriStr = "geo:$_latitude,$_longitude"
        val uri = Uri.parse(uriStr)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private inner class GPSLocationListener: LocationListener{
        /**
         * Inner Class Access : https://qiita.com/kaleidot725/items/f2c6611648b04f7f41db
         */
        override fun onLocationChanged(location: Location) {
            this@MainActivity._latitude = location.latitude
            this@MainActivity._longitude = location.longitude
            this@MainActivity._tvLatitude.text = this@MainActivity._latitude.toString()
            this@MainActivity._tvLongitude.text = this@MainActivity._longitude.toString()
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
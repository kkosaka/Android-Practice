package android.wings.websarva.com.asyncsample

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * 天気予報を表示するアクティビティ
 * Livedoorの天気情報APIを呼ぶ
 */
class WeatherInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        // 画面部品ListViewを取得
        val lvCityList = findViewById<ListView>(R.id.lvCityList)
        val cityList = ArrayList<Map<String, String>>().apply {
            this.add(HashMap<String, String>().apply {
                this["name"] = "東京"
                this["id"] = "130010"
            })
            this.add(HashMap<String, String>().apply {
                this["name"] = "横浜"
                this["id"] = "140010"
            })
            this.add(HashMap<String, String>().apply {
                this["name"] = "甲府"
                this["id"] = "190010"
            })
            this.add(HashMap<String, String>().apply {
                this["name"] = "大阪"
                this["id"] = "270000"
            })
            this.add(HashMap<String, String>().apply {
                this["name"] = "神戸"
                this["id"] = "280010"
            })
        }
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleAdapter(this, cityList, android.R.layout.simple_expandable_list_item_1, from, to)
        lvCityList.adapter = adapter

        // タップした地域の天気情報を取得する
        lvCityList.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Map<String, String>
            var cityName = item["name"]
            var cityId = item["id"]

            val tvCityName = findViewById<TextView>(R.id.tvCityName)
            tvCityName.text = "${cityName}の天気： "

            val tvWeatherTelop = findViewById<TextView>(R.id.tvWeatherTelop)
            val tvWeatherDesc = findViewById<TextView>(R.id.tvWeatherDesc)

            val reciever = WeatherInfoReciever(tvWeatherTelop, tvWeatherDesc)
            reciever.execute(cityId)
        }
    }
}

/**
 * ライブドアの天気予報API(Weather Hacks)を非同期呼ぶクラス
 * リンク：http://weather.livedoor.com/weather_hacks/webservice
 */
private class WeatherInfoReciever(val tvWeatherTelop: TextView, val tvWeatherDesc : TextView) : AsyncTask<String, String, String>() {

    override fun doInBackground(vararg params: String?): String {
        val id = params[0]
        val urlStr = "http://weather.livedoor.com/forecast/webservice/json/v1?city=$id"
        var result = ""
        var httpURLConnection: HttpURLConnection? = null
        try{
            httpURLConnection = URL(urlStr).openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()
            result =  inputStream2String(httpURLConnection.inputStream)
        }catch (ex: MalformedURLException){
            // 省略
        }catch (ex: IOException){
            // 省略
        }finally {
            if(httpURLConnection != null){
                try{
                    httpURLConnection.inputStream.close()
                }catch (ex: IOException){

                }
            }
            return result
        }
    }

    override fun onPostExecute(result: String?) {
        var telop = ""
        var desc = ""
        try{
            val rootJSON = JSONObject(result)
            var descJSON = rootJSON.getJSONObject("description")
            desc = descJSON.getString("text")
            val forecasts = rootJSON.getJSONArray("forecasts")
            val forecastNow = forecasts.getJSONObject(0)
            telop = forecastNow.getString("telop")
        }catch (ex: JSONException){
            // 省略
        }
        tvWeatherTelop.text = telop
        tvWeatherDesc.text = desc
    }

    private fun inputStream2String(inputStream: InputStream) :String {
        return inputStream.bufferedReader().use{ it.readText() }
    }
}
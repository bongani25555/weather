package com.example.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class MainActivity  : AppCompatActivity() {
    private val API_KEY = "0172e3840e895fa9d87fb8eaa73d24ad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            val city = et_city.text.toString()
            if (city.isNotEmpty()) {
                fetchWeatherData(city)
            } else {
                Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchWeatherData(city: String) {
        val url =
            "http://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY&units=metric"

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = URL(url).readText()
                updateUI(apiResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateUI(response: String) {
        val jsonObject = JSONObject(response)
        val main = jsonObject.getJSONObject("main")
        val temp = main.getString("temp")
        val humidity = main.getString("humidity")

        runOnUiThread {
            tv_temperature.text = "Temperature: $temp°C"
            tv_humidity.text = "Humidity: $humidity%"
        }
    }
}
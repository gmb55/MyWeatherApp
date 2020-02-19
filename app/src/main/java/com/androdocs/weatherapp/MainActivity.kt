package com.androdocs.weatherapp

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cities_list.*
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {

    var cities: ArrayList<String> = ArrayList(7)
    var icons: ArrayList<String> = ArrayList(7)
    var temps: ArrayList<String> = ArrayList(7)
    var pressure: ArrayList<String> = ArrayList(7)
    var humidity: ArrayList<String> = ArrayList(7)
    var wind: ArrayList<String> = ArrayList(7)
    lateinit var location : String
    lateinit var recyclerView : RecyclerView
    val key = "&appid=8118ed6ee68db2debfaaa5a44c832918"

    lateinit var newBTN : Button


    fun coordinate(completion: (String) -> Unit) {

        Location(this, this).getLastLocation(completion)
    }

    fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_cities_list)

        if (isLocationEnabled()) {

            coordinate { locationString ->
                location = locationString
                getCitiesNames(location, this).execute()
            }

        } else {
            Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }


    }

    inner class getCitiesNames(var loc : String, var con : Context) : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
          var response : String

            val API = "https://api.openweathermap.org/data/2.5/find?$loc&cnt=7&units=metric$key"

            response = try {
                URL(API).readText(Charsets.UTF_8)
            } catch (e: Exception) {
                "1"
            }

            return response
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

            val jsonObj = JSONObject(result)
            val list = jsonObj.getJSONArray("list")


        for(y in 0 until 7) {
            val x = list.getJSONObject(y).getString("name")
            cities.add(y,x)
            val z: String = list.getJSONObject(y).getJSONArray("weather").getJSONObject(0).getString("icon")
            icons.add(y,z)
            val v: String = list.getJSONObject(y).getJSONObject("main").getString("temp") + "°C"
            temps.add(y,v)
            val p: String = list.getJSONObject(y).getJSONObject("main").getString("pressure") + "hPa"
            pressure.add(y,p)
            val h: String = list.getJSONObject(y).getJSONObject("main").getString("humidity") + "%"
            humidity.add(y,h)
            val w: String = list.getJSONObject(y).getJSONObject("wind").getString("speed") + "m/s"
            wind.add(y,w)

        }


            rv_cities_list.layoutManager = LinearLayoutManager(con)
            rv_cities_list.adapter = IconAdapter(cities ,icons, temps, pressure, humidity, wind, con)
        }
    }
}
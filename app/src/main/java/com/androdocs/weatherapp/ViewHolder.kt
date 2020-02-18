package com.androdocs.weatherapp

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.cities_list.view.*


class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val tvCityName = view.tv_city_name
    val ivWeatherIcon: ImageView = view.iv_weather_icon
    val tvTemp = view.tv_temp
    val pressureTV = view.pressure
    val humidityTV = view.humidity
    val windTV = view.wind
    val main: LinearLayout = view.mainLayout
    val details: LinearLayout = view.detailsLayout
}
package com.androdocs.weatherapp

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cities_list.view.*

class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val tvCityName = view.tv_city_name
    val ivWeatherIcon: ImageView = view.iv_weather_icon
    val tvTemp = view.tv_temp
}
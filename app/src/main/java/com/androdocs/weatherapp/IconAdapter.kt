package com.androdocs.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class IconAdapter(val items: ArrayList<String>,val items2: ArrayList<String>, val items3: ArrayList<String>, val context : Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return         ViewHolder(LayoutInflater.from(context).inflate(R.layout.cities_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var icon = items2[position]

        holder.tvCityName?.text = items[position]

        Picasso.get().load("http://openweathermap.org/img/wn/$icon@2x.png").into(holder.ivWeatherIcon)

        holder.tvTemp?.text = items3[position]
    }


}
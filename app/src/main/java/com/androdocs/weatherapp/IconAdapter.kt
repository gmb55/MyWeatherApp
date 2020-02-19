package com.androdocs.weatherapp

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cities_list.view.*

class IconAdapter(val items: ArrayList<String>,val items2: ArrayList<String>, val items3: ArrayList<String>, val items4: ArrayList<String>, val items5: ArrayList<String>, val items6: ArrayList<String>, val context : Context) : RecyclerView.Adapter<ViewHolder>() {

    var counter = ArrayList<Int>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return         ViewHolder(LayoutInflater.from(context).inflate(R.layout.cities_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.details.visibility = View.GONE

        for (i in items.indices)
        {
            counter.add(0)
        }

        var icon = items2[position]

        holder.tvCityName?.text = items[position]

        Picasso.get().load("http://openweathermap.org/img/wn/$icon@2x.png").into(holder.ivWeatherIcon)

        holder.tvTemp?.text = items3[position]

        holder.details.pressure.text = items4[position]

        holder.details.humidity.text = items5[position]

        holder.details.wind.text = items6[position]


        holder.main.setOnClickListener {
            if (counter.get(position) % 2 ==0) {
                holder.details.visibility = View.VISIBLE
            } else {
                holder.details.visibility = View.GONE
            }

            counter[position] = counter[position] + 1
        }


    }


}
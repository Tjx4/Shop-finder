package com.entersekt.shopfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entersekt.outlets.models.City
import com.entersekt.shopfinder.R

class CitiesAdapter(val context: Context, private val cities: List<City>) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var cityClickListener: CityClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.city_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities?.get(position)
        holder.cityNameTv.text = city?.name
        holder.mallsTv.text =
            "${city?.malls?.size ?: 0} mall${if (city?.malls?.size == 1) "" else "s"}"

        city.malls?.let {
            val mallsLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            mallsLayoutManager.initialPrefetchItemCount = cities?.size
            holder.mallsRv?.layoutManager = mallsLayoutManager
            var mallsAdapter = MallsAdapter(context, it)
//mallsAdapter.setCityClickListener(this)
            holder.mallsRv?.adapter = mallsAdapter
        }
    }

    private fun toggleMalls(toggleView: View){
        toggleView?.visibility = if(toggleView?.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var cityNameTv: TextView = itemView.findViewById(R.id.tvCityName)
        internal var mallsTv: TextView = itemView.findViewById(R.id.tvMalls)
        internal var mallsRv: RecyclerView = itemView.findViewById(R.id.rvMalls)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            toggleMalls(mallsRv)
            cityClickListener?.onCityClickListener(view, adapterPosition)
        }
    }

    override fun getItemCount() = cities?.size

    fun setCityClickListener(cityClickListener: CityClickListener) {
        this.cityClickListener = cityClickListener
    }

    interface CityClickListener {
        fun onCityClickListener(view: View, position: Int)
    }

}
package com.entersekt.shopfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.entersekt.outlets.models.Shop
import com.entersekt.shopfinder.R

class ShopsAdapter(val context: Context, private val shops: List<Shop>) : RecyclerView.Adapter<ShopsAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.shop_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shop = shops?.get(position)
        holder.shopNameTv.text = shop?.name
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var shopNameTv: TextView = itemView.findViewById(R.id.tvShopName)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
        }
    }

    override fun getItemCount() = shops?.size

}
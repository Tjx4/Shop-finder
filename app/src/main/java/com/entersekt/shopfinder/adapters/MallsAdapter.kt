package com.entersekt.shopfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entersekt.outlets.models.Mall
import com.entersekt.shopfinder.R

class  MallsAdapter(val context: Context, private val malls: List<Mall>) : RecyclerView.Adapter<MallsAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.mall_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mall = malls?.get(position)
        holder.mallNameTv.text = mall?.name
        holder.shopsTv.text = "${mall?.shops?.size ?: 0} shop${if(mall?.shops?.size == 1) "" else "s"}"

        mall.shops?.let {
            val mallsLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            mallsLayoutManager.initialPrefetchItemCount = malls?.size
            holder.shopsRv?.layoutManager = mallsLayoutManager
            var mallsAdapter = ShopsAdapter(context, it)
//mallsAdapter.setCityClickListener(this)
            holder.shopsRv?.adapter = mallsAdapter
        }
    }

    private fun toggleMalls(toggleView: View){
        toggleView?.visibility = if(toggleView?.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var mallNameTv: TextView = itemView.findViewById(R.id.tvMallName)
        internal var shopsTv: TextView = itemView.findViewById(R.id.tvShops)
        internal var shopsRv: RecyclerView = itemView.findViewById(R.id.rvShops)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            toggleMalls(shopsRv)
        }
    }

    override fun getItemCount() = malls?.size

}
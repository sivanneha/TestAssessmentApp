package com.example.mytest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListItemAdapter(var mList: List<ListItemData>) :
    RecyclerView.Adapter<ListItemAdapter.LanguageViewHolder>() {
    val filteredList = ArrayList<ListItemData>()
    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
    }

    fun setFilteredList(mList: List<ListItemData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list , parent , false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.titleTv.text = mList[position].title
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
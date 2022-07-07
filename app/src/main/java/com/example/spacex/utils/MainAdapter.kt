package com.example.spacex.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.R
import com.example.spacex.databinding.RecyclerViewItemBinding
import com.example.spacex.models.ResponseModelItem

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var list = mutableListOf<ResponseModelItem>()

    fun setList(list: List<ResponseModelItem>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    class MainViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val launch = list[position]
        holder.binding.name.text = launch.name
        holder.binding.date.text = launch.date_local
        holder.binding.flight.text = launch.cores.map { it.flight }.toString()

        if (launch.success) {
            holder.binding.success.text = "Успех"
        } else {
            holder.binding.success.text = "Неудача"
        }

        Glide.with(MAIN)
            .load(launch.links.patch.small)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.avatar)
    }

    override fun getItemCount() = list.size
}
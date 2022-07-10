package com.example.spacex.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.CrewItemBinding
import com.example.spacex.databinding.RecyclerViewItemBinding
import com.example.spacex.models.ResponseCrewItem

class CrewAdapter: RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    private var list = mutableListOf<ResponseCrewItem>()

    fun setList(list: List<ResponseCrewItem>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    class CrewViewHolder(val binding: CrewItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CrewItemBinding.inflate(inflater, parent, false)
        return CrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val crewItem = list[position]
        holder.binding.crewName.text = crewItem.name
        holder.binding.crewAgency.text = crewItem.agency
        holder.binding.crewStatus.text = crewItem.status
    }

    override fun getItemCount() = list.size

}
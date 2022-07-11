package com.example.spacex.utils


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.spacex.R
import com.example.spacex.databinding.RecyclerViewItemBinding
import com.example.spacex.models.ResponseModelItem
import com.example.spacex.screens.main.MainFragment
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var list = mutableListOf<ResponseModelItem>()

    fun setList(list: List<ResponseModelItem>) {
        this.list = list.toMutableList()
        this.list.sortWith(Comparator { lhs, rhs ->
            if (lhs.date_utc > rhs.date_utc) -1 else if (lhs.id < rhs.id) 1 else 0
        })
        notifyDataSetChanged()
    }

    class MainViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val launch = list[position]
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val output = formatter.format(parser.parse(launch.date_utc))

        holder.binding.date.text = output
        holder.binding.flight.text = launch.cores.map { it.flight }.toString()

        if (launch.success) {
            holder.binding.success.text = "Успех"
        } else {
            holder.binding.success.text = "Неудача"
        }

        holder.binding.avatar
            .load(launch.links.patch.small) {
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_launcher_foreground)
            }
    }

    override fun getItemCount() = list.size

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.clickLaunch(list[holder.bindingAdapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

}


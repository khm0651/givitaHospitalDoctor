package com.example.vitameanshospitaldoctor.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.DetailInfo
import com.example.vitameanshospitaldoctor.databinding.DetailItemLayoutBinding

class DetailInfoAdapter: RecyclerView.Adapter<DetailInfoAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DetailInfo>(){
        override fun areItemsTheSame(oldItem: DetailInfo, newItem: DetailInfo): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: DetailInfo, newItem: DetailInfo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)

    inner class ViewHolder(private val binding: DetailItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: DetailInfo){
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailInfoAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.detail_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailInfoAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}
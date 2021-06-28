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
import com.example.vitameanshospitaldoctor.databinding.DetailItemOneLayoutBinding

class DetailInfoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DetailInfo>(){
        override fun areItemsTheSame(oldItem: DetailInfo, newItem: DetailInfo): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: DetailInfo, newItem: DetailInfo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)

    inner class BothViewHolder(private val binding: DetailItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: DetailInfo){
            binding.apply {

                day.text = item.day
                date.text = item.date
                immWakeupBp.text = if(item.detailBloodPressure!!.immediatleyWakeup.shrink == null && item.detailBloodPressure!!.immediatleyWakeup.relaxation == null) "-" else "${item.detailBloodPressure!!.immediatleyWakeup.shrink}\n/${item.detailBloodPressure!!.immediatleyWakeup.relaxation}"
                bBBp.text = if(item.detailBloodPressure!!.beforeBreakfast.shrink == null && item.detailBloodPressure!!.beforeBreakfast.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeBreakfast.shrink}\n/${item.detailBloodPressure!!.beforeBreakfast.relaxation}"
                aBBp.text = if(item.detailBloodPressure!!.afterBreakfast.shrink == null && item.detailBloodPressure!!.afterBreakfast.relaxation == null) "-" else "${item.detailBloodPressure!!.afterBreakfast.shrink}\n/${item.detailBloodPressure!!.afterBreakfast.relaxation}"
                bLBp.text = if(item.detailBloodPressure!!.beforeLunch.shrink == null && item.detailBloodPressure!!.beforeLunch.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeLunch.shrink}\n/${item.detailBloodPressure!!.beforeLunch.relaxation}"
                aLBp.text = if(item.detailBloodPressure!!.afterLunch.shrink == null && item.detailBloodPressure!!.afterLunch.relaxation == null) "-" else "${item.detailBloodPressure!!.afterLunch.shrink}\n/${item.detailBloodPressure!!.afterLunch.relaxation}"
                bDBp.text = if(item.detailBloodPressure!!.beforeDinner.shrink == null && item.detailBloodPressure!!.beforeDinner.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeDinner.shrink}\n/${item.detailBloodPressure!!.beforeDinner.relaxation}"
                aDBp.text = if(item.detailBloodPressure!!.afterDinner.shrink == null && item.detailBloodPressure!!.afterDinner.relaxation == null) "-" else "${item.detailBloodPressure!!.afterDinner.shrink}\n/${item.detailBloodPressure!!.afterDinner.relaxation}"
                bSBp.text = if(item.detailBloodPressure!!.beforeSleep.shrink == null && item.detailBloodPressure!!.beforeSleep.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeSleep.shrink}\n/${item.detailBloodPressure!!.beforeSleep.relaxation}"
                immWakeupBs.text = if(item.detailBloodSugar!!.immediatleyWakeup.bloodSugar == null) "-" else "${item.detailBloodSugar!!.immediatleyWakeup.bloodSugar}"
                bBBs.text = if(item.detailBloodSugar!!.beforeBreakfast.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeBreakfast.bloodSugar}"
                aBBs.text = if(item.detailBloodSugar!!.afterBreakfast.bloodSugar == null) "-" else "${item.detailBloodSugar!!.afterBreakfast.bloodSugar}"
                bLBs.text = if(item.detailBloodSugar!!.beforeLunch.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeLunch.bloodSugar}"
                aLBs.text = if(item.detailBloodSugar!!.afterLunch.bloodSugar == null) "-" else "${item.detailBloodSugar!!.afterLunch.bloodSugar}"
                bDBs.text = if(item.detailBloodSugar!!.beforeDinner.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeDinner.bloodSugar}"
                aDBs.text = if(item.detailBloodSugar!!.afterDinner.bloodSugar == null) "-" else "${item.detailBloodSugar!!.afterDinner.bloodSugar}"
                bSBs.text = if(item.detailBloodSugar!!.beforeSleep.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeSleep.bloodSugar}"

            }
        }
    }

    inner class OneViewHolder(private val binding: DetailItemOneLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: DetailInfo){
            binding.apply {
                day.text = item.day
                date.text = item.date
                if(item.detailBloodPressure == null){
                    title.text = "혈당"
                    subTitle.text = "(mg/dl)"
                    immWakeup.text = if(item.detailBloodSugar!!.immediatleyWakeup.bloodSugar == null) "-" else "${item.detailBloodSugar!!.immediatleyWakeup.bloodSugar}"
                    bB.text = if(item.detailBloodSugar!!.beforeBreakfast.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeBreakfast.bloodSugar}"
                    aB.text = if(item.detailBloodSugar!!.afterBreakfast.bloodSugar == null) "-" else "${item.detailBloodSugar!!.afterBreakfast.bloodSugar}"
                    bL.text = if(item.detailBloodSugar!!.beforeLunch.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeLunch.bloodSugar}"
                    aL.text = if(item.detailBloodSugar!!.afterLunch.bloodSugar == null) "-" else "${item.detailBloodSugar!!.afterLunch.bloodSugar}"
                    bD.text = if(item.detailBloodSugar!!.beforeDinner.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeDinner.bloodSugar}"
                    aD.text = if(item.detailBloodSugar!!.afterDinner.bloodSugar == null) "-" else "${item.detailBloodSugar!!.afterDinner.bloodSugar}"
                    bS.text = if(item.detailBloodSugar!!.beforeSleep.bloodSugar == null) "-" else "${item.detailBloodSugar!!.beforeSleep.bloodSugar}"
                }else{
                    title.text = "혈압"
                    subTitle.text = "(mmHg)"
                    immWakeup.text = if(item.detailBloodPressure!!.immediatleyWakeup.shrink == null && item.detailBloodPressure!!.immediatleyWakeup.relaxation == null) "-" else "${item.detailBloodPressure!!.immediatleyWakeup.shrink}\n/${item.detailBloodPressure!!.immediatleyWakeup.relaxation}"
                    bB.text = if(item.detailBloodPressure!!.beforeBreakfast.shrink == null && item.detailBloodPressure!!.beforeBreakfast.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeBreakfast.shrink}\n/${item.detailBloodPressure!!.beforeBreakfast.relaxation}"
                    aB.text = if(item.detailBloodPressure!!.afterBreakfast.shrink == null && item.detailBloodPressure!!.afterBreakfast.relaxation == null) "-" else "${item.detailBloodPressure!!.afterBreakfast.shrink}\n/${item.detailBloodPressure!!.afterBreakfast.relaxation}"
                    bL.text = if(item.detailBloodPressure!!.beforeLunch.shrink == null && item.detailBloodPressure!!.beforeLunch.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeLunch.shrink}\n/${item.detailBloodPressure!!.beforeLunch.relaxation}"
                    aL.text = if(item.detailBloodPressure!!.afterLunch.shrink == null && item.detailBloodPressure!!.afterLunch.relaxation == null) "-" else "${item.detailBloodPressure!!.afterLunch.shrink}\n/${item.detailBloodPressure!!.afterLunch.relaxation}"
                    bD.text = if(item.detailBloodPressure!!.beforeDinner.shrink == null && item.detailBloodPressure!!.beforeDinner.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeDinner.shrink}\n/${item.detailBloodPressure!!.beforeDinner.relaxation}"
                    aD.text = if(item.detailBloodPressure!!.afterDinner.shrink == null && item.detailBloodPressure!!.afterDinner.relaxation == null) "-" else "${item.detailBloodPressure!!.afterDinner.shrink}\n/${item.detailBloodPressure!!.afterDinner.relaxation}"
                    bS.text = if(item.detailBloodPressure!!.beforeSleep.shrink == null && item.detailBloodPressure!!.beforeSleep.relaxation == null) "-" else "${item.detailBloodPressure!!.beforeSleep.shrink}\n/${item.detailBloodPressure!!.beforeSleep.relaxation}"
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return differ.currentList[position].viewType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if(viewType == 0) {
            return BothViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.detail_item_layout,
                    parent,
                    false
                )
            )
        } else{
            return OneViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.detail_item_one_layout,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        when(holder){
            is BothViewHolder ->  holder.bind(item)
            is OneViewHolder -> holder.bind(item)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size
}
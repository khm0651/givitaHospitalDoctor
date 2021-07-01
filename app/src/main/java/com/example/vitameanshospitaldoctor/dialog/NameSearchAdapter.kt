package com.example.vitameanshospitaldoctor.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.example.vitameanshospitaldoctor.databinding.NameSearchItemBinding
import com.example.vitameanshospitaldoctor.showSnackbar
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class NameSearchAdapter:
    RecyclerView.Adapter<NameSearchAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    private val diffCallback = object : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.adminId == newItem.adminId
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NameSearchAdapter.ViewHolder {
        mContext = parent.context
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.name_search_item,
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val binding: NameSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserData) {
            binding.apply {

                val format = SimpleDateFormat("yy.MM.dd")
                val format2 = SimpleDateFormat("yyMMdd")

                tvNSearchId.text = item.id.toString()
                tvNSearchName.text = item.userName
                tvNSearchSex.text = item.sex
                tvNSearchAge.text = item.age.toString()
                tvNSearchDisease.text = item.diseaseType
                tvNSearchBp.text = "${item.shrinkage}/${item.relaxation}"
                tvNSearchBpDate.text = format2.format(item.bpRegistrationDate?.time)
                tvNSearchBs.text = item.bloodSugar.toString()
                tvNSearchBsDate.text = format2.format(item.bsRegistrationDate?.time)
                tvNSearchLastvisitDate.text = format.format(item.lastVisitDate?.time)
                tvNSearchRequest.text=format.format(item.measureRequestDate?.time)
                tvNSearchReceive.text = item.receiveOrNot
                tvNSearchReceiveDate.text = format.format(item.measureRequestDate?.time)

                if (item.receiveOrNot == "Y" && item.canRegisterEMR) {
                   btnNameSearchReceive.background =
                        mContext.getDrawable(R.drawable.all_radius_fill_grey_bg)
                    btnNameSearchReceive.isEnabled = true
                } else {
                    btnNameSearchReceive.setBackgroundColor(mContext.getColor(R.color.enable))
                    btnNameSearchReceive.isEnabled = false
                }

                btnNameSearchEmr.setOnClickListener {
                    item.canRegisterEMR = false
                    it.showSnackbar("${item.userName}님의 정보를 EMR에 전송하였습니다", Snackbar.LENGTH_SHORT)
                    notifyDataSetChanged()
                }

                btnNameSearchReceive.setOnClickListener {
                    it.showSnackbar("${item.userName}님께 측정요청을 보냈습니다", Snackbar.LENGTH_SHORT)
                }

                btnNameSearchEducation.setOnClickListener {
                    it.showSnackbar("${item.userName}님께 교육 요청을 보냈습니다", Snackbar.LENGTH_SHORT)
                }
            }
        }
    }


    override fun onBindViewHolder(holder: NameSearchAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size



}
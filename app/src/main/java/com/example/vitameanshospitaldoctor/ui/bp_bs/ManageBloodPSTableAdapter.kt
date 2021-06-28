package com.example.vitameanshospitaldoctor.ui.bp_bs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.Patient
import com.example.vitameanshospitaldoctor.databinding.ManageItemLayoutBinding
import com.example.vitameanshospitaldoctor.showSnackbar
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class ManageBloodPSTableAdapter( val navigate: ()-> Unit ): RecyclerView.Adapter<ManageBloodPSTableAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    private val diffCallback = object : DiffUtil.ItemCallback<Patient>(){
        override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffCallback)

    inner class ViewHolder(private val binding: ManageItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Patient) = with(item){
            binding.apply {
                binding.root.setOnClickListener{ navigate() }
                val format = SimpleDateFormat("yy.MM.dd")
                val format2 = SimpleDateFormat("yyMMdd")
                noTv.text = no.toString()
                nameTv.text = name
                genderTv.text =gender
                ageTv.text = age.toString()
                diseaseTv.text = disease
                bloodPressureTv.text = "${shrinkage}/${relaxation}"
                bloodPressureDateTv.text = format2.format(bpRegistrationDate.time)
                bloodSugarTv.text = bloodSugar.toString()
                bloodSugarDateTv.text = format2.format(bsRegistrationDate.time)
                latestVisitTv.text = format.format(latestVisit.time)
                requestDateTv.text = format.format(requestDate.time)
                requestCheckTv.text = requestCheck

                if(requestCheck == "N"){
                    individualRegistrationBtn.setBackgroundColor(mContext.getColor(R.color.enable))
                    individualRegistrationBtn.isEnabled = false
                }

                individualRegistrationBtn.setOnClickListener {
                    it.showSnackbar("${name}님의 정보를 EMR에 전송하였습니다",Snackbar.LENGTH_SHORT)
                }

                requestBtn.setOnClickListener {
                    it.showSnackbar("${name}님께 측정요청을 보냈습니다",Snackbar.LENGTH_SHORT)
                }

                recommendBtn.setOnClickListener {
                    it.showSnackbar("${name}님께 식이/운동 추천을 보냈습니다",Snackbar.LENGTH_SHORT)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        mContext = parent.context
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.manage_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        if(position % 2 != 0) holder.itemView.setBackgroundColor(mContext.resources.getColor(R.color.odd,null))
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}
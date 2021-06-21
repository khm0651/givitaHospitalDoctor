package com.example.vitameanshospitaldoctor.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.Patient
import com.example.vitameanshospitaldoctor.databinding.ManageItemLayoutBinding
import java.text.SimpleDateFormat

class ManageBloodPSTableAdapter: RecyclerView.Adapter<ManageBloodPSTableAdapter.ViewHolder>(){

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
                val format = SimpleDateFormat("yy.MM.dd")
                val format2 = SimpleDateFormat("yyMMdd")
                noTv.text = no.toString()
                manageIdTv.text = uid
                registerDateTv.text = format.format(registration.time)
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

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ManageBloodPSTableAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.manage_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ManageBloodPSTableAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}
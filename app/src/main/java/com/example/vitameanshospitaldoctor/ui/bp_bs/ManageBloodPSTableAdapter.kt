package com.example.vitameanshospitaldoctor.ui.bp_bs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.example.vitameanshospitaldoctor.databinding.ManageItemLayoutBinding
import com.example.vitameanshospitaldoctor.dialog.PersonRecommendDialog
import com.example.vitameanshospitaldoctor.showSnackbar
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class ManageBloodPSTableAdapter(val fragmentManager: FragmentManager, val navigate: ()-> Unit ): RecyclerView.Adapter<ManageBloodPSTableAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    private val diffCallback = object : DiffUtil.ItemCallback<UserData>(){
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.adminId == newItem.adminId
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffCallback)

    inner class ViewHolder(private val binding: ManageItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: UserData) {
            binding.apply {
                binding.root.setOnClickListener { navigate() }
                val format = SimpleDateFormat("yy.MM.dd")
                val format2 = SimpleDateFormat("yyMMdd")

                noTv.text = item.id.toString()
                nameTv.text = item.userName
                genderTv.text = item.sex
                ageTv.text = item.age.toString()
                diseaseTv.text = item.diseaseType
                bloodPressureTv.text = "${item.shrinkage}/${item.relaxation}"
                bloodPressureDateTv.text = format2.format(item.bpRegistrationDate?.time)
                bloodSugarTv.text = item.bloodSugar.toString()
                bloodSugarDateTv.text = format2.format(item.bsRegistrationDate?.time)
                latestVisitTv.text = format.format(item.lastVisitDate?.time)
                requestDateTv.text = format.format(item.measureRequestDate?.time)
                requestCheckTv.text = item.receiveOrNot

                if(item.receiveOrNot == "Y" && item.canRegisterEMR){
                    individualRegistrationBtn.background =
                        mContext.getDrawable(R.drawable.all_radius_fill_grey_bg)
                    individualRegistrationBtn.isEnabled = true
                }else{
                    individualRegistrationBtn.setBackgroundColor(mContext.getColor(R.color.enable))
                    individualRegistrationBtn.isEnabled = false
                }

                checkbox.isChecked = item.isChecked


                checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                    item.isChecked = isChecked
                }

                individualRegistrationBtn.setOnClickListener {
                    item.canRegisterEMR = false
                    it.showSnackbar("${item.userName}님의 정보를 EMR에 전송하였습니다", Snackbar.LENGTH_SHORT)
                    notifyDataSetChanged()
                }

                requestBtn.setOnClickListener {
                    it.showSnackbar("${item.userName}님께 측정요청을 보냈습니다", Snackbar.LENGTH_SHORT)
                }

                recommendBtn.setOnClickListener {
                    PersonRecommendDialog().show(fragmentManager,"PersonRecommendDialog")
//                    it.showSnackbar("${item.userName}님께 식이/운동 추천을 보냈습니다", Snackbar.LENGTH_SHORT)
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
        else holder.itemView.setBackgroundColor(mContext.resources.getColor(R.color.white,null))
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}
package com.example.vitameanshospitaldoctor.ui.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.Notification
import com.example.vitameanshospitaldoctor.databinding.FragmentNotificationListItemLayoutBinding

class NotiFragAdapter(private var noti: MutableList<Notification>) : RecyclerView.Adapter<NotiFragAdapter.ItemViewHolder>() {

    private lateinit var removeItem: ImageView

    private val diffUtil = object : DiffUtil.ItemCallback<Notification>() {
        // 두 아이템이 동일한 아이템인지 체크한다.
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.notiText == newItem.notiText
        }

        // 두 아이템이 동일한 내용물을 가지고 있는지 체크한다. areItemsTheSame()가 true일 때만 호출된다.
        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fragment_notification_list_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
        removeItem.setOnClickListener(View.OnClickListener { rmItem(position) })
    }

    override fun getItemCount(): Int = noti.size

    inner class ItemViewHolder(private val binding: FragmentNotificationListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Notification) = with(item) {
            binding.apply {
                notiTV.text = notiText.toString()
                notiBtn.text = notiButton.toString()
                removeItem = notiBtnRemove
            }
        }
    }

    private fun rmItem(position: Int) {
        noti.removeAt(position)
        notifyDataSetChanged()
    }
}
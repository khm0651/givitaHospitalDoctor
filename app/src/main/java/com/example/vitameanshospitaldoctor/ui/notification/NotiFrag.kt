package com.example.vitameanshospitaldoctor.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.Notification
import com.example.vitameanshospitaldoctor.databinding.FragmentNotificationBinding
import java.text.SimpleDateFormat
import java.util.*

class NotiFrag: Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notiFragAdapter: NotiFragAdapter
    private val list = mutableListOf<Notification>()
    private lateinit var calendar_format: SimpleDateFormat
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var date: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setData()
    }

    private fun initAdapter() {
        notiFragAdapter = NotiFragAdapter{item -> doClick(item)}
        binding.apply {
            notiList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            notiList.setHasFixedSize(true) // 항상 동일한 크기로 정해두어 메모리 낭비를 최소화
            notiList.adapter = notiFragAdapter
        }
    }

    private fun setData() {
        list.apply {
            time(-4)
            add(Notification("조윤진님 외 2명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록",date))
            time(1)
            add(Notification("김태양님 외 3명이\n아직 측정하지 않았습니다.\n알람을 보낼까요?","측정 요청",date))
            time(1)
            add(Notification("곽하민님 외 4명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","측정 요청",date))
            add(Notification("염승현님 외 5명이\n아직 측정하지 않았습니다.\n알람을 보낼까요?","EMR 일괄등록",date))
            add(Notification("김주영님 외 6명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록",date))
            time(1)
            add(Notification("김주영1님 외 6명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록",date))
            add(Notification("김주영2님 외 6명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록",date))
            add(Notification("김주영3님 외 6명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록",date))
            time(1)
            add(Notification("김주영4님 외 6명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록",date))
            reverse()
        }
        notiFragAdapter.differ.submitList(list)
    }

    private fun time(day: Int) {
        calendar_format = SimpleDateFormat("yy-MM-dd",Locale.KOREA)
        calendar.add(Calendar.DAY_OF_WEEK,day)
        date = calendar_format.format(calendar.time).toString()
    }

    fun doClick(pos:Int) {
        //do things related to activity
        println(pos)
        list.removeAt(pos)
        binding.notiList.adapter = notiFragAdapter
        notiFragAdapter.differ.submitList(list)
    }
}
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

class NotiFrag: Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notiFragAdapter: NotiFragAdapter

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
        notiFragAdapter = NotiFragAdapter()
        binding.apply {
            notiList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            notiList.setHasFixedSize(true) // 항상 동일한 크기로 정해두어
            notiList.adapter = notiFragAdapter
        }
    }

    private fun setData() {
        val list = mutableListOf<Notification>()
        list.add(Notification("조윤진님 외 2명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록"))
        list.add(Notification("김태양님 외 3명이\n아직 측정하지 않았습니다.\n알람을 보낼까요?","측정 요청"))
        list.add(Notification("곽하민님 외 4명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","측정 요청"))
        list.add(Notification("염승현님 외 5명이\n아직 측정하지 않았습니다.\n알람을 보낼까요?","EMR 일괄등록"))
        list.add(Notification("김주영님 외 6명이\n측정값을 보냈습니다.\nEMR에 등록할까요?","EMR 일괄등록"))
        notiFragAdapter.differ.submitList(list)
    }
}


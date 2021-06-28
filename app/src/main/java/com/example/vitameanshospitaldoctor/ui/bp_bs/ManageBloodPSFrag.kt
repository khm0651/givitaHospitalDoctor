package com.example.vitameanshospitaldoctor.ui.bp_bs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.databinding.FragmentManageBloodPSBinding
import com.example.vitameanshospitaldoctor.databinding.ManageItemLayoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ManageBloodPSFrag : Fragment() {

    lateinit var binding: FragmentManageBloodPSBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBloodPSBinding.inflate(inflater,container,false)

        return binding.root
    }



}
package com.example.vitameanshospitaldoctor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var tabLayout: TabLayout
    lateinit var viewpager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        tabLayout = binding.tabs
        viewpager = binding.viewPager

        viewpager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout,viewpager) { tab, pos ->
            tab.text = getTabTitle(pos)
        }.attach()
        for(i in 0 until tabLayout.tabCount){
            val view = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val param = view.layoutParams as ViewGroup.MarginLayoutParams
            param.rightMargin = 20
            val param2 = view.layoutParams
            param2.width = 500
            view.requestLayout()
        }
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            BP_BS_PATIENT_MANAGE_PAGE -> getString(R.string.bp_bs_patient_manage_title)
            OBESITY_PATIENT_MANAGE_PAGE -> getString(R.string.obesity_patient_manage_title)
            HIGH_BP_PATIENT_MANAGE_PAGE -> getString(R.string.high_bp_patient_manage_title)
            else -> null
        }
    }
}
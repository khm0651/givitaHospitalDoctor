package com.example.vitameanshospitaldoctor.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vitameanshospitaldoctor.ui.bp_bs.ManageBloodPSFrag
import com.example.vitameanshospitaldoctor.ui.high_blood_pressure.HightBPFragment
import com.example.vitameanshospitaldoctor.ui.obese.ObeseFragment
import java.lang.IndexOutOfBoundsException

const val BP_BS_PATIENT_MANAGE_PAGE = 0
const val OBESITY_PATIENT_MANAGE_PAGE = 1
const val HIGH_BP_PATIENT_MANAGE_PAGE = 2

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, ()-> Fragment> = mapOf(
        BP_BS_PATIENT_MANAGE_PAGE to { ManageBloodPSFrag() },
        OBESITY_PATIENT_MANAGE_PAGE to { ObeseFragment() },
        HIGH_BP_PATIENT_MANAGE_PAGE to { HightBPFragment() }
    )
    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}
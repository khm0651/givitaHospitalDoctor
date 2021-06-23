package com.example.vitameanshospitaldoctor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.vitameanshospitaldoctor.DBdoctor
import com.example.vitameanshospitaldoctor.ManageBloodVMFactory
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.databinding.FragmentManageBloodPSBinding


class ManageBloodPSFrag : Fragment() {

    lateinit var database: DBdoctor
    lateinit var viewModel: ManageBloodPSVM
    lateinit var binding: FragmentManageBloodPSBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_manage_blood_p_s,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = DBdoctor.getInstance(requireContext())!!
        viewModel = ViewModelProvider(this,ManageBloodVMFactory(database)).get(ManageBloodPSVM::class.java)

        setObserver()
    }

    private fun setObserver() {
        viewModel.doctor.observe(viewLifecycleOwner,{
            val doctor = it.last()
            binding.appbar.welcomeTv.text = "${doctor.hospital} ${doctor.doctorname} 선생님 안녕하세요"
        })
    }


}
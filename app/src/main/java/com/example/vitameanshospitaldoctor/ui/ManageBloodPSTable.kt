package com.example.vitameanshospitaldoctor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.Patient
import com.example.vitameanshospitaldoctor.databinding.FragmentManageBloodPSTableBinding
import java.util.*


class ManageBloodPSTable(
//    private val pViewModel: ViewModel
) : Fragment() {

    lateinit var binding: FragmentManageBloodPSTableBinding
    lateinit var adapter: ManageBloodPSTableAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBloodPSTableBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setFakeData()
    }

    private fun setFakeData() {
        val c = Calendar.getInstance()
        val list = mutableListOf<Patient>()
        list.add(Patient(36784,"36wbdcxr",c,"김비당","남",64,"1형",130,70,c,98,c,c,c,"N"))
        list.add(Patient(36783,"tg2sdfsd",c,"박준규","남",62,"1형",130,102,c,80,c,c,c,"N"))
        list.add(Patient(36782,"asdf324d",c,"이익준","남",57,"2형",130,99,c,85,c,c,c,"N"))
        list.add(Patient(36781,"1213asd",c,"심하늘","남",39,"3형",130,104,c,78,c,c,c,"N"))
        list.add(Patient(36780,"zxcv3fa",c,"조윤진","여",51,"1형",141,101,c,76,c,c,c,"Y"))
        list.add(Patient(36779,"343dfsdfe",c,"심성균","남",43,"2형",130,87,c,95,c,c,c,"Y"))
        list.add(Patient(36778,"gsdfg212",c,"박재상","남",49,"1형",130,88,c,80,c,c,c,"Y"))
        list.add(Patient(36777,"asdf321",c,"김남준","남",42,"2형",130,89,c,88,c,c,c,"Y"))
        list.add(Patient(36776,"1dasdzx2",c,"최다울","남",36,"1형",130,97,c,95,c,c,c,"Y"))
        list.add(Patient(36775,"dffs3adf",c,"진정환","남",29,"3형",130,99,c,85,c,c,c,"Y"))
        adapter.differ.submitList(list)
    }

    private fun setRecyclerView() {
        adapter = ManageBloodPSTableAdapter()
        binding.apply {
            manageRv.layoutManager = LinearLayoutManager(requireContext())
            manageRv.setHasFixedSize(true)
            manageRv.adapter = adapter
        }
    }

//    companion object {
//
//        @JvmStatic
//        fun newInstance(pViewModel: ViewModel) = ManageBloodPSTable(pViewModel)
//
//    }
}
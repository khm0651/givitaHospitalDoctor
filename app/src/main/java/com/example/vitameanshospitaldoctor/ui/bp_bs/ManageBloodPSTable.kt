package com.example.vitameanshospitaldoctor.ui.bp_bs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.data.Patient
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.example.vitameanshospitaldoctor.databinding.FragmentManageBloodPSTableBinding
import com.example.vitameanshospitaldoctor.dialog.EmrRegistrationDialog
import com.example.vitameanshospitaldoctor.dialog.FilterSearchDialog
import com.example.vitameanshospitaldoctor.dialog.NameSearchDialog
import com.example.vitameanshospitaldoctor.dialog.OtherRecommendDialog
import com.example.vitameanshospitaldoctor.dialog.PersonRecommendDialog
import com.example.vitameanshospitaldoctor.showSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ManageBloodPSTable(
//    private val pViewModel: ViewModel
) : Fragment() {

    lateinit var binding: FragmentManageBloodPSTableBinding
    lateinit var adapter: ManageBloodPSTableAdapter
    private val viewModel: ManageBloodPSTableVM by viewModels()
    private var filterSearchDialog = FilterSearchDialog()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentManageBloodPSTableBinding.inflate(inflater,container,false)
        binding.btnFilterSearch.setOnClickListener {
            filterSearchDialog.show(childFragmentManager, "FilterSearchDialog")
        }

        binding.btnNameSearch.setOnClickListener {
            NameSearchDialog().show(childFragmentManager,"NameSearchDialog")
        }
        binding.btnRecommend.setOnClickListener {
            OtherRecommendDialog().show(childFragmentManager,"OtherRecommendDialog")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterSearchDialog.setOnClickedListener(object : FilterSearchDialog.btnClickListener{
            override fun onClicked(userData: List<UserData>?) {
                adapter.differ.submitList(userData)
                adapter.notifyDataSetChanged()
            }
        })
        setRecyclerView()
        setObserver()
        setListener()
//        setFakeData()
    }

    private fun setListener() {
        binding.apply {
            btnAllCheck.setOnClickListener {
                togleAllCheck()
            }

            btnEmrRegister.setOnClickListener {
                var count = 0
                for(item in adapter.differ.currentList){
                    if(item.isChecked && item.canRegisterEMR) {
                        count++
                        item.canRegisterEMR = false
                    }
                }
                EmrRegistrationDialog(count).show(childFragmentManager,"NameSearchDialog")
                adapter.notifyDataSetChanged()
            }

            btnSendSelectPatient.setOnClickListener {
                var count = 0
                for(item in adapter.differ.currentList){
                    if(item.isChecked) {
                        count++
                    }
                }
                it.showSnackbar("${count}????????? ??????????????? ???????????????",Snackbar.LENGTH_SHORT)
            }

//            btnRecommend.setOnClickListener {
//                var count = 0
//                for(item in adapter.differ.currentList){
//                    if(item.isChecked) {
//                        count++
//                    }
//                }
//                it.showSnackbar("${count}????????? ??????/?????? ????????? ???????????????",Snackbar.LENGTH_SHORT)
//            }
        }

    }

    private fun togleAllCheck(){
        if(isAllCheck()){
            for(item in adapter.differ.currentList){
                item.isChecked = false
            }
        }else{
            for(item in adapter.differ.currentList){
                item.isChecked = true
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun isAllCheck(): Boolean {
        for(item in adapter.differ.currentList){
            if(!item.isChecked) return false
        }
        return true
    }

    private fun setObserver(){
        viewModel.userList.observe(viewLifecycleOwner,{
            adapter.differ.submitList(it)
        })
    }


    private fun setFakeData() {

        val c = Calendar.getInstance()


        val list = mutableListOf<Patient>()
       /* list.add(Patient(36784,"36wbdcxr",c,"?????????","???",64,"1???",130,70,c,98,c,c,c,"N"))
        list.add(Patient(36783,"tg2sdfsd",c,"?????????","???",62,"1???",130,102,c,80,c,c,c,"N"))
        list.add(Patient(36782,"asdf324d",c,"?????????","???",57,"2???",130,99,c,85,c,c,c,"N"))
        list.add(Patient(36781,"1213asd",c,"?????????","???",39,"3???",130,104,c,78,c,c,c,"N"))
        list.add(Patient(36780,"zxcv3fa",c,"?????????","???",51,"1???",141,101,c,76,c,c,c,"Y"))
        list.add(Patient(36779,"343dfsdfe",c,"?????????","???",43,"2???",130,87,c,95,c,c,c,"Y"))
        list.add(Patient(36778,"gsdfg212",c,"?????????","???",49,"1???",130,88,c,80,c,c,c,"Y"))
        list.add(Patient(36777,"asdf321",c,"?????????","???",42,"2???",130,89,c,88,c,c,c,"Y"))
        list.add(Patient(36776,"1dasdzx2",c,"?????????","???",36,"1???",130,97,c,95,c,c,c,"Y"))
        list.add(Patient(36775,"dffs3adf",c,"?????????","???",29,"3???",130,99,c,85,c,c,c,"Y"))*/
    }

    private fun setRecyclerView() {
        adapter = ManageBloodPSTableAdapter(childFragmentManager){ findNavController().navigate(R.id.action_manageBloodPSTable_to_detailInfoFrag)}
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
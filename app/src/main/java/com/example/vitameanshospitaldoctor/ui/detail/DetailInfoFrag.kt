package com.example.vitameanshospitaldoctor.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitameanshospitaldoctor.data.*
import com.example.vitameanshospitaldoctor.databinding.FragmentDetailInfoBinding


class DetailInfoFrag : Fragment() {

    lateinit var binding: FragmentDetailInfoBinding
    lateinit var adapter: DetailInfoAdapter
    private lateinit var callback: OnBackPressedCallback
    private val viewmodel: DetailInfoVM by viewModels()


    private var bothList = mutableListOf<DetailInfo>()
    private var bpList = mutableListOf<DetailInfo>()
    private var bsList = mutableListOf<DetailInfo>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setFakeData()
        setObserver()
        setListener()
    }

    private fun setListener(){
        binding.apply {
            bloodPressureCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                viewmodel.setBpCheck(isChecked)
            }

            bloodSugarCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                viewmodel.setBsCheck(isChecked)
            }
        }
    }

    private fun setObserver() {
        viewmodel.bpCheck.observe(viewLifecycleOwner,{
            viewmodel.isBoth()
        })

        viewmodel.bsCheck.observe(viewLifecycleOwner,{
            viewmodel.isBoth()
        })

        viewmodel.isBoth.observe(viewLifecycleOwner,{
            when(it){
                DetailType.BP -> adapter.differ.submitList(bpList)
                DetailType.BS -> adapter.differ.submitList(bsList)
                DetailType.Both -> adapter.differ.submitList(bothList)
                DetailType.Nothing -> adapter.differ.submitList(mutableListOf())
            }
        })
    }

    private fun setFakeData() {
        bothList.add(DetailInfo("SAT","21.06.19",
            DetailBloodPressure(
                BloodPressure(127,72),
                BloodPressure(125,75),
                BloodPressure(null,null),
                BloodPressure(131,82),
                BloodPressure(135,79),
                BloodPressure(null,null),
                BloodPressure(131,82),
                BloodPressure(127,72)
            ),
            DetailBloodSugar(
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(172),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(143)
            ),
            DetailInfo.SHOW_BOTH
        ))
        bothList.add(DetailInfo("FRI","21.06.18",
            DetailBloodPressure(
                BloodPressure(null,null),
                BloodPressure(127,72),
                BloodPressure(138,84),
                BloodPressure(127,72),
                BloodPressure(138,84),
                BloodPressure(131,82),
                BloodPressure(135,79),
                BloodPressure(125,75)
            ),
            DetailBloodSugar(
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(163),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(192),
                BloodSugar(null)
            ),
            DetailInfo.SHOW_BOTH
        ))
        bothList.add(DetailInfo("THU","21.06.17",
            DetailBloodPressure(
                BloodPressure(125,75),
                BloodPressure(131,82),
                BloodPressure(135,79),
                BloodPressure(127,72),
                BloodPressure(null,null),
                BloodPressure(125,75),
                BloodPressure(127,72),
                BloodPressure(131,82)
            ),
            DetailBloodSugar(
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null)
            ),
            DetailInfo.SHOW_BOTH
        ))
        bothList.add(DetailInfo("WED","21.06.16",
            DetailBloodPressure(
                BloodPressure(125,75),
                BloodPressure(125,75),
                BloodPressure(null,null),
                BloodPressure(131,82),
                BloodPressure(138,84),
                BloodPressure(127,72),
                BloodPressure(null,null),
                BloodPressure(127,72)
            ),
            DetailBloodSugar(
                BloodSugar(124),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null)
            ),
            DetailInfo.SHOW_BOTH
        ))


        bpList.add(DetailInfo("SAT","21.06.19",
            DetailBloodPressure(
                BloodPressure(127,72),
                BloodPressure(125,75),
                BloodPressure(null,null),
                BloodPressure(131,82),
                BloodPressure(135,79),
                BloodPressure(null,null),
                BloodPressure(131,82),
                BloodPressure(127,72)
            ),
            null,
            DetailInfo.SHOW_ONE
        ))
        bpList.add(DetailInfo("FRI","21.06.18",
            DetailBloodPressure(
                BloodPressure(null,null),
                BloodPressure(127,72),
                BloodPressure(138,84),
                BloodPressure(127,72),
                BloodPressure(138,84),
                BloodPressure(131,82),
                BloodPressure(135,79),
                BloodPressure(125,75)
            ),
            null,
            DetailInfo.SHOW_ONE
        ))
        bpList.add(DetailInfo("THU","21.06.17",
            DetailBloodPressure(
                BloodPressure(125,75),
                BloodPressure(131,82),
                BloodPressure(135,79),
                BloodPressure(127,72),
                BloodPressure(null,null),
                BloodPressure(125,75),
                BloodPressure(127,72),
                BloodPressure(131,82)
            ),
            null,
            DetailInfo.SHOW_ONE
        ))
        bpList.add(DetailInfo("WED","21.06.16",
            DetailBloodPressure(
                BloodPressure(125,75),
                BloodPressure(125,75),
                BloodPressure(null,null),
                BloodPressure(131,82),
                BloodPressure(138,84),
                BloodPressure(127,72),
                BloodPressure(null,null),
                BloodPressure(127,72)
            ),
            null,
            DetailInfo.SHOW_ONE
        ))

        bsList.add(DetailInfo("SAT","21.06.19",
            null,
            DetailBloodSugar(
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(172),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(143)
            ),
            DetailInfo.SHOW_ONE
        ))
        bsList.add(DetailInfo("FRI","21.06.18",
           null,
            DetailBloodSugar(
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(163),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(192),
                BloodSugar(null)
            ),
            DetailInfo.SHOW_ONE
        ))
        bsList.add(DetailInfo("THU","21.06.17",
            null,
            DetailBloodSugar(
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null)
            ),
            DetailInfo.SHOW_ONE
        ))
        bsList.add(DetailInfo("WED","21.06.16",
            null,
            DetailBloodSugar(
                BloodSugar(124),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null),
                BloodSugar(null)
            ),
            DetailInfo.SHOW_ONE
        ))

        adapter.differ.submitList(bothList)
    }

    private fun setRecyclerView() {
        adapter = DetailInfoAdapter()
        binding.apply {
            detailRv.layoutManager = LinearLayoutManager(requireContext())
            detailRv.adapter = adapter
            detailRv.setHasFixedSize(true)
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

}
package com.example.vitameanshospitaldoctor.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.databinding.FragmentDetailInfoBinding


class DetailInfoFrag : Fragment() {

    lateinit var binding: FragmentDetailInfoBinding
    lateinit var adapter: DetailInfoAdapter
    private lateinit var callback: OnBackPressedCallback

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
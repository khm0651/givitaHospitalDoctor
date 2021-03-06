package com.example.vitameanshospitaldoctor.dialog


import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitameanshospitaldoctor.databinding.NameSearchDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NameSearchDialog : DialogFragment() {

    private var _binding: NameSearchDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NameSearchDialogVM by viewModels()
    lateinit var adapter: NameSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NameSearchDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.ivCancel.setOnClickListener {
            dismiss()
        }

        binding.tvResult.alpha = 0.0f
        binding.linearHead.alpha = 0.0f
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.uName.value = binding.etName.text.toString()
                binding.tvResult.alpha = 1f
                binding.linearHead.alpha = 1f
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        setObserver()
        setRecyclerView()

        return view
    }

    override fun onResume() {
        super.onResume()
        val windowManager =
            requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = (size.x * 0.9).toInt()
        params?.height = (size.y * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun setObserver() {
        viewModel.result.observe(viewLifecycleOwner, {
            adapter.differ.submitList(it)
            binding.tvResult.text = "???????????? ${it.size} ???"


        })
    }

    private fun setRecyclerView() {
        adapter = NameSearchAdapter()
        binding.apply {
            rvNameSearch.adapter = adapter
            rvNameSearch.layoutManager = LinearLayoutManager(context)
        }
    }
}
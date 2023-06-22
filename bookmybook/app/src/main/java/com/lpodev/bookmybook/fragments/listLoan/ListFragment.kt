package com.lpodev.bookmybook.fragments.listLoan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lpodev.bookmybook.databinding.FragmentListBinding
import androidx.navigation.fragment.findNavController
import com.lpodev.bookmybook.R

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.flotingBtnAddLoan.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_addLoanFragment)
        }

        return binding.root
    }
}
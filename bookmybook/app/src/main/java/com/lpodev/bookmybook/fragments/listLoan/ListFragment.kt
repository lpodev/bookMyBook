package com.lpodev.bookmybook.fragments.listLoan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lpodev.bookmybook.databinding.FragmentListBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.fragments.library.BookListAdapter
import com.lpodev.bookmybook.viewmodels.LoanViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val loanViewModel: LoanViewModel by viewModels()
    private val recyclerViewAdapter: LoanListAdapter by lazy { LoanListAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.flotingBtnAddLoan.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_addLoanFragment)
        }

        binding.loansRecyclerView.adapter = recyclerViewAdapter
        binding.loansRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        loanViewModel.loans.observe(viewLifecycleOwner) { loans ->
            recyclerViewAdapter.setData(loans)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Fetch the updated loans when the fragment resumes
        loanViewModel.fetchLoans()
    }
}
    package com.lpodev.bookmybook.fragments.library

    import android.os.Bundle
    import android.os.Handler
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.SearchView
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.viewModels
    import androidx.navigation.fragment.findNavController
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.lpodev.bookmybook.R
    import com.lpodev.bookmybook.viewmodels.BookViewModel
    import com.lpodev.bookmybook.databinding.FragmentLibraryBinding

    class LibraryFragment : Fragment(), SearchView.OnQueryTextListener {
        private var _binding: FragmentLibraryBinding? = null
        private val binding get() = _binding!!
        private val mBookViewModel: BookViewModel by viewModels()
        private val recyclerViewAdapter: BookListAdapter by lazy { BookListAdapter()}
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentLibraryBinding.inflate(inflater, container, false)

            binding.floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_library_to_addBookFragment)
            }

            binding.searchBar.setOnQueryTextListener(this)

            binding.booksRecyclerView.adapter = recyclerViewAdapter
            binding.booksRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            mBookViewModel.books.observe(viewLifecycleOwner) { book ->
                recyclerViewAdapter.setData(book)
            }

            return binding.root
        }

        override fun onResume() {
            super.onResume()

            val searchQuery = LibraryFragmentArgs.fromBundle(requireArguments()).searchQuery
            if (!searchQuery.isNullOrEmpty()) {
                binding.searchBar.clearFocus()

                Handler().postDelayed({
                    searchBookInDatabase(searchQuery)
                }, 200)

                // Update the query in the SearchView (optional)
                binding.searchBar.setQuery(searchQuery, false)
            }
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            if (!query.isNullOrEmpty()) {
                searchBookInDatabase(query)
            } else {
                // Query is empty, retrieve all books from the database
                mBookViewModel.getAllBooks()
            }
            return true
        }

        override fun onQueryTextChange(query: String?): Boolean {
            if (!query.isNullOrEmpty()) {
                searchBookInDatabase(query)
            } else {
                // Query is empty, retrieve all books from the database
                mBookViewModel.getAllBooks()
            }
            return true
        }

        private fun searchBookInDatabase(query: String?) {
            val searchQuery = "%$query%"
            mBookViewModel.searchBook(searchQuery)
        }
    }
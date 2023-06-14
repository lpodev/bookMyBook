package com.lpodev.bookmybook

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.viewmodels.BookViewModel
import java.lang.Exception

class DetailFragment : Fragment() {
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var mBookViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        view.findViewById<ImageView>(R.id.cover).load(args.currentBook.cover)
        view.findViewById<TextView>(R.id.title).text = args.currentBook.title
        view.findViewById<TextView>(R.id.author).text = args.currentBook.author
        view.findViewById<TextView>(R.id.isbn).text = args.currentBook.isbn

        view.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            deleteBook()
        }

        return view
    }

    private fun deleteBook() {
        print("coucou")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Supprimer ${args.currentBook.title} ?")
        builder.setMessage("Voulez-vous vraiment supprimer ${args.currentBook.title} ?")

        builder.setNegativeButton("Non") { _, _ -> }
        builder.setPositiveButton("Oui") { _, _ ->
            try {
                mBookViewModel.deleteBook(args.currentBook)
                Toast.makeText(requireContext(), "Livre supprimé avec succès", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }catch (e: Exception){
                Toast.makeText(requireContext(), "Échec de la suppression du livre", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

        }
        builder.create().show()
    }
}
package com.lpodev.bookmybook.fragments.addLoan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.models.Book

class BookSelectorAdapter(val context: Context, var dataSource: List<Book>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var selectedItemPosition: Int = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val viewHolder: ItemHolder

        if (view == null) {
            view = inflater.inflate(R.layout.book_selector_row, parent, false)
            viewHolder = ItemHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ItemHolder
        }

        val model = getItem(position) as Book

        viewHolder.title.text = model.title;
        viewHolder.author.text = model.author;

        return view!!
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val title: TextView
        val author: TextView
        init {
            title = row?.findViewById(R.id.title) as TextView
            author = row.findViewById(R.id.author) as TextView
        }
    }

    init {
        this.dataSource = dataSource}

}
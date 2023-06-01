package com.lpodev.bookmybook

var bookList = mutableListOf<Book>()

class Book(
    var cover: Int,
    var title: String,
    var author : String,
    var description : String,
    var isbn : String,
    var id : Int? = bookList.size
)
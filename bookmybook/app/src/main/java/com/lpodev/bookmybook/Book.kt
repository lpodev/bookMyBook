package com.lpodev.bookmybook

var bookList = mutableListOf<Book>()
class Book(
    var cover : Int,
    var title : String,
    var author : String,
    var isbn : String,
    val id : Int? = bookList.size
) {
}
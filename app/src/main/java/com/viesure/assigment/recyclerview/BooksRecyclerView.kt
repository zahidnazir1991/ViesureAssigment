package com.viesure.assigment.recyclerview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.viesure.assigment.listitems.RecyclerViewSingleItem
import com.viesure.assigment.models.BookDetailsResponseItem

@Composable
fun BooksRecyclerView(data: List<BookDetailsResponseItem>, callback: (BookDetailsResponseItem) -> Unit){
    LazyColumn(content = {
        items(data){book->
            RecyclerViewSingleItem(book,callback)

        }
    })
}
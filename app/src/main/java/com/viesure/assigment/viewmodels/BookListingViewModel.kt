package com.viesure.assigment.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viesure.assigment.api.ApiResult
import com.viesure.assigment.models.BookDetailsResponseItem
import com.viesure.assigment.repository.ApiRepository
import com.viesure.assigment.util.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class BookListingViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {
    private val _data = MutableStateFlow<List<BookDetailsResponseItem>>(emptyList())
    val data: StateFlow<List<BookDetailsResponseItem>> = _data.asStateFlow()
    val isShowerror = mutableStateOf(true)
    val errorMessage = mutableStateOf("")
    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            AppState.showLoader.value = true
            showHideError(true,"Loadi..")
            val result = repository.getBooks()
            when (result) {
                is ApiResult.Success -> {
                    AppState.showLoader.value = false
                    showHideError(false,"")
                    withContext(Dispatchers.Main) {
                        result.data?.let {
                            CoroutineScope(Dispatchers.IO).launch {
                                result.data?.let {
                                    var list = sortBooks(it)
                                    _data.value = list
                                    repository.encryptAndSaveBooks(it)
                                }
                            }
                        }
                    }
                }

                is ApiResult.Error -> {
                    withContext(Dispatchers.Main) {
                        AppState.showLoader.value = false
                        fetchBooksLocalDatabase(result.message)
                    }
                }
            }

        }
    }


    fun fetchBooksLocalDatabase(message: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var result = repository.getDecryptedBooks()
                withContext(Dispatchers.Main) {
                    if (result.isNotEmpty()) {
                        showHideError(false, "")
                        _data.value = sortBooks(result)
                    } else {
                        showHideError(true, message ?: "SomeThing Went Wrong")
                    }

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showHideError(true, "SomeThing Went Wrong")
                }
            }
        }
    }

    fun showHideError(isShow: Boolean, message: String) {
        if (isShow) {
            isShowerror.value = true
            errorMessage.value = message
        } else {
            isShowerror.value = false
            errorMessage.value = ""
        }
    }

    fun sortBooks(books: List<BookDetailsResponseItem>): List<BookDetailsResponseItem> {
        try {
            val dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy")
            // Separate books with valid and invalid dates
            val (validBooks, invalidBooks) = books.partition { book ->
                try {
                    LocalDate.parse(book.release_date, dateFormatter)
                    true
                } catch (e: DateTimeParseException) {
                    false
                }
            }
            // Sort the valid books by date
            val sortedValidBooks = validBooks.sortedBy { book ->
                LocalDate.parse(book.release_date, dateFormatter)
            }
            // Combine sorted valid books with invalid books at the end
            return sortedValidBooks + invalidBooks
        } catch (e: Exception) {
            throw e
        }
    }
}
package com.viesure.assigment.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.viesure.assigment.util.ToastType

open class BaseViewModel : ViewModel() {
    val showLoader = mutableStateOf(false)
    val showToaststate = mutableStateOf(false)
    val showToastmessage = mutableStateOf("")
    val showToasttype = mutableStateOf(ToastType.FAIL)
}
package com.viesure.assigment.viewmodels

import com.viesure.assigment.models.TopBarActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : BaseViewModel() {

    private val buttonVisibility = MutableStateFlow(TopBarActions())
    val buttonVisibility_: StateFlow<TopBarActions> = buttonVisibility
    val updateButtonVisibility: (TopBarActions) -> Unit = { elementsVisibility ->
        buttonVisibility.value = elementsVisibility
    }


    fun TopBarSettings(
        isShowBackButton: Boolean,
        titleText: String,
    ) {
        val updatedTopBarActions = TopBarActions(
            imageBack = isShowBackButton,
            titleText = titleText,
        )
        updateButtonVisibility(updatedTopBarActions)
    }


}





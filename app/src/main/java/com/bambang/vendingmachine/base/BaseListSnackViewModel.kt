package com.bambang.vendingmachine.base

import ListSnackComponent
import ListSnackModule
import androidx.lifecycle.ViewModel
import com.bambang.vendingmachine.viewmodel.ListSnackViewModel

abstract class BaseListSnackViewModel : ViewModel() {

    private val component: ListSnackComponent = DaggerListSnackComponent
        .builder()
        .module(ListSnackModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ListSnackViewModel -> component.inject(this)
        }
    }
}
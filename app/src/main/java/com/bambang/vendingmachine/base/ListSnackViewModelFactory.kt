package com.bambang.vendingmachine.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bambang.vendingmachine.viewmodel.ListSnackViewModel

class ListSnackViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListSnackViewModel() as T
    }
}

package com.bambang.vendingmachine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bambang.vendingmachine.base.BaseListSnackViewModel
import com.bambang.vendingmachine.model.Snack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ListSnackViewModel : BaseListSnackViewModel(),
    CoroutineScope {
    private var job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    val textChangeSnack = MutableLiveData<Int>()
    val textPriceSnack = MutableLiveData<Int>()
    val textPaymentSnack = MutableLiveData<Int>()
    val textTotalSnack = MutableLiveData<Int>()
    val listSnack = MutableLiveData<ArrayList<Snack>>()


    fun setListSnackValue() {
        var listArray : ArrayList<Snack> = ArrayList()
        listArray.add(
            Snack(
                1,
                "Biskuit",
                "https://assets.tiptop.co.id/00464160103a.jpg",
                6000,
                10
            )
        )
        listArray.add(
            Snack(
                2,
                "Chips",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvDDGCS-E_Ptwlt0NCj4ZGw-GomUsVmXuq7g&usqp=CAU",
                8000,
                10
            ),
        )
        listArray.add(
            Snack(
                3,
                "Oreo",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTjA1ScRJchF-7ki2lf9AZw6Gizmrfi1D4ezw&usqp=CAU",
                10000,
                10
            ),
        )
        listArray.add(
            Snack(
                4,
                "Tango",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTH7qkQlZVe9XtZJM3L7I1QHMag2spv8_nciQ&usqp=CAU",
                12000,
                10
            ),
        )
        listArray.add(
            Snack(
                5,
                "Cokelat",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRl1Lm6tEKZ3pP9FZCbMAS97dNAYvrEV9ElZw&usqp=CAU",
                8000,
                10
            ),
        )

        listSnack.value = listArray

    }

    fun setCountTotalPay(){
        if (textPaymentSnack.value != null || textPaymentSnack.value != 0)
            textChangeSnack.value = textPaymentSnack.value!! - textPriceSnack.value!!
    }

    fun resetValue(){
        textChangeSnack.value = 0
        textPriceSnack.value = null
        textPaymentSnack.value = null
    }

}
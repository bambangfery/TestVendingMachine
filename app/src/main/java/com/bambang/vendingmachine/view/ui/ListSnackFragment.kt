package com.bambang.vendingmachine.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bambang.vendingmachine.R
import com.bambang.vendingmachine.databinding.ListSnackFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class ListSnackFragment : Fragment() {

    lateinit var binding: ListSnackFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.list_snack_fragment,
            container, false
        )
        binding.lifecycleOwner = this

        return binding.root
    }

}


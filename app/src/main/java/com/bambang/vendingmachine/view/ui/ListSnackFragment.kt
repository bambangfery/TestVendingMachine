package com.bambang.vendingmachine.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bambang.vendingmachine.R
import com.bambang.vendingmachine.base.ListSnackViewModelFactory
import com.bambang.vendingmachine.databinding.ListSnackFragmentBinding
import com.bambang.vendingmachine.model.Snack
import com.bambang.vendingmachine.view.ui.adapter.ListSnackAdapter
import com.bambang.vendingmachine.viewmodel.ListSnackViewModel

class ListSnackFragment : Fragment() {

    lateinit var binding: ListSnackFragmentBinding
    private lateinit var mAdapter: ListSnackAdapter
    private lateinit var lManager: GridLayoutManager
    private val viewModelFactory = ListSnackViewModelFactory()
    private val viewModel: ListSnackViewModel by activityViewModels(
        factoryProducer = { viewModelFactory })

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bindViewModel()
        bindingViewEvent()
    }

    private fun initView() {
        mAdapter = ListSnackAdapter(listener, requireActivity())
        lManager = GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
        if (viewModel.listSnack.value.isNullOrEmpty())
            viewModel.setListSnackValue()
    }


    private fun bindViewModel() {
        viewModel.listSnack.observe(viewLifecycleOwner, Observer {
            mAdapter.refresh(it)
        })

    }

    private fun bindingViewEvent() {
        binding.rvSnackList.apply {
            layoutManager = lManager
            adapter = mAdapter
        }

        binding.restock.setOnClickListener {
            viewModel.setListSnackValue()
        }
    }

    private val listener = object : ListSnackAdapter.SnackListener {
        override fun onClickedListener(item: Snack, position: Int) {
            findNavController().navigate(
                ListSnackFragmentDirections.actionListSnackFragmentToDetailSnackFragment(item)
            )
        }

    }
}


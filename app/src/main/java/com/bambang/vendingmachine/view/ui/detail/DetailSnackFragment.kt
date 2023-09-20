package com.bambang.vendingmachine.view.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bambang.vendingmachine.R
import com.bambang.vendingmachine.base.ListSnackViewModelFactory
import com.bambang.vendingmachine.databinding.DetailSnackFragmentBinding
import com.bambang.vendingmachine.viewmodel.ListSnackViewModel
import com.bumptech.glide.Glide

class DetailSnackFragment : Fragment() {

    lateinit var binding: DetailSnackFragmentBinding
    private val viewModelFactory = ListSnackViewModelFactory()
    private val viewModel: ListSnackViewModel by activityViewModels(
        factoryProducer = { viewModelFactory })
    private val args: DetailSnackFragmentArgs by navArgs()
    private var count: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_snack_fragment,
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

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.tvName.text = args.snackItem.titleSnack
        binding.tvPrice.text = "Rp."+args.snackItem.priceSnack.toString()
        Glide.with(this)
            .load(args.snackItem.imgSnack)
            .into(binding.ivSnack)
        viewModel.textTotalSnack.value = 1
    }


    private fun bindViewModel() {
        viewModel.textTotalSnack.observe(viewLifecycleOwner, Observer {
                binding.tvTotalValue.text = "x $it"
        })

    }

    private fun bindingViewEvent() {
        binding.btnPlus.setOnClickListener {
            if (count < args.snackItem.stockSnack!!)
                viewModel.textTotalSnack.value = ++count
        }
        binding.btnMin.setOnClickListener {
            if (count > 1)
                viewModel.textTotalSnack.value = --count
        }
        binding.btnBack.setOnClickListener {
            viewModel.textTotalSnack.value = 1
            count = 1
            findNavController().navigate(
                DetailSnackFragmentDirections.actionDetailSnackFragmentToListSnackFragment()
            )
        }
        binding.btnPay.setOnClickListener {
            findNavController().navigate(
                DetailSnackFragmentDirections.actionDetailSnackFragmentToPayDetailFragment(args.snackItem,
                    viewModel.textTotalSnack.value!!
                )
            )
        }
    }

}


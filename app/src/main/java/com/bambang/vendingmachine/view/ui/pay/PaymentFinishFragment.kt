package com.bambang.vendingmachine.view.ui.pay

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
import com.bambang.vendingmachine.databinding.PaymentFinishFragmentBinding
import com.bambang.vendingmachine.viewmodel.ListSnackViewModel

class PaymentFinishFragment : Fragment() {

    lateinit var binding: PaymentFinishFragmentBinding
    private val viewModelFactory = ListSnackViewModelFactory()
    private val viewModel: ListSnackViewModel by activityViewModels(
        factoryProducer = { viewModelFactory })
    private val args: PaymentFinishFragmentArgs by navArgs()

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
            inflater, R.layout.payment_finish_fragment,
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
        binding.tvTotal.text = args.snackItem.titleSnack+" x "+args.count
    }


    private fun bindViewModel() {
        viewModel.textPaymentSnack.observe(viewLifecycleOwner, Observer {
            binding.tvPayment.text = "Rp. $it"
        })

        viewModel.textPriceSnack.observe(viewLifecycleOwner, Observer {
            binding.tvTotalValue.text = "Rp. $it"
        })

        viewModel.textChangeSnack.observe(viewLifecycleOwner, Observer {
            binding.tvChangeSnack.text = "Rp. $it"
        })
    }

    private fun bindingViewEvent() {

        binding.btnBack.setOnClickListener {
            viewModel.listSnack.value!!.forEach {
                if (it.idSnack == args.snackItem.idSnack){
                    it.stockSnack = it.stockSnack!!-args.count
                }
            }
            findNavController().navigate(
               PaymentFinishFragmentDirections.actionPaymentFinishFragmentToListSnackFragment()
            )
            viewModel.textChangeSnack.value = 0
            viewModel.textPriceSnack.value = null
            viewModel.textTotalSnack.value = null
            viewModel.textPaymentSnack.value = null
            println(viewModel.listSnack.value)
        }
    }

}


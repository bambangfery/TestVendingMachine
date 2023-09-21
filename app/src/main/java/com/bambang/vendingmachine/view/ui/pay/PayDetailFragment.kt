package com.bambang.vendingmachine.view.ui.pay

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bambang.vendingmachine.R
import com.bambang.vendingmachine.base.ListSnackViewModelFactory
import com.bambang.vendingmachine.databinding.PayDetailFragmentBinding
import com.bambang.vendingmachine.viewmodel.ListSnackViewModel

class PayDetailFragment : Fragment() {

    lateinit var binding: PayDetailFragmentBinding
    private val viewModelFactory = ListSnackViewModelFactory()
    private val viewModel: ListSnackViewModel by activityViewModels(
        factoryProducer = { viewModelFactory })
    private val args: PayDetailFragmentArgs by navArgs()
    private var payment: Int = 0

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
            inflater, R.layout.pay_detail_fragment,
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
        viewModel.textPriceSnack.value = args.snackItem.priceSnack!!*args.count
//        viewModel.textPaymentSnack.value = 0
    }


    private fun bindViewModel() {
        viewModel.textPaymentSnack.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.tvPayment.text = "Rp. $it"
        })

        viewModel.textPriceSnack.observe(viewLifecycleOwner, Observer {
            binding.tvTotalValue.text = "Rp. $it"
        })

        viewModel.textChangeSnack.observe(viewLifecycleOwner, Observer {
            if (it < 0)
                Toast.makeText(context,"Kurang Bayar",Toast.LENGTH_SHORT).show()
            else if (viewModel.textPaymentSnack.value != null)
                findNavController().navigate(
                   PayDetailFragmentDirections.actionPayDetailFragmentToPaymentFinishFragment(args.snackItem,
                       viewModel.textTotalSnack.value!!)
                )
        })
    }

    private fun bindingViewEvent() {
        binding.btn2000.setOnClickListener {
            payment += 2000
            viewModel.textPaymentSnack.value = payment
        }
        binding.btn5000.setOnClickListener {
            payment += 5000
            viewModel.textPaymentSnack.value = payment
        }
        binding.btn10000.setOnClickListener {
            payment += 10000
            viewModel.textPaymentSnack.value = payment
        }
        binding.btn20000.setOnClickListener {
            payment += 20000
            viewModel.textPaymentSnack.value = payment
        }
        binding.btn50000.setOnClickListener {
            payment += 50000
            viewModel.textPaymentSnack.value = payment
        }
        binding.btnPay.setOnClickListener {
            if (viewModel.textPaymentSnack.value != 0 && viewModel.textPaymentSnack.value != null)
                viewModel.setCountTotalPay()
        }
    }

}


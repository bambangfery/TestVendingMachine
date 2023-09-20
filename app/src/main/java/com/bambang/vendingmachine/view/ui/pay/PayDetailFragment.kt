package com.bambang.vendingmachine.view.ui.pay

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bambang.vendingmachine.R
import com.bambang.vendingmachine.base.ListSnackViewModelFactory
import com.bambang.vendingmachine.databinding.DetailSnackFragmentBinding
import com.bambang.vendingmachine.databinding.ListSnackFragmentBinding
import com.bambang.vendingmachine.databinding.PayDetailFragmentBinding
import com.bambang.vendingmachine.model.Snack
import com.bambang.vendingmachine.view.ui.ListSnackFragmentDirections
import com.bambang.vendingmachine.view.ui.adapter.ListSnackAdapter
import com.bambang.vendingmachine.viewmodel.ListSnackViewModel
import com.bumptech.glide.Glide

class PayDetailFragment : Fragment() {

    lateinit var binding: PayDetailFragmentBinding
    private val viewModelFactory = ListSnackViewModelFactory()
    private val viewModel: ListSnackViewModel by activityViewModels(
        factoryProducer = { viewModelFactory })
    private val args: PayDetailFragmentArgs by navArgs()
    private var count: Int = 1

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
        binding.tvTotalValue.text = "Rp."+ args.snackItem.stockSnack.toString()
    }


    private fun bindViewModel() {
        viewModel.textTotalSnack.observe(viewLifecycleOwner, Observer {
                binding.tvTotalValue.text = "x $it"
        })

    }

    private fun bindingViewEvent() {
        binding.btnPay.setOnClickListener {

        }
    }

}


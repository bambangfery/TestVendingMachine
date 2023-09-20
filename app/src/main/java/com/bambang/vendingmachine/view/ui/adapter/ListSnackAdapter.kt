package com.bambang.vendingmachine.view.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bambang.vendingmachine.databinding.ItemListSnackBinding
import com.bambang.vendingmachine.model.Snack
import com.bumptech.glide.Glide

class ListSnackAdapter(val listener: SnackListener, val context: Context) :
    RecyclerView.Adapter<ListSnackAdapter.SnackDetail>() {

    var list = arrayListOf<Snack>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SnackDetail {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemListSnackBinding.inflate(layoutInflater, parent, false)
        return SnackDetail(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: SnackDetail, position: Int
    ) {
        val item = list[position]
        holder.apply {
            this.binding.tvName.text = "${item.titleSnack}"
            this.binding.tvPrice.text = "Rp. ${item.priceSnack}"
            this.binding.tvStock.text = "Stock : ${item.stockSnack}"

            Glide.with(itemView)
                .load(item.imgSnack)
                .into(binding.ivSnack)

            holder.binding.clContainer.setOnClickListener {
                if (item.stockSnack!! > 0)
                    listener.onClickedListener(item, position)
                else
                    Toast.makeText(context,"Stock Habis", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class SnackDetail(val binding: ItemListSnackBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface SnackListener {
        fun onClickedListener(item: Snack, position: Int)
    }

    fun refresh(itemList: ArrayList<Snack>) {
        list.clear()
        list.addAll(itemList)
        notifyDataSetChanged()
    }

}
package com.app.bin_info_viewer.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bin_info_viewer.R
import com.app.databinding.BinInfoLayoutBinding
import com.core.data.BinInfoEntity

class BinInfoRecyclerAdapter(
    private val context: Context
): RecyclerView.Adapter<BinInfoRecyclerAdapter.BinInfoHolder> () {

    class BinInfoHolder(val binding: BinInfoLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    var binInfoList: List<BinInfoEntity> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinInfoHolder {
        val binding = BinInfoLayoutBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return BinInfoHolder(binding)
    }

    override fun getItemCount(): Int {
        return binInfoList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BinInfoHolder, position: Int) {
        initBinLabels(holder.binding)

        val binInfo = binInfoList[position]

        holder.binding.bin.text = binInfo.bin
        holder.binding.cardNumberLayout.value.text = binInfo.numberLength
        holder.binding.typeLayout.value.text = binInfo.type
        holder.binding.brandLayout.value.text = binInfo.brand
        holder.binding.prepaidLayout.value.text = binInfo.prepaid
        holder.binding.schemeLayout.value.text = binInfo.scheme

        holder.binding.bankLayout.value1.text = "${binInfo.bankName}, ${binInfo.bankCity}"
        holder.binding.bankLayout.url.text = binInfo.bankUrl
        holder.binding.bankLayout.value2.text = binInfo.bankPhone

        holder.binding.countryLayout.value1.text = binInfo.countryName
        holder.binding.countryLayout.value2.text =
            "(latitude: ${binInfo.countryLatitude}, longitude: ${binInfo.countryLongitude})"

    }

    private fun initBinLabels(binding: BinInfoLayoutBinding) {
        binding.schemeLayout.label.text = context.getString(com.app.R.string.scheme)
        binding.brandLayout.label.text = context.getString(com.app.R.string.brand)

        binding.cardNumberLayout.label1.text = context.getString(com.app.R.string.card_number_l1)
        binding.cardNumberLayout.label2.text = context.getString(com.app.R.string.card_number_l2)

        binding.typeLayout.label.text = context.getString(com.app.R.string.type)
        binding.prepaidLayout.label.text = context.getString(com.app.R.string.prepaid)
        binding.countryLayout.label.text = context.getString(com.app.R.string.country)
        binding.bankLayout.label.text = context.getString(com.app.R.string.bank)
    }
}
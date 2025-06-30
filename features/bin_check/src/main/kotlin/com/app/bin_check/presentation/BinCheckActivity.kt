package com.app.bin_check.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.bin_check.R
import com.app.bin_check.databinding.ActivityBinCheckBinding
import com.app.bin_check.data.BinData
import com.app.bin_check.data.BinResponseState
import com.app.bin_check.di.BinCheckDepsProvider
import com.app.bin_check.utils.BinCheckRouter
import com.core.utils.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinCheckActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    private lateinit var binding: ActivityBinCheckBinding
    private val viewModel: BinCheckViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBinCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initDI()

        clearBinValues()
        initBinLabels()
        setBinCheckListener()

        binding.checkButton.setOnClickListener(this::lookupButtonHandler)
        binding.historyButton.setOnClickListener(this::startHistoryListActivity)
    }

    private fun initDI() {
        val loginComponent =
            (applicationContext as BinCheckDepsProvider).getBinCheckComponent()
        loginComponent.inject(this)
        loginComponent.inject(viewModel)
    }

    private fun initBinLabels() {
        binding.binInfo.schemeLayout.label.text = getString(com.app.R.string.scheme)
        binding.binInfo.brandLayout.label.text = getString(com.app.R.string.brand)

        binding.binInfo.cardNumberLayout.label1.text = getString(com.app.R.string.card_number_l1)
        binding.binInfo.cardNumberLayout.label2.text = getString(com.app.R.string.card_number_l2)

        binding.binInfo.typeLayout.label.text = getString(com.app.R.string.type)
        binding.binInfo.prepaidLayout.label.text = getString(com.app.R.string.prepaid)
        binding.binInfo.countryLayout.label.text = getString(com.app.R.string.country)
        binding.binInfo.bankLayout.label.text = getString(com.app.R.string.bank)
    }

    @SuppressLint("SetTextI18n")
    private fun setBinValues(bin: BinData) {
        binding.binInfo.schemeLayout.value.text = bin.scheme
        binding.binInfo.brandLayout.value.text = bin.brand
        binding.binInfo.cardNumberLayout.value.text = bin.cardLength
        binding.binInfo.typeLayout.value.text = bin.type
        binding.binInfo.prepaidLayout.value.text = bin.prepaid

        binding.binInfo.countryLayout.value1.text = bin.country.name
        binding.binInfo.countryLayout.value2.text =
            "(latitude: ${bin.country.lat}, longitude: ${bin.country.long})"

        binding.binInfo.bankLayout.value1.text = bin.bank.name
        binding.binInfo.bankLayout.url.text = bin.bank.url
        binding.binInfo.bankLayout.value2.text = bin.bank.phone
    }

    private fun clearBinValues() {
        binding.binInfo.schemeLayout.value.text = ""
        binding.binInfo.brandLayout.value.text = ""
        binding.binInfo.cardNumberLayout.value.text = ""
        binding.binInfo.typeLayout.value.text = ""
        binding.binInfo.prepaidLayout.value.text = ""

        binding.binInfo.countryLayout.value1.text = ""
        binding.binInfo.countryLayout.value2.text = ""

        binding.binInfo.bankLayout.value1.text = ""
        binding.binInfo.bankLayout.url.text = ""
        binding.binInfo.bankLayout.value2.text = ""
    }

    private fun lookupButtonHandler(view: View?) {
        val bin = binding.inputText.text.toString()
        if (bin.isEmpty()) {
            Toast.makeText(baseContext, "BIN is invalid.", Toast.LENGTH_LONG).show()
            return
        }

        clearBinValues()
        viewModel.getBinInfo(bin)
    }

    private fun startHistoryListActivity(view: View?) {
        (router as BinCheckRouter).moveToBinHistory(this@BinCheckActivity)
    }

    private fun setBinCheckListener() {
        lifecycleScope.launch {
            viewModel.binData.collect { state ->
                when(state) {
                    is BinResponseState.AcceptState ->
                        setBinValues(viewModel.mapResponse(state.binData))

                    is BinResponseState.ExceptionState ->
                        Toast.makeText(this@BinCheckActivity, state.message, Toast.LENGTH_LONG)
                            .show()
                }
            }
        }
    }
}
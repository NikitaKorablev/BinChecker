package com.app.bin_info_viewer.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.bin_info_viewer.R
import com.app.bin_info_viewer.databinding.ActivityBinInfoListBinding
import com.app.bin_info_viewer.di.BinInfoViewerDepsProvider
import com.core.data.BinInfoEntity
import kotlinx.coroutines.launch

class BinInfoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBinInfoListBinding
    private lateinit var recyclerAdapter: BinInfoRecyclerAdapter
    private val viewModel: BinInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBinInfoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initDI()
        initRecyclerView()

        setBinHistoryListener()
        viewModel.updateBinHistory()
    }

    private fun initDI() {
        val loginComponent =
            (applicationContext as BinInfoViewerDepsProvider).getBinInfoComponent()
        loginComponent.inject(this)
        loginComponent.inject(viewModel)
    }

    private fun initRecyclerView() {
        recyclerAdapter = BinInfoRecyclerAdapter(this@BinInfoListActivity)
        binding.recyclerView.adapter = recyclerAdapter
    }

    private fun addBinInfoToList(binInfo: BinInfoEntity) {
        val binInfoList = recyclerAdapter.binInfoList.toMutableList()
        binInfoList.add(binInfo)

        recyclerAdapter.binInfoList = binInfoList
    }


    private fun setBinHistoryListener() {
        lifecycleScope.launch {
            viewModel.binHistory.collect { history ->
                recyclerAdapter.binInfoList = history
            }
        }
    }
}
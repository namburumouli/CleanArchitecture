package com.example.cleanarchitecturesample.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecturesample.R
import com.example.cleanarchitecturesample.presentation.fit_glucosedata.FitGlucoseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<FitGlucoseViewModel>()
    val data = ArrayList<String>()

//     val viewModel : FitGlucoseViewModel by

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //array adapter
        var arrayAdapter: ArrayAdapter<String>



        viewModel.getData()
//       Log.i("Data", )Data
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest {
//                Log.i("MainActivity", it.glucoseData[0].name)
                when (it) {
                    is FitGlucoseViewModel.DataState.Empty ->{
                        Log.i("Main", "Empty")
                    }
                    is FitGlucoseViewModel.DataState.Success -> {
                        Log.i("Main", it.listState.glucoseData[0].name)
                        it.listState.glucoseData.forEach {
                            data.addAll(listOf(it.name))
                        }
                        val mListView = findViewById<ListView>(R.id.userlist)
                        arrayAdapter = ArrayAdapter(
                            this@MainActivity,
                            android.R.layout.simple_list_item_1, data
                        )
                        mListView.adapter = arrayAdapter
                    }
                    is FitGlucoseViewModel.DataState.Error -> {
                        Log.i("Main", "Error")
                    }
                }
            }
        }



    }
}





package com.junior.cinephile.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.junior.cinephile.databinding.ActivityMainBinding
import com.test.movies.presentation.RecyclerViewScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<VM>()
    private val adapterMovie = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.loadData()
        getData()
        initViews()
        initListeners()
    }

    private fun getData() {
        vm.liveData.observe(this) {
            when(it.loading){
                true ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                false ->{
                    binding.progressBar.visibility = View.GONE
                    if (it.listData.isNotEmpty()) {
                        adapterMovie.items = it.listData.last().results
                    }
                }
            }
        }
    }

    private fun initViews() {
        binding.run {
            rvMovie.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rvMovie.adapter = adapterMovie
        }
    }

    private fun initListeners() {
        binding.run {
            rvMovie.addOnScrollListener(object : RecyclerViewScrollListener() {
                override fun onScrollToBottom() {
                    vm.loadData()
                }
            })
        }
    }
}
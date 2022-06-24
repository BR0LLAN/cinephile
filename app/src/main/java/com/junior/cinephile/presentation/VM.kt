package com.junior.cinephile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.cinephile.domain.useСase.GetDataMovies
import com.junior.cinephile.entities.ResultState
import com.junior.cinephile.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VM @Inject constructor(
    private val getData: GetDataMovies
) : ViewModel() {

    private val _liveData: MutableLiveData<State> = MutableLiveData(State())
    val liveData: LiveData<State> = _liveData
    private var job: Job? = null

    fun loadData() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            getData(_liveData.value!!.offset).collect {
                when (it) {
                    is ResultState.Success -> {
                        _liveData.value!!.listData.add(it.data)
                        _liveData.postValue(
                            _liveData.value!!.copy(
                                offset = _liveData.value!!.offset + 20,
                                listData = _liveData.value!!.listData,
                                loading = false
                            )
                        )
                    }
                    is ResultState.Error -> {
                        _liveData.postValue(
                            _liveData.value!!.copy(
                                loading = false
                            )
                        )
                    }
                    is ResultState.Loading -> {
                        _liveData.postValue(
                            _liveData.value!!.copy(
                                loading = true
                            )
                        )
                    }
                }
            }
        }
    }
}
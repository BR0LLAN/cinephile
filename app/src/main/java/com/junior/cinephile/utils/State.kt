package com.junior.cinephile.utils

import com.junior.cinephile.entities.Response

data class State(
    val loading: Boolean = false,
    val offset: Int = 0,
    var listData: ArrayList<Response> = arrayListOf()
)
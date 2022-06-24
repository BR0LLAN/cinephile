package com.test.movies.presentation

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

open class RecyclerViewScrollListener : RecyclerView.OnScrollListener(), BottomListener {
    // Положение последних нескольких полностью видимых элементов (как в случае с каскадными макетами)

    private lateinit var lastCompletelyVisiblePositions: IntArray
    // Положение последнего полностью видимого элемента
    private var lastCompletelyVisibleItemPosition: Int? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager
        // находим положение последнего полностью видимого элемента
        when (layoutManager) {
            is StaggeredGridLayoutManager -> {
                layoutManager.findLastCompletelyVisibleItemPositions(lastCompletelyVisiblePositions)
                lastCompletelyVisibleItemPosition = getMaxPosition(lastCompletelyVisiblePositions)
            }
            is GridLayoutManager -> {
                lastCompletelyVisibleItemPosition =
                    layoutManager.findLastCompletelyVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastCompletelyVisibleItemPosition =
                    layoutManager.findLastCompletelyVisibleItemPosition()
            }
            else -> {
                throw RuntimeException("Unsupported LayoutManager.")
            }
        }
    }

    private fun getMaxPosition(positions: IntArray): Int {
        var max = positions[0]
        for (i in 1 until positions.size) {
            if (positions[i] > max) {
                max = positions[i]
            }
        }
        return max
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        val layoutManager = recyclerView.layoutManager
        // Определяем, нужно ли скользить вниз, сравнивая положение последнего полностью видимого элемента и общее количество элементов
        val visibleItemCount = layoutManager!!.childCount
        val totalItemCount = layoutManager.itemCount
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition!! >= totalItemCount - 1) {
                onScrollToBottom()
            }
        }
    }

    override fun onScrollToBottom() {

    }
}
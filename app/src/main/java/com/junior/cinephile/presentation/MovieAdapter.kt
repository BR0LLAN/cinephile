package com.junior.cinephile.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junior.cinephile.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.junior.cinephile.entities.Results

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.VH>() {

    var items: ArrayList<Results> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.addAll(value)
            notifyItemRangeInserted(field.count(), value.count()) }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))}

    override fun onBindViewHolder(holder: VH, position: Int) { holder.bind(items[position]) }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.titleFilm)
        private val imgFilm: ImageView = itemView.findViewById(R.id.imgFilm)
        private val desc: TextView = itemView.findViewById(R.id.descriptionFilm)

        fun bind(it: Results) {
            itemView.run {
                title.text = it.displayTitle
                desc.text = it.summaryShort
            }

            Glide.with(itemView.context)
                .load(it.multimedia?.src)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgFilm)
        }

    }
}

package com.standardListAndDetailApp.screens.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.ListAdapterBinding
import com.standardListAndDetailApp.repository.database.DatabaseHome

class ListAdapter(private val itemClickListener: (DatabaseHome)->Unit): RecyclerView.Adapter<ListViewHolder>() {

    private var homes = mutableListOf<DatabaseHome>()

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeList(movies: List<DatabaseHome>) {
        this.homes = movies.toMutableList()
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            ListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemClickListener
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.apply {
            bind(homes[position])
        }

    }

    override fun getItemCount(): Int {
        return homes.size
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbs: ImageView, url: String?) {
            if(url==null)
                return
            Glide.with(thumbs)
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thumbs)
        }
    }
}
class ListViewHolder(
    private val binding: ListAdapterBinding,
    val itemClickListener: (DatabaseHome) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(home: DatabaseHome) {
        binding.p = home
        binding.root.setOnClickListener { itemClickListener(home) }
    }
}
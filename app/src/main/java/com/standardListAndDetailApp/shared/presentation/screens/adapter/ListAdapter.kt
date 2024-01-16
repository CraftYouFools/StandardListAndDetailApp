package com.standardListAndDetailApp.shared.presentation.screens.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.ListViewHolderBinding
import com.example.standardlistanddetailapplicationcontent.databinding.WishListViewHolderBinding
import com.standardListAndDetailApp.home_detail.presentation.view_holder.ListViewHolder
import com.standardListAndDetailApp.home_list.presentation.HomeListCardViewState
import com.standardListAndDetailApp.shared.presentation.screens.adapter.ListAdapter.Companion.loadImage
import com.standardListAndDetailApp.whish_list.presentation.view_holder.WishListViewHolder
import com.standardListAndDetailApp.shared.presentation.state.ListState
import com.standardListAndDetailApp.whish_list.presentation.HomeWishListCardViewState
import java.lang.Exception

class ListAdapter(private val itemClickListener: (ListState)->Unit, private val addToWishListListener : ((ListState)->Unit)?): RecyclerView.Adapter<WiewHolderMain>() {

    private var homes = mutableListOf<ListState>()

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeList(state: List<ListState>) {
        this.homes = state.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(homes[position] ) {
            is HomeListCardViewState -> 0
            is HomeWishListCardViewState -> 1
            else -> 0
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WiewHolderMain {

        when (viewType) {
            0 -> return ListViewHolder(
                ListViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                itemClickListener,
                addToWishListListener
            )

            1 -> return WishListViewHolder(
                WishListViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                itemClickListener,
            )

            else -> return ListViewHolder(
                ListViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                itemClickListener,
                addToWishListListener
            )
        }

    }


    override fun onBindViewHolder(holder: WiewHolderMain, position: Int) {
        holder.apply {
            when(holder) {
                is ListViewHolder -> {
                    this as ListViewHolder
                    Log.d(TAG, "is a ListViewHolder")
                    with(homes[position]){
                        if(this is HomeListCardViewState) {
                            bind(this)
                        }
                    }
                }
                is WishListViewHolder -> {
                    this as WishListViewHolder
                    Log.d(TAG, "is a WishListViewHolder")

                    with(homes[position]){
                        if(this is HomeWishListCardViewState) {
                            bind(this)
                        }
                    }
                }
                else -> {
                    throw Exception(" Wrong holder format")
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return homes.size
    }

    companion object {
        @JvmStatic
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

        private const val TAG = "ListAdapter"
    }
}


abstract class WiewHolderMain(binding : ViewBinding) : RecyclerView.ViewHolder(binding.root)

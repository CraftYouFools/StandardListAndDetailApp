package com.standardListAndDetailApp.whish_list.presentation.view_holder

import androidx.core.content.ContextCompat
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.WishListViewHolderBinding
import com.standardListAndDetailApp.shared.presentation.screens.adapter.ListAdapter
import com.standardListAndDetailApp.shared.presentation.screens.adapter.WiewHolderMain
import com.standardListAndDetailApp.whish_list.presentation.HomeWishListCardViewState

class WishListViewHolder(
    private val binding: WishListViewHolderBinding,
    val itemClickListener: (HomeWishListCardViewState) -> Unit,
) : WiewHolderMain(binding)  {
    fun bind(homeWishListCardViewState: HomeWishListCardViewState) {

        binding.homeType.text = homeWishListCardViewState.homeType
        if(homeWishListCardViewState.url==null) {
            binding.ivItem.setImageResource(R.drawable.icons_home_100)
        }
        else{
            ListAdapter.loadImage(binding.ivItem, homeWishListCardViewState.url)
        }
        binding.location.text=homeWishListCardViewState.city
        binding.surfaceArea.text=""+homeWishListCardViewState.area+" "+binding.root.context.getString(
            R.string.surface_unit)
        binding.price.text=""+homeWishListCardViewState.price+""+binding.root.context.getString(R.string.local_currency)

        binding.root.setOnClickListener { itemClickListener(homeWishListCardViewState) }

        binding.ivWhishList.setImageDrawable(
            ContextCompat.getDrawable(
                this.binding.root.context,
                R.drawable.ic_favorite_enabled
            )
        )


    }
}
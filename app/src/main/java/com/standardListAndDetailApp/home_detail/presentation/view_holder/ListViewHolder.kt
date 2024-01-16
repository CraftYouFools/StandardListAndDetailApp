package com.standardListAndDetailApp.home_detail.presentation.view_holder

import androidx.core.content.ContextCompat
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.ListViewHolderBinding
import com.standardListAndDetailApp.home_list.presentation.HomeListCardViewState
import com.standardListAndDetailApp.shared.presentation.screens.adapter.ListAdapter
import com.standardListAndDetailApp.shared.presentation.screens.adapter.WiewHolderMain

class ListViewHolder(
    private val binding: ListViewHolderBinding,
    val itemClickListener: (HomeListCardViewState) -> Unit,
    private val addToWishListListener: ((HomeListCardViewState) -> Unit)?
) : WiewHolderMain(binding) {
    fun bind(homeListCardViewState: HomeListCardViewState) {
        binding.homeType.text = homeListCardViewState.homeType
        if(homeListCardViewState.url==null) {
            binding.ivItem.setImageResource(R.drawable.icons_home_100)
        }
        else{
            ListAdapter.loadImage(binding.ivItem, homeListCardViewState.url)
        }
        binding.location.text=homeListCardViewState.city
        binding.surfaceArea.text=""+homeListCardViewState.area+" "+binding.root.context.getString(R.string.surface_unit)
        binding.price.text=""+homeListCardViewState.price+""+binding.root.context.getString(R.string.local_currency)

        binding.root.setOnClickListener { itemClickListener(homeListCardViewState) }
        binding.ivWhishList.setOnClickListener {
            addToWishListListener?.let {
                it(
                    homeListCardViewState
                )
            }
        }
        when (homeListCardViewState.isInWishList) {
            true -> binding.ivWhishList.setImageDrawable(
                ContextCompat.getDrawable(
                    this.binding.root.context,
                    R.drawable.ic_favorite_enabled
                )
            )

            else -> binding.ivWhishList.setImageDrawable(
                ContextCompat.getDrawable(
                    this.binding.root.context,
                    R.drawable.ic_favorite_disabled
                )
            )
        }

    }
}
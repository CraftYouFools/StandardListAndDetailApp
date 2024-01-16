package com.standardListAndDetailApp.home_detail.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.FragmentDetailBinding
import com.standardListAndDetailApp.home_detail.presentation.HomeDetailViewState
import com.standardListAndDetailApp.home_detail.presentation.viewmodel.DetailViewModel
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
import com.standardListAndDetailApp.shared.presentation.screens.adapter.ListAdapter
import com.standardListAndDetailApp.shared.presentation.screens.fragment.BaseFragment
import com.standardListAndDetailApp.shared.presentation.screens.viewmodel.ViewModelFactory
import javax.inject.Inject


private const val HOME_DETAIL_ID = "home_detail_id"
class DetailFragment : BaseFragment() {

    private var homeId: Int? = null

    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var repository: HomesRepositoryImpl

    @Inject
    lateinit var myViewModelFactory: ViewModelFactory

    @Inject
    @JvmField
    var supportActionBar: ActionBar?=null

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, myViewModelFactory)[DetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)

        arguments?.let {
            homeId = it.getInt(HOME_DETAIL_ID)
        }

        homeId?.let {
            viewModel.getHome(it)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        updateListener()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { homeDetailViewState ->
            when (homeDetailViewState) {
                is HomeDetailViewState.Loading -> {
                    binding.pbDetails.visibility = View.VISIBLE
                    binding.linearLayoutDetails.visibility = GONE
                    binding.errorTextView.visibility = GONE
                }
                is HomeDetailViewState.Error -> {
                    binding.pbDetails.visibility = GONE
                    binding.linearLayoutDetails.visibility = GONE
                    binding.errorTextView.visibility = View.VISIBLE

                }
                is HomeDetailViewState.Content -> {
                    binding.pbDetails.visibility = GONE
                    binding.linearLayoutDetails.visibility = View.VISIBLE
                    binding.errorTextView.visibility = GONE
                    setupScreenInfos(homeDetailViewState)
                    supportActionBar?.title=homeDetailViewState.home.city
                }
            }
        }


    }

    private fun setupScreenInfos(homeDetailViewState : HomeDetailViewState.Content) {
        val home = homeDetailViewState.home

        if(home.homeType==null) binding.tvType.visibility=GONE else binding.tvType.text = home.homeType

        if(home.url==null) {
            binding.ivHouse.setImageResource(R.drawable.icons_home_100)
        }
        else{
            ListAdapter.loadImage(binding.ivHouse, home.url)
        }

        if(home.city==null) binding.location.visibility=GONE else binding.location.text=home.city
        if(home.area==null) binding.tvSurfaceArea.visibility=GONE else binding.tvSurfaceArea.text=""+home.area+" "+binding.root.context.getString(R.string.surface_unit)
        if(home.price==null) binding.tvPrice.visibility=GONE else binding.tvPrice.text=""+home.price+""+binding.root.context.getString(R.string.local_currency)
        if(home.rooms==null) binding.tvRoomNumber.visibility=GONE else binding.tvRoomNumber.text=home.rooms.toString()
        if(home.professional==null) binding.tvProfessional.visibility=GONE else binding.tvProfessional.text=home.professional

        when (homeDetailViewState.home.isInWishList) {
            true -> {
                context?.let { ctx ->
                    binding.ivWhishList.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.ic_favorite_enabled
                        )
                    )
                }
            }
            else -> {
                context?.let { ctx ->
                    binding.ivWhishList.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.ic_favorite_disabled
                        )
                    )
                }
            }
        }
    }

    private fun updateListener() {
        binding.ivWhishList.setOnClickListener {
            homeId?.let {
                id ->
                viewModel.onFavoriteIconClicked(id)
                Log.d(TAG, "Home id : $homeId clicked")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(homeId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(HOME_DETAIL_ID, homeId)
                }
            }

        const val FRAGMENT_LIST_NAME = "DetailFragment"

        private const val TAG = "DetailFragment"

    }
}
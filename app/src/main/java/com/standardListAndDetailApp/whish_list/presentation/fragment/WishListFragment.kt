package com.standardListAndDetailApp.whish_list.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.FragmentWishListBinding
import com.google.android.material.snackbar.Snackbar
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
import com.standardListAndDetailApp.shared.presentation.screens.adapter.ListAdapter
import com.standardListAndDetailApp.shared.presentation.screens.fragment.BaseFragment
import com.standardListAndDetailApp.shared.presentation.screens.viewmodel.ViewModelFactory
import com.standardListAndDetailApp.shared.presentation.state.ListState
import com.standardListAndDetailApp.whish_list.presentation.HomeWishListCardViewState
import com.standardListAndDetailApp.whish_list.presentation.HomeWishListViewState
import com.standardListAndDetailApp.whish_list.presentation.viewmodel.WishListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WishListFragment : BaseFragment() {

    @Inject
    lateinit var navigator : INavigator

    @Inject
    lateinit var repository: HomesRepositoryImpl

    @Inject
    lateinit var myViewModelFactory: ViewModelFactory

    @Inject
    @JvmField
    var supportActionBar: ActionBar?=null

    private lateinit var binding: FragmentWishListBinding

    private val viewModel: WishListViewModel by lazy {
        ViewModelProvider(this, myViewModelFactory)[WishListViewModel::class.java]
    }

    private lateinit var adapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            state.apply {
                when(this){
                    is HomeWishListViewState.Loading -> {
                        binding.tvError.visibility = View.GONE
                        binding.pbWishList.visibility=View.VISIBLE
                    }
                    is HomeWishListViewState.Content -> {
                        Log.d(TAG,"Content")
                        CoroutineScope(Dispatchers.Main).launch {
                            this@apply.homes.collect{
                                    list ->
                                Log.d(TAG,"datasize : "+list.size)
                                adapter.setHomeList(list)
                            }
                        }
                        binding.tvError.visibility = View.GONE
                        binding.pbWishList.visibility=View.GONE
                    }
                    is HomeWishListViewState.Error -> {
                        binding.pbWishList.visibility=View.GONE
                        binding.tvError.visibility = View.VISIBLE

                    }
                    is HomeWishListViewState.NetWorkError -> {
                        binding.tvError.visibility = View.GONE
                        binding.pbWishList.visibility=View.GONE
                        onNetworkError()
                    }
                }

            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=getString(R.string.wishlist)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishListBinding.inflate(inflater, container, false)

        adapter = ListAdapter(itemClickListener,null)

        binding.rvList.adapter = adapter
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=adapter
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    private val itemClickListener  = { homeListDetails: ListState -> if(homeListDetails is HomeWishListCardViewState){
        navigator.navigateToHomeDetail(homeListDetails.id )
    } }

    private fun onNetworkError() {
        Snackbar.make(binding.root, "Network Error", Snackbar.LENGTH_SHORT)
            .setAction("CLOSE") { }
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light,resources.newTheme()))
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WishListFragment()

        private const val TAG = "WishListFragment"
    }
}
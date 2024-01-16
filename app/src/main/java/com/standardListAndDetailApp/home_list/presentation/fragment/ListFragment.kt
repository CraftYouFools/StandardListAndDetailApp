package com.standardListAndDetailApp.home_list.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar
import com.standardListAndDetailApp.home_list.presentation.HomeListCardViewState
import com.standardListAndDetailApp.home_list.presentation.HomeListViewState
import com.standardListAndDetailApp.home_list.presentation.viewmodel.ListViewModel
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
import com.standardListAndDetailApp.shared.presentation.screens.adapter.ListAdapter
import com.standardListAndDetailApp.shared.presentation.screens.fragment.BaseFragment
import com.standardListAndDetailApp.shared.presentation.screens.viewmodel.ViewModelFactory
import com.standardListAndDetailApp.shared.presentation.state.ListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : BaseFragment() {

    @Inject
    lateinit var navigator : INavigator

    @Inject
    lateinit var repository: HomesRepositoryImpl

    @Inject
    lateinit var myViewModelFactory: ViewModelFactory

    @Inject
    @JvmField
    var supportActionBar: ActionBar?=null

    private lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this, myViewModelFactory)[ListViewModel::class.java]
    }

    private lateinit var adapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        injector.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.open_wishlist -> {
                navigator.navigateToWishList()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.srlList.setOnRefreshListener {
            viewModel.onRefresh()
        }
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            state.apply {
                when(this){
                    is HomeListViewState.Loading -> {
                        binding.tvError.visibility = View.GONE
                        binding.srlList.isRefreshing=true
                    }
                    is HomeListViewState.Content -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            this@apply.homes.collect{
                                    list -> adapter.setHomeList(list)
                            }
                        }
                        binding.tvError.visibility = View.GONE
                        binding.srlList.isRefreshing=false
                    }
                    is HomeListViewState.Error -> {
                        binding.srlList.isRefreshing=false
                        binding.tvError.visibility = View.VISIBLE

                    }
                    is HomeListViewState.NetWorkError -> {
                        binding.tvError.visibility = View.GONE
                        binding.srlList.isRefreshing=false
                        onNetworkError()
                    }
                }

            }
        }

        supportActionBar?.title= getString(R.string.real_estate_app)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        adapter = ListAdapter(itemClickListener,addToWishListListener)

        binding.rvList.adapter = adapter
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=adapter
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    private val itemClickListener = { homeListDetails: ListState ->
        if (homeListDetails is HomeListCardViewState) {
            navigator.navigateToHomeDetail(homeListDetails.id)
        }
    }
    private val addToWishListListener: (homeListDetails: ListState) -> Unit = { homeListCardViewState ->
        if (homeListCardViewState is HomeListCardViewState) {
            onFavoriteIconClicked(homeListCardViewState.id)
        }
    }

    private fun onFavoriteIconClicked(homeId : Int) {
        viewModel.onFavoriteIconClicked(homeId)
    }

    private fun onNetworkError() {
        Snackbar.make(binding.root, "Network Error", Snackbar.LENGTH_SHORT)
            .setAction("CLOSE") { }
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light,resources.newTheme()))
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()

        private const val TAG = "ListFragment"
    }
}
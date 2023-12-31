package com.standardListAndDetailApp.home_list.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standardlistanddetailapplicationcontent.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
import com.standardListAndDetailApp.home_list.presentation.adapter.ListAdapter
import com.standardListAndDetailApp.shared.presentation.screens.fragment.BaseFragment
import com.standardListAndDetailApp.home_list.presentation.viewmodel.ListViewModel
import com.standardListAndDetailApp.shared.presentation.screens.viewmodel.ViewModelFactory
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
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.homeList.observe(viewLifecycleOwner) { homes ->
            homes.apply {
                adapter.setHomeList(this)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        adapter = ListAdapter {
            navigator.navigateToHomeDetail(it)
        }

        binding.rvList.adapter = adapter
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=adapter
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    private fun onNetworkError() {
        if(viewModel.isNetworkErrorShown.value==false) {
            val parentLayout: View = binding.root
            Snackbar.make(parentLayout, "Network Error", Snackbar.LENGTH_SHORT)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                .show()
            viewModel.onNetworkErrorShown()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}
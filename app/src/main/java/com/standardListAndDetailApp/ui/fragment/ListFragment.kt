package com.standardListAndDetailApp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer.ListListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standardlistanddetailapplicationcontent.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.navigation.Navigator
import com.standardListAndDetailApp.repository.HomesRepository
import com.standardListAndDetailApp.ui.adapter.ListAdapter
import com.standardListAndDetailApp.viewmodel.ListViewModel
import com.standardListAndDetailApp.viewmodel.ViewModelFactory
import javax.inject.Inject

class ListFragment : BaseFragment() {

    @Inject
    lateinit var navigator : INavigator

    @Inject
    lateinit var repository: HomesRepository

    @Inject
    lateinit var myViewModelFactory: ViewModelFactory

    private lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
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
package com.standardListAndDetailApp.home_detail.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.example.standardlistanddetailapplicationcontent.databinding.FragmentDetailBinding
import com.standardListAndDetailApp.home_detail.presentation.HomeDetailViewState
import com.standardListAndDetailApp.home_detail.presentation.viewmodel.DetailViewModel
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
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

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { homeDetailViewState ->
            when (homeDetailViewState) {
                is HomeDetailViewState.Loading -> {
                    binding.pbDetails.visibility = View.VISIBLE
                    binding.linearLayoutDetails.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                }

                is HomeDetailViewState.Error -> {
                    binding.pbDetails.visibility = View.GONE
                    binding.linearLayoutDetails.visibility = View.GONE
                    binding.errorTextView.visibility = View.VISIBLE

                }

                is HomeDetailViewState.Content -> {
                    binding.p = homeDetailViewState.home
                    binding.pbDetails.visibility = View.GONE
                    binding.linearLayoutDetails.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.GONE

                }
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

    }
}
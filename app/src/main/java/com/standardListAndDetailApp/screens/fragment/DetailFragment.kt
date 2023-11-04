package com.standardListAndDetailApp.screens.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.standardlistanddetailapplicationcontent.databinding.FragmentDetailBinding
import com.standardListAndDetailApp.repository.HomesRepository
import com.standardListAndDetailApp.screens.viewmodel.DetailViewModel
import com.standardListAndDetailApp.screens.viewmodel.ViewModelFactory
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val HOME_DETAIL_ID = "home_detail_id"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : BaseFragment() {

    private var homeId: Int? = null

    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var repository: HomesRepository

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
        viewModel.home.observe(viewLifecycleOwner) { home ->
            binding.p = home
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
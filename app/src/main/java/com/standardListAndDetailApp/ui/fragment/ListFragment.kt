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
import com.standardListAndDetailApp.ui.adapter.ListAdapter
import com.standardListAndDetailApp.viewmodel.ListViewModel
import com.standardListAndDetailApp.viewmodel.ViewModelFactory
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    @Inject
    lateinit var navigator : INavigator

    private lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, ViewModelFactory(activity.application))[ListViewModel::class.java]
    }

    private lateinit var adapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
            Toast.makeText(context,"id : "+it.id+" url : "+it.url,Toast.LENGTH_SHORT).show()
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

            //Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
            ListFragment().apply {
              /*  arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                } */
            }
    }
}
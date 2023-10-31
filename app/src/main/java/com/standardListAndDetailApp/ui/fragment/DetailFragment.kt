package com.standardListAndDetailApp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.database.DatabaseHome


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val HOME_DETAIL_ID = "home_detail_id"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var homeId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            homeId = it.getInt(HOME_DETAIL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
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
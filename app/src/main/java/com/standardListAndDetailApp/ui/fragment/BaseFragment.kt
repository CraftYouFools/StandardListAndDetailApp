package com.standardListAndDetailApp.ui.fragment

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.di.presentation.PresentationModule
import com.standardListAndDetailApp.ui.activity.BaseActivity

@BindingAdapter("app:setImageDrawableP")
fun setImageDrawableP(view: ImageView, add: Boolean) {
    if (add) {
        view.setImageResource(R.drawable.icons_home_100)
    }
}
open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector get() = presentationComponent

}
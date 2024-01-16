package com.standardListAndDetailApp.shared.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.standardlistanddetailapplicationcontent.R
import com.example.standardlistanddetailapplicationcontent.databinding.ActivityMainBinding
import com.standardListAndDetailApp.navigation.INavigator
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var navigator: INavigator

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbar.title= getString(R.string.real_estate_app)

        setSupportActionBar(toolbar)

        navigator.attachHomeList()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> { onBackPressed()}
        }
        return super.onOptionsItemSelected(item)
    }
}
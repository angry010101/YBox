package com.yakymovych.simon.everywhere.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.ui.BaseActivity
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import com.yakymovych.simon.everywhere.ui.main.recyclerview.TasksAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.yakymovych.simon.everywhere.data.Sort
import android.view.View

import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProviders


class MainActivity : BaseActivity() {
    lateinit var viewModel: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }
}

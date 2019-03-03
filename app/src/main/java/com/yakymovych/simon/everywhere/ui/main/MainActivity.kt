package com.yakymovych.simon.everywhere.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.ui.BaseActivity
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import com.yakymovych.simon.everywhere.ui.main.recyclerview.TasksAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*
import javax.inject.Inject
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.yakymovych.simon.everywhere.data.Sort
import android.view.View

import androidx.appcompat.widget.PopupMenu


class MainActivity : BaseActivity(),PopupMenu.OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        item?.setChecked(true)
        when (item?.itemId) {
            R.id.action_sort_dueby -> {
                viewModel.selectSort(Sort.DUEBY)
                return true
            }
            R.id.action_sort_priority -> {
                viewModel.selectSort(Sort.PRIORITY)
                return true
            }
            R.id.action_sort_title -> {
                viewModel.selectSort(Sort.TITLE)
                return true
            }
            R.id.action_sort_id -> {
                viewModel.selectSort(Sort.ID)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @Inject
    lateinit var viewModel: MainActivityViewModel



    fun openMenu(v: View){
        val popup = PopupMenu(this, v)
        val inflater = popup.getMenuInflater()
        popup.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.menu_main, popup.getMenu())
        popup.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TasksAdapter {
            Timber.d { "SOMETHING PRESSED" }
            //launchNoteDetailActivity(this@NoteListActivity, it.noteId)
        }
        viewModel.tasks.observe(this, Observer {
            it?.let {
                Timber.d { "SOMETHING CHANGED" }
                swipeContainer.isRefreshing = false
                adapter.submitList(it)
            }
        })
        viewModel.isResfreshing.observe(this, Observer {
            it?.let {
                swipeContainer.setRefreshing(it)
            }
        })
        swipeContainer.setOnRefreshListener(viewModel.refreshListener)

        tasks_recycler_view.layoutManager =
                LinearLayoutManager(this).apply {
            orientation = RecyclerView.VERTICAL
        }
        tasks_recycler_view.adapter = adapter
    }
}

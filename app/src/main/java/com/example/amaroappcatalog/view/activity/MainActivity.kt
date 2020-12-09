package com.example.amaroappcatalog.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.amaroappcatalog.R
import com.example.amaroappcatalog.view.adapter.CatalogListAdapter
import com.example.amaroappcatalog.viewModel.CatalogViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: CatalogViewModel by lazy {
        ViewModelProviders.of(this).get(CatalogViewModel::class.java)
    }

    lateinit var catalogListAdapter: CatalogListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        subscribeUI()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)

        catalogListAdapter = CatalogListAdapter()

        catalogList.adapter = catalogListAdapter
    }

    private fun subscribeUI() {
        viewModel.response.observe(this, Observer {
            catalogListAdapter.updateList(it.products)
        })
    }
}
package com.example.mad_lab_2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mad_lab_2_2.viewModel.RickAndMortyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var charactersData: Results
    private lateinit var adapter: CharacterAdapter
    lateinit var characterViewModel: RickAndMortyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.r_view)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        characterViewModel = ViewModelProvider(this).get(RickAndMortyViewModel::class.java)

        CoroutineScope(Dispatchers.Default).launch{
            characterViewModel.retrofitAPI()
        }

        characterViewModel.charactersData.observe(this, Observer {
            charactersData = it
            adapter = CharacterAdapter(this, it)
            recyclerView.adapter = adapter
        })
    }
}
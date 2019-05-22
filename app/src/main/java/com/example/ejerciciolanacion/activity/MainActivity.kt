package com.example.ejerciciolanacion.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.ejerciciolanacion.R
import com.example.ejerciciolanacion.adapter.AlbumAdapter
import com.example.ejerciciolanacion.model.Album
import com.example.ejerciciolanacion.view_model.AlbumViewModel
import com.example.ejerciciolanacion.view_model.AlbumViewModelFactory

class MainActivity : AppCompatActivity() {

    var adapter: AlbumAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = this.findViewById(R.id.rvAlbums)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getViewModel()
    }


    //Get View Model and update UI
    private fun getViewModel() {
        val myViewModel = ViewModelProviders.of(this,
            AlbumViewModelFactory(this.application, "")
        ).get(AlbumViewModel::class.java)

        // Create the observer which updates the UI.
        val albumObserver = Observer<List<Album>> { list ->
            // Update the UI, in this case, a adapter.
            adapter = AlbumAdapter(this@MainActivity, albumList = list!!)
            recyclerView.adapter = adapter
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myViewModel.getAlbums.observe(this, albumObserver)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

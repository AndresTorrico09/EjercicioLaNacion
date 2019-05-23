package com.example.ejerciciolanacion.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.ejerciciolanacion.R
import com.example.ejerciciolanacion.adapter.AlbumAdapter
import com.example.ejerciciolanacion.adapter.PhotoAdapter
import com.example.ejerciciolanacion.model.Album
import com.example.ejerciciolanacion.model.Photo
import com.example.ejerciciolanacion.view_model.AlbumViewModel
import com.example.ejerciciolanacion.view_model.AlbumViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var adapter: PhotoAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        recyclerView = this.findViewById(R.id.rvPhotos)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,3)

        val idAlbum = intent.getStringExtra("idAlbum")
        getViewModel(idAlbum)
    }

    private fun getViewModel(idAlbum: String) {
        val myViewModel = ViewModelProviders.of(this,
            AlbumViewModelFactory(this.application, idAlbum)
        ).get(AlbumViewModel::class.java)

        // Create the observer which updates the UI.
        val photoObserver = Observer<List<Photo>> { list ->
            // Update the UI, in this case, a adapter.
            adapter = PhotoAdapter(this@DetailActivity, photoList = list!!)
            recyclerView.adapter = adapter
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myViewModel.getAlbumById.observe(this, photoObserver)
    }
}

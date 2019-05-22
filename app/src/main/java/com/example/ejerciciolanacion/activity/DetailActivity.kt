package com.example.ejerciciolanacion.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ejerciciolanacion.R
import com.example.ejerciciolanacion.model.Album
import com.example.ejerciciolanacion.view_model.AlbumViewModel
import com.example.ejerciciolanacion.view_model.AlbumViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val idAlbum = intent.getStringExtra("idAlbum")

        // Get the ViewModel.
        val myViewModel = ViewModelProviders.of(this,
            AlbumViewModelFactory(this.application, idAlbum)
        ).get(AlbumViewModel::class.java)

        // Create the observer which updates the UI.
        val albumByIdObserver = Observer<Album> { album ->
            Picasso.get().load(album!!.url).resize(200, 200).centerCrop().into(imgFoto)
            tvTitle.text = album.title
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myViewModel.getAlbumById.observe(this, albumByIdObserver)
    }
}

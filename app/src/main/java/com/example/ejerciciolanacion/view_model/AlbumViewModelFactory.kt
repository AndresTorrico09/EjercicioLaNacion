package com.example.ejerciciolanacion.view_model

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class AlbumViewModelFactory (private val mApplication: Application, private val mParam: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(mApplication, mParam) as T
    }
}
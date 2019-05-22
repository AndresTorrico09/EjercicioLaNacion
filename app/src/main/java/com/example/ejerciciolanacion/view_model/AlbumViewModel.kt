package com.example.ejerciciolanacion.view_model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.ejerciciolanacion.`interface`.Api
import com.example.ejerciciolanacion.model.Album
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import retrofit2.Callback

class AlbumViewModel (mApplication: Application, mParam: String) : ViewModel() {
    //this is the data that we will fetch asynchronously
    var albumList: MutableLiveData<List<Album>>? = null
    var album: MutableLiveData<Album>? = null
    private val id: Any = mParam

    //we will call this method to get the data
    //if the list is null
    //we will load it asynchronously from server in this method
    //finally we will return the list
    val getAlbums: LiveData<List<Album>>
        get() {
            if (albumList == null) {
                albumList = MutableLiveData()
                loadAlbums()
            }
            return albumList as MutableLiveData<List<Album>>
        }

    val getAlbumById: LiveData<Album>
        get() {
            if (album == null) {
                album = MutableLiveData()
                loadAlbumById(id as String)
            }
            return album as MutableLiveData<Album>
        }

    private fun loadAlbums() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)
        val call = api.albums

        call.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                Collections.sort(response.body()) {
                        object1, object2 -> object1.title!!.compareTo(object2.title!!) }

                //finally we are setting the list to our MutableLiveData
                albumList!!.value = response.body()
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
            }
        })
    }

    private fun loadAlbumById(id: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val Api = retrofit.create(Api::class.java)
        val call = Api.albumById(id)

        call.enqueue(object : Callback<Album> {
            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                album!!.value = response.body()
            }
            override fun onFailure(call: Call<Album>, t: Throwable) {
            }
        })
    }
}
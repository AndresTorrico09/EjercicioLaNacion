package com.example.ejerciciolanacion.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ejerciciolanacion.R
import com.example.ejerciciolanacion.activity.DetailActivity
import com.example.ejerciciolanacion.model.Album
import com.squareup.picasso.Picasso

class AlbumAdapter(private var mCtx: Context, internal var albumList: List<Album>)
    : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.cv_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList[position]

        Picasso.get()
            .load(album.thumb)
            .resize(100, 100)
            .centerCrop()
            .into(holder.imgThumb)

        holder.tvTitle.text = album.title

        holder.itemView.setOnClickListener {
            val intent = Intent(mCtx, DetailActivity::class.java)
            intent.putExtra("idAlbum", album.id)
            mCtx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgThumb: ImageView = itemView.findViewById(R.id.imgThumb)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    }
}
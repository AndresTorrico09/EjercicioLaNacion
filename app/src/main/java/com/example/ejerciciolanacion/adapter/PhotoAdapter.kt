package com.example.ejerciciolanacion.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ejerciciolanacion.R
import com.example.ejerciciolanacion.model.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter(private var mCtx: Context, internal var photoList: List<Photo>)
    : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.cv_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photoList[position]

        Picasso.get()
            .load(photo.thumbnailUrl)
            .resize(100, 100)
            .centerCrop()
            .into(holder.imgThumb)

        holder.tvTitle.text = photo.title

/*        holder.itemView.setOnClickListener {
            val intent = Intent(mCtx, DetailActivity::class.java)
            intent.putExtra("idAlbum", photo.id)
            mCtx.startActivity(intent)
        }*/
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgThumb: ImageView = itemView.findViewById(R.id.thumbnailUrl)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    }
}
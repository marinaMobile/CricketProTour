package com.mobirix.d

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobirix.d.data.model.Data
import com.mobirix.d.data.model.Teams

class RecAdapter (private val context: Context, private val articles: List<Data>): RecyclerView.Adapter<RecAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        var image: ImageView = itemView.findViewById(R.id.imageView)
        var newTitle: TextView = itemView.findViewById(R.id.title_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val team: Data = articles[position]
        holder.newTitle.text=team.name
        Glide.with(context)
            .load(team.image_path)
//            .placeholder(R.drawable.sport)
//            .error(R.drawable.sport)
//            .fallback(R.drawable.sport)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
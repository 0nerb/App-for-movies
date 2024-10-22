package com.example.ingressocom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Locale

class FilmeAdapter(private val filmesList: List<JSONObject>) :
    RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.filmeImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.filmeTitleTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.filmeDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = filmesList[position]

        val title = filme.optString("title")
        holder.titleTextView.text = title

        val premiereDate = filme.optJSONObject("premiereDate")
        val formattedDate = if (premiereDate != null) {
            val localDate = premiereDate.optString("localDate")
            // Converter o formato da data (23-10-2024)
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date = inputFormat.parse(localDate)
            outputFormat.format(date)
        } else {
            "Sem data"
        }
        holder.dateTextView.text = formattedDate

        // Exibir a imagem com Glide
        val imagesArray = filme.optJSONArray("images")
        val imageUrl = if (imagesArray != null && imagesArray.length() > 0) {
            imagesArray.getJSONObject(0).optString("url")
        } else {
            null
        }

        if (imageUrl != null) {
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.ic_sem_imagem_foreground) // Placeholder padrão
        }
    }

    override fun getItemCount(): Int {
        return filmesList.size
    }
}

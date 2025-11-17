package com.example.ingressocom.ui.filmes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ingressocom.FilmeAdapter
import com.example.ingressocom.R


class FilmesFragment : Fragment() {

    private val filmesViewModel: FilmesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_filmes, container, false)


        val recyclerView = rootView.findViewById<RecyclerView>(R.id.lista_filmes)
        val gridLayoutManager = GridLayoutManager(context, 3) // 3 colunas por linha
        recyclerView.layoutManager = gridLayoutManager

        filmesViewModel.getFilmes().observe(viewLifecycleOwner, Observer { filmesList ->

            for (filme in filmesList) {
                val imagesArray = filme.optJSONArray("images")
                val imageUrl = if (imagesArray != null && imagesArray.length() > 0) {
                    imagesArray.getJSONObject(0).optString("url")
                } else {
                    "No Image"
                }

            }

            // Configurar o adaptador com a lista de filmes
            val adapter = FilmeAdapter(filmesList)
            recyclerView.adapter = adapter
        })

        return rootView
    }
}

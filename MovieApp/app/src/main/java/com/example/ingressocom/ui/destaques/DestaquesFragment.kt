package com.example.ingressocom.ui.destaques

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ingressocom.FilmeAdapter
import com.example.ingressocom.R


class DestaquesFragment : Fragment() {

    private val destaquesViewModel: DestaquesViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_destaques, container, false)

        
        recyclerView = rootView.findViewById(R.id.lista_destaques)
        progressBar = rootView.findViewById(R.id.progressBar)
        textView = rootView.findViewById(R.id.titulo_filmes)

        progressBar.visibility = View.VISIBLE
        textView.visibility = View.GONE

        val gridLayoutManager = GridLayoutManager(context, 3) // 3 colunas por linha
        recyclerView.layoutManager = gridLayoutManager

        destaquesViewModel.getDestaques().observe(viewLifecycleOwner, Observer { destaquesList ->

            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE

            val adapter = FilmeAdapter(destaquesList)
            recyclerView.adapter = adapter
        })


        return rootView
    }
}

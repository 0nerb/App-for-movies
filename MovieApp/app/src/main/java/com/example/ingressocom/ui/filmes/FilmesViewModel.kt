package com.example.ingressocom.ui.filmes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject

class FilmesViewModel : ViewModel() {

    private val destaques = MutableLiveData<List<JSONObject>>()

    fun setFilmes(destaquesList: List<JSONObject>) {
        destaques.value = destaquesList
    }

    fun getFilmes(): LiveData<List<JSONObject>> {
        return destaques
    }
}
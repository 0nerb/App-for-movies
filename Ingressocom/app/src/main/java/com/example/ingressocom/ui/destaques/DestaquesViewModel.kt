package com.example.ingressocom.ui.destaques

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject

class DestaquesViewModel : ViewModel() {

    private val destaques = MutableLiveData<List<JSONObject>>()

    fun setDestaques(destaquesList: List<JSONObject>) {
        destaques.value = destaquesList
    }

    fun getDestaques(): LiveData<List<JSONObject>> {
        return destaques
    }
}
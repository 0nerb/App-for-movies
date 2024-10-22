package com.example.ingressocom.ui.noticias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoticiasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Notícias fragment"
    }
    val text: LiveData<String> = _text
}
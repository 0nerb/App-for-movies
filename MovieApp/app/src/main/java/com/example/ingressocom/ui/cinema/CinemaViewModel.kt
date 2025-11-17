package com.example.ingressocom.ui.cinema

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CinemaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Cinema fragment"
    }
    val text: LiveData<String> = _text
}
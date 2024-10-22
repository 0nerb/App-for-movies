package com.example.ingressocom

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ingressocom.databinding.ActivityMainBinding
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ingressocom.ui.destaques.DestaquesViewModel
import com.example.ingressocom.ui.filmes.FilmesViewModel
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var destaquesViewModel: DestaquesViewModel
    private lateinit var filmesViewModel: FilmesViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_destaques, R.id.navigation_filmes, R.id.navigation_cinema, R.id.navigation_noticias
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



    val url = "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio"

        // Requisição da API
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val itemsArray = response.optJSONArray("items")
                if (itemsArray != null) {
                    processMovies(itemsArray)
                }
            },
            { error ->
                Log.e("MainActivity", "Erro: ${error.message}")
            }
        )
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }

    private fun processMovies(response: JSONArray) {

        val filmesComData = mutableListOf<JSONObject>()
        val filmesSemData = mutableListOf<JSONObject>()

        for (i in 0 until response.length()) {
            val movieObject: JSONObject = response.getJSONObject(i)

            val id = movieObject.optString("id")
            val title = movieObject.optString("title")
            val premiereDate = movieObject.optJSONObject("premiereDate")
            val imagesArray = movieObject.optJSONArray("images")

            val imageUrl = if (imagesArray != null && imagesArray.length() > 0) {
                imagesArray.getJSONObject(0).optString("url")
            } else {
                "No Image"
            }
            if (premiereDate != null){
                filmesComData.add(movieObject)
            } else{
                filmesSemData.add(movieObject)
            }
        }
        destaquesViewModel = ViewModelProvider(this).get(DestaquesViewModel::class.java)
        filmesViewModel = ViewModelProvider(this).get(FilmesViewModel::class.java)

        destaquesViewModel.setDestaques(filmesComData)
        filmesViewModel.setFilmes(filmesSemData)
    }
}


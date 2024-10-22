package com.example.ingressocom.ui.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ingressocom.databinding.FragmentCinemaBinding

class CinemaFragment : Fragment() {

    private var _binding: FragmentCinemaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cinemaViewModel =
            ViewModelProvider(this).get(CinemaViewModel::class.java)

        _binding = FragmentCinemaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCinema
        cinemaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
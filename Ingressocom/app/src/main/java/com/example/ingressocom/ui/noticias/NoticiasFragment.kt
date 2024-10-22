package com.example.ingressocom.ui.noticias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ingressocom.databinding.FragmentNoticiasBinding

class NoticiasFragment : Fragment() {

    private var _binding: FragmentNoticiasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noticiasViewModel =
            ViewModelProvider(this).get(NoticiasViewModel::class.java)

        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNoticias
        noticiasViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
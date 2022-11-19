package ru.yacevyuk.r.r.company.myapplication.ui.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.yacevyuk.r.r.company.myapplication.R
import ru.yacevyuk.r.r.company.myapplication.databinding.FragmentTimerBinding

class TimeFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val TimeViewModel =
            ViewModelProvider(this).get(TimeViewModel::class.java)
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timerText : TextView = view.findViewById(R.id.time)

        binding.buttondel.setOnClickListener {
            timerText.setText(null)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
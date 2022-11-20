package ru.yacevyuk.r.r.company.myapplication.ui.time

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.yacevyuk.r.r.company.myapplication.databinding.FragmentTimerBinding

class TimeFragment : Fragment() {
    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!
    private var countdown_timer: CountDownTimer? = null
    private var time_in_milliseconds = 10000L
    private var pauseOffSet = 0L


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

   binding.time.text = "${(time_in_milliseconds / 1000).toString()}"
        binding.btnstart.setOnClickListener {
            starTimer(pauseOffSet)
            Toast.makeText(requireContext(), "Начать", Toast.LENGTH_SHORT).show()
        }
        binding.btnpause.setOnClickListener {
            pauseTimer()
            Toast.makeText(requireContext(), "Пауза", Toast.LENGTH_SHORT).show()
        }
        binding.btnstop.setOnClickListener {
            resetTimer()
            Toast.makeText(requireContext(), "Стоп", Toast.LENGTH_SHORT).show()
        }
    }
    private fun starTimer(pauseOffSetL : Long){
        countdown_timer = object : CountDownTimer(time_in_milliseconds - pauseOffSetL, 1000){
            override fun onTick(millisUntilFinished: Long) {
                pauseOffSet = time_in_milliseconds - millisUntilFinished
                binding.time.text= (millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }.start()
    }
    private fun pauseTimer(){
        if (countdown_timer!= null){
            countdown_timer!!.cancel()
        }
    }
    private fun resetTimer(){
        if (countdown_timer!= null){
            countdown_timer!!.cancel()
            binding.time.text = " ${(time_in_milliseconds/1000).toString()}"
            countdown_timer = null
            pauseOffSet =0
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
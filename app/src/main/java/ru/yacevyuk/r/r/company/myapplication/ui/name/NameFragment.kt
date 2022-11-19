package ru.yacevyuk.r.r.company.myapplication.ui.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.yacevyuk.r.r.company.myapplication.R
import ru.yacevyuk.r.r.company.myapplication.databinding.FragmentNameBinding
import ru.yacevyuk.r.r.company.myapplication.db.DbConnection
import ru.yacevyuk.r.r.company.myapplication.room.Name
import ru.yacevyuk.r.r.company.myapplication.room.NameRepository

class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNameBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savebtn.setOnClickListener {
            var name = binding.textInputLayout.text.toString()
            val repository = NameRepository(DbConnection.getDatabase(requireContext()).nameDao())
                GlobalScope.launch {
                repository.insert(Name(name))
            }
            Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.action_nameFragment_to_navigation_Profile)
        }

    }
}




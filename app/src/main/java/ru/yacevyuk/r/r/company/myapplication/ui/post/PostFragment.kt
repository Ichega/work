package ru.yacevyuk.r.r.company.myapplication.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.yacevyuk.r.r.company.myapplication.databinding.FragmentPostBinding
import ru.yacevyuk.r.r.company.myapplication.ui.post.adapter.PostListAdapter

class PostFragment : Fragment() {

    private lateinit var _binding: FragmentPostBinding
    private lateinit var viewModel: PostViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostListAdapter()
        adapter.itemClick {

        }

        _binding.postsListRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[PostViewModel::class.java]
        viewModel.posts.observe(requireActivity()) {
            if (it != null) {
                adapter.items = it
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.saveInCache()
    }
}

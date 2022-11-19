package ru.yacevyuk.r.r.company.myapplication.ui.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.yacevyuk.r.r.company.myapplication.R
import ru.yacevyuk.r.r.company.myapplication.databinding.PostsListItemBinding
import ru.yacevyuk.r.r.company.myapplication.ui.post.models.Post

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    var items: List<Post> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var itemClick: (Post) -> Unit = {}
    fun itemClick(listener: (Post) -> Unit) {
        itemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.post = items[position]
        holder.itemView.setOnClickListener {
            itemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = PostsListItemBinding.bind(view)

    }
}
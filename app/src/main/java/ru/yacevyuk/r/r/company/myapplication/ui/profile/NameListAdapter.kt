package ru.yacevyuk.r.r.company.myapplication.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.yacevyuk.r.r.company.myapplication.R
import ru.yacevyuk.r.r.company.myapplication.databinding.NameListBinding
import ru.yacevyuk.r.r.company.myapplication.room.Name

class NameListAdapter : RecyclerView.Adapter<NameListAdapter.ViewHolder>() {

    var items: List<Name> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var itemClick: (Name) -> Unit = {}
    fun itemClick(listener: (Name) -> Unit) {
        itemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.name_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name = items[position]
        holder.itemView.setOnClickListener {
            itemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = NameListBinding.bind(view)

    }
}
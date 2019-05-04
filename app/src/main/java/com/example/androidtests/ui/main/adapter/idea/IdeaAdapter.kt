package com.example.androidtests.ui.main.adapter.idea

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtests.R

class IdeaAdapter(
    private val ideas: Array<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<IdeaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_idea, parent, false)
        return IdeaViewHolder(view)
    }

    override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) {
        val label = ideas[position]
        holder.textView.text = label
        holder.textView.setOnClickListener { listener.onItemClick(position) }
    }

    override fun getItemCount(): Int {
        return ideas.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
package com.example.note_tab.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.note_tab.databinding.NoteItemBinding
import com.example.note_tab.ui.dp.OnClickItem
import com.example.note_tab.ui.model.NoteModel

class NoteAdapter(
    private val onLongCLick: OnClickItem,
    private val onCLick: OnClickItem,
): ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(item: NoteModel){
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvDate.text = item.date
            binding.rvNotesItem.setBackgroundColor(item.color)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener {
            onLongCLick.onLongClick(getItem(position))
            true
        }

        holder.itemView.setOnClickListener {
            onCLick.onClick(getItem(position))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class DiffCallback: DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }
}
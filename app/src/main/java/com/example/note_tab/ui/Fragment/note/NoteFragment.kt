package com.example.note_tab.ui.Fragment.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_tab.R
import com.example.note_tab.databinding.FragmentNoteBinding
import com.example.note_tab.ui.App
import com.example.note_tab.ui.adapters.NoteAdapter
import com.example.note_tab.ui.dp.OnClickItem
import com.example.note_tab.ui.model.NoteModel

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initalize()
        setUpListeners()
        getData()
    }

    private fun initalize() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setUpListeners() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { model ->
            noteAdapter.submitList(model)
            noteAdapter.notifyDataSetChanged()
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext()).setView(view).create()
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.delete_alert_dialog, null)
        val delete = view.findViewById<TextView>(R.id.delete_ok)
        val cancel = view.findViewById<TextView>(R.id.delete_cancel)

        builder.setView(view)
        val alertDialog = builder
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.show()

        delete.setOnClickListener {
            App.appDatabase?.noteDao()?.deleteNote(noteModel)
            alertDialog.dismiss()
        }

        cancel.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}
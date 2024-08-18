package com.example.room.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.room.R
import com.example.room.databinding.FragmentEditAndDeleteNoteBinding
import com.example.room.models.ModelNotes
import com.example.room.viewmodels.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditAndDeleteNoteFragment : Fragment() {
    private lateinit var binding: FragmentEditAndDeleteNoteBinding
    private lateinit var notesViewModel: NotesViewModel
    private var noteId = 0
    private var date: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditAndDeleteNoteBinding.inflate(inflater, container, false)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.title = "Edit Note"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        arguments?.let {
            noteId = it.getInt("NOTE_ID", 0)
            val title = it.getString("TITLE")
            val description = it.getString("DESCRIPTION")
            val dateString = it.getString("DATE")

            binding.etTitle.setText(title)
            binding.etDescription.setText(description)


        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveNote -> {
                saveNote()
                true
            }
            R.id.deleteNote -> {
                val deleteNote =ModelNotes(
                    id = noteId,
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDescription.text.toString(),
                    date = date ?: Date() // Use current date if date is null
                )
                notesViewModel.deleteNote(deleteNote)
                findNavController().navigateUp()
                true
            }
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        val updatedNote = ModelNotes(
            id = noteId,
            title = binding.etTitle.text.toString(),
            desc = binding.etDescription.text.toString(),
            date = date ?: Date() // Use current date if date is null
        )
        notesViewModel.updateNote(updatedNote)
        Toast.makeText(requireContext(), "Note updated", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }


}

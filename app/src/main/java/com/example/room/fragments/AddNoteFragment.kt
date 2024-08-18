package com.example.room.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.databinding.FragmentAddNoteBinding
import com.example.room.models.ModelNotes
import com.example.room.viewmodels.NotesViewModel
import java.util.Date


class AddNoteFragment : Fragment() {

    private lateinit var binding:FragmentAddNoteBinding
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater,container,false)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Note"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)


        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    private fun addNote() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()

        if(title.isNotEmpty() && description.isNotEmpty()){
            val date = Date()

         val newNote = ModelNotes(
                title = title,
             desc = description,
             date = date
            )
            notesViewModel.addNote(newNote)
            Toast.makeText(requireContext(), "Note Added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
            }
            R.id.addNote->{
                addNote()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
package com.example.room.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Query
import com.example.room.R
import com.example.room.adapter.RvHomeAdapter
import com.example.room.databinding.FragmentHomeBinding
import com.example.room.viewmodels.NotesViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var adapter: RvHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        adapter = RvHomeAdapter(emptyList())

        (activity as AppCompatActivity).supportActionBar?.title = "Notes"

        binding.fabAdd.setOnClickListener(){
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
        inItRv()
        return binding.root
    }
    private fun inItRv() {
        binding.rvHome.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        notesViewModel.getAllNotes().observe(viewLifecycleOwner, Observer { notes->
            adapter.arrNotes = notes
            adapter.notifyDataSetChanged()
        })
        binding.rvHome.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    searchDatabase(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!=null){
                    searchDatabase(newText)
                }
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"

        notesViewModel.searchNote(searchQuery)?.observe(this, Observer {list->
            adapter.arrNotes = list
            adapter.notifyDataSetChanged()
        })
    }


}
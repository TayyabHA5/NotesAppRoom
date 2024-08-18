package com.example.room.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.fragments.EditAndDeleteNoteFragment
import com.example.room.models.ModelNotes
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Locale

class RvHomeAdapter( var arrNotes : List<ModelNotes>) : RecyclerView.Adapter<RvHomeAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvTitleRv)
        val description = itemView.findViewById<TextView>(R.id.tvDescRv)
        val date = itemView.findViewById<TextView>(R.id.tvDateRv)
        val cardLayout = itemView.findViewById<MaterialCardView>(R.id.cardLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_home_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvHomeAdapter.ViewHolder, position: Int) {
        val currentPosition = arrNotes[position]
        holder.title.text = currentPosition.title
        holder.description.text = currentPosition.desc
        val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
        val formatedDate = dateFormat.format(currentPosition.date)
        holder.date.text = formatedDate


        holder.cardLayout.setOnClickListener(){
            val bundle = Bundle().apply {
                putInt("NOTE_ID",currentPosition.id)
                putString("TITLE",currentPosition.title)
                putString("DESCRIPTION",currentPosition.desc)
                putString("DATE", currentPosition.date.toString())
            }
            it.findNavController().navigate(R.id.action_homeFragment_to_editAndDeleteNoteFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return arrNotes.size
    }
}
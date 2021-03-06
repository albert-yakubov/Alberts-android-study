package com.stepasha.notetaker.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stepasha.notetaker.R
import com.stepasha.notetaker.ViewActivity
import com.stepasha.notetaker.models.NotesModel
import kotlinx.android.synthetic.main.item_card_list_notes.view.*


class RecyclerViewForNotesAdapter : RecyclerView.Adapter<RecyclerViewForNotesAdapter.ViewHolder>{
//list of notes
    lateinit var listdata : MutableList<NotesModel>
    lateinit var inflater : LayoutInflater
    lateinit var context : Context

    constructor(listdata : MutableList<NotesModel>, context: Context):super(){
        this.context=context
        this.inflater= LayoutInflater.from(context)
        this.listdata=listdata
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view : View = inflater.inflate(R.layout.item_card_list_notes, p0, false )
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listnote : NotesModel =listdata.get(position)
        holder.id.text = listnote.id.toString()
        holder.title.text = listnote.title
        holder.note.text = listnote.note
        holder.date.text = listnote.date
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var id = itemView.txt_Id
        var title = itemView.txt_Title
        var date = itemView.txt__Date
        var note = itemView.txt_Note

        //setting on click on the view itself
        init {
            itemView.setOnClickListener(this)
        }
        //then put in the context
        override fun onClick(v: View?) {
            val intent = Intent(itemView.context, ViewActivity::class.java)
            intent.putExtra("id",id.text.toString())
            itemView.context.startActivity(intent)
        }
    }
}
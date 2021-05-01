package de.brazill.occursum.activities.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.brazill.occursum.R
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.conversation_topic.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class EditContactDislikeAdaptor constructor(
        private var contact: ContactModel,
        private val listener: EditContactListener) : RecyclerView.Adapter<EditContactDislikeAdaptor.ViewContactDislikesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewContactDislikesHolder {
        return ViewContactDislikesHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.conversation_topic,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(dislikesHolder: ViewContactDislikesHolder, position: Int) {
        dislikesHolder.bind(contact, position, listener)
    }

    override fun getItemCount(): Int = contact.dislikes.size

    class ViewContactDislikesHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView), AnkoLogger {
        fun bind(contact: ContactModel, position: Int, listener: EditContactListener) {
            itemView.topic.text = contact.dislikes[position]
            itemView.topic_delete_button.setOnClickListener { listener.onDislikeDeleteClick(contact, position) }
        }
    }
}
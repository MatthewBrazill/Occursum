package de.brazill.occursum.activities.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.brazill.occursum.R
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.conversation_topic.view.*
import org.jetbrains.anko.AnkoLogger

class EditContactLikeAdaptor constructor(private var contact: ContactModel, private val listener: EditContactListener) :
    RecyclerView.Adapter<EditContactLikeAdaptor.ViewContactLikesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewContactLikesHolder {
        return ViewContactLikesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.conversation_topic,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(likesHolder: ViewContactLikesHolder, position: Int) {
        likesHolder.bind(contact, position, listener)
    }

    override fun getItemCount(): Int = contact.likes.size

    class ViewContactLikesHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView), AnkoLogger {
        fun bind(contact: ContactModel, position: Int, listener: EditContactListener) {
            itemView.topic.text = contact.likes[position]
            itemView.topic_delete_button.setOnClickListener { listener.onLikeDeleteClick(contact, position) }
        }
    }
}
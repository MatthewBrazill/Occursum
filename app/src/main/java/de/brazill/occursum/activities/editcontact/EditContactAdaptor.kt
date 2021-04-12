package de.brazill.occursum.activities.editcontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.brazill.occursum.R
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.conversation_topic.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class EditContactAdaptor constructor(
        private var contacts: List<ContactModel>,
        private val listener: EditContactListener) : RecyclerView.Adapter<EditContactAdaptor.ViewContactHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewContactHolder {
        return ViewContactHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.conversation_topic,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewContactHolder, position: Int) {
        val contact = contacts[holder.adapterPosition]
        holder.likeBind(contact, position, listener)
    }

    override fun getItemCount(): Int = contacts.size

    class ViewContactHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView), AnkoLogger {
        fun likeBind(contact: ContactModel, position: Int, listener: EditContactListener) {
            info(position)
            itemView.topic.text = contact.likes[position]
            itemView.topic_delete_button.setOnClickListener{ listener.onLikeDeleteClick(contact, position) }
        }
    }
}
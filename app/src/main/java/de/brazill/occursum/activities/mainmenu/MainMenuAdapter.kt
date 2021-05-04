package de.brazill.occursum.activities.mainmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.brazill.occursum.R
import de.brazill.occursum.helpers.setImageSafe
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.contact_card.view.*
import java.util.*

class MainMenuAdapter constructor(private var contacts: List<ContactModel>, private val listener: MainMenuListener) : RecyclerView.Adapter<MainMenuAdapter.MainHolder>() {

    var filterList = emptyList<ContactModel>().toMutableList()
    var filterActive = false

    fun setFilter(query: String) {
        filterList = emptyList<ContactModel>().toMutableList()
        filterActive = true
        for (contact in contacts) {
            val name = "${contact.firstName} ${contact.lastName}".toLowerCase(Locale.getDefault())
            if (query.toLowerCase(Locale.getDefault()) in name) {
                filterList.add(contact)
            }
        }
        notifyDataSetChanged()
    }

    fun clearFilter() {
        filterActive = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if (!filterActive) holder.bind(contacts[holder.adapterPosition], listener)
        else holder.bind(filterList[holder.adapterPosition], listener)
    }

    override fun getItemCount(): Int {
        return if (!filterActive) contacts.size
        else filterList.size
    }

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: ContactModel, listener: MainMenuListener) {
            //Merge the names before assignment.
            val fullName = "${contact.firstName} ${contact.lastName}"
            itemView.contact_card_name.text = fullName

            //Add the image to the card.
            itemView.contact_card_contact_image.setImageSafe(contact.img)

            //Create the well formatted 'likes' String to add to the interface.
            var likes = ""
            if (contact.likes.isNotEmpty()) {
                for (like in contact.likes) likes += "- $like\n"
            }
            itemView.contact_card_likes_list.text = likes

            //Create the well formatted 'dislikes' String.
            var dislikes = ""
            for (dislike in contact.dislikes) dislikes += "- $dislike\n"
            itemView.contact_card_dislikes_list.text = dislikes

            //Set the listener for clicking on the card
            itemView.setOnClickListener { listener.onCardClick(contact) }
        }
    }
}
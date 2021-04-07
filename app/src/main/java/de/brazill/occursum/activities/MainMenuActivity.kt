package de.brazill.occursum.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.brazill.occursum.R
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.contact_card.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainMenuActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("Main Menu Started...")

        setContentView(R.layout.activity_main_menu)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        contact_cards.layoutManager = layoutManager
        contact_cards.adapter = MainMenuAdapter(app.contacts)
    }
}

class MainMenuAdapter constructor(private var contacts: List<ContactModel>) : RecyclerView.Adapter<MainMenuAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.contact_card,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val contact = contacts[holder.adapterPosition]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = contacts.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: ContactModel) {
            //Merge the names before assignment.
            val fullName = contact.firstName + " " + contact.lastName
            itemView.contact_card_name.text = fullName

            //Add the image to the card.
            itemView.contact_card_contact_image.setImageResource(contact.img)

            //Create the well formatted 'likes' String to add to the interface.
            var likes = ""
            for (like in contact.likes) likes += "- $like\n"
            itemView.contact_card_likes_list.text = likes

            //Create the well formatted 'dislikes' String.
            var dislikes = ""
            for (dislike in contact.dislikes) dislikes += "- $dislike\n"
            itemView.contact_card_dislikes_list.text = dislikes
        }
    }
}
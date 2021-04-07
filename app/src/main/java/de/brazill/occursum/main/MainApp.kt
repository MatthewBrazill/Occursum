package de.brazill.occursum.main

import android.app.Application
import de.brazill.occursum.R
import de.brazill.occursum.models.ContactModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {

    val contacts = ArrayList<ContactModel>()

    override fun onCreate() {
        super.onCreate()
        info("Main App Started...")

        contacts.add(ContactModel("John", "Smith", "john@smith.com", "+353 83 549 7628", listOf("Food", "Wine", "Cars"), listOf("Mushrooms", "Bees"), R.drawable.ic_example_avatar_one))
        contacts.add(ContactModel("Damien", "Colley", "dcolley1@shutterfly.com", "+237 540 772 8911", listOf("Food", "Wine", "Cars"), listOf("Mushrooms", "Bees"), R.drawable.ic_example_avatar_two))
        contacts.add(ContactModel("Sawyere", "Browning", "sbrowning2@e-recht24.de", "+63 770 351 1970", listOf("Food", "Wine", "Cars"), listOf("Mushrooms", "Bees"), R.drawable.ic_example_avatar_three))
        contacts.add(ContactModel("Cherice", "Haseman", "chaseman3@netscape.com", "+63 662 697 7037", listOf("Food", "Wine", "Cars"), listOf("Mushrooms", "Bees")))
        contacts.add(ContactModel("Donalt", "Richardeau", "drichardeau4@surveymonkey.com", "+420 844 186 5696", listOf("Food", "Wine", "Cars"), listOf("Mushrooms", "Bees")))
        contacts.add(ContactModel("Jelene", "Rustedge", "jrustedge0@twitpic.com", "+961 404 353 1715", listOf("Food", "Wine", "Cars"), listOf("Mushrooms", "Bees")))
    }
}
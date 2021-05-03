package de.brazill.occursum.activities.mainmenu

import de.brazill.occursum.models.ContactModel

interface MainMenuListener {
    fun onCardClick(contact: ContactModel)
    fun onQueryTextSubmit(query: String): Boolean
    fun onQueryTextChange(query: String): Boolean
}
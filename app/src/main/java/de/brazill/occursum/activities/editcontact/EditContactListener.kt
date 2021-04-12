package de.brazill.occursum.activities.editcontact

import de.brazill.occursum.models.ContactModel

interface EditContactListener {
    fun onLikeDeleteClick(contact: ContactModel, position: Int)
}
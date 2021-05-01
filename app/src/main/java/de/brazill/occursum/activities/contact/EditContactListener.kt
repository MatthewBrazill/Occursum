package de.brazill.occursum.activities.contact

import de.brazill.occursum.models.ContactModel

interface EditContactListener {
    fun onLikeDeleteClick(contact: ContactModel, position: Int)
    fun onDislikeDeleteClick(contact: ContactModel, position: Int)
}
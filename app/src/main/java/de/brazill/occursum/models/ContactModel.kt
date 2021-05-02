package de.brazill.occursum.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ContactModel(
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var phone: String = "",
        var likes: MutableList<String> = listOf("").toMutableList(),
        var dislikes: MutableList<String> = listOf("").toMutableList(),
        var img: String = "",
        val id: String = UUID.randomUUID().toString()
) : Parcelable {}
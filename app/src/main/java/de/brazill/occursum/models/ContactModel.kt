package de.brazill.occursum.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactModel(
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var phone: String = "",
        var likes: MutableList<String> = listOf("").toMutableList(),
        var dislikes: MutableList<String> = listOf("").toMutableList(),
        var img: Int = 0
) : Parcelable
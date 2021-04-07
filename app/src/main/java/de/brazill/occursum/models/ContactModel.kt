package de.brazill.occursum.models

data class ContactModel(
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var phone: String = "",
        var likes: List<String> = listOf(""),
        var dislikes: List<String> = listOf(""),
        var img: Int = 0
)
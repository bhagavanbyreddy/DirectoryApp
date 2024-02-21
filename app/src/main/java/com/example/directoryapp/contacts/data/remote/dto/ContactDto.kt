package com.example.directoryapp.contacts.data.remote.dto

import com.example.directoryapp.contacts.domain.model.Contact

data class ContactDto(
    val avatar: String,
    val createdAt: String,
    val email: String,
    val favouriteColor: String,
    val firstName: String,
    val id: String,
    val jobtitle: String,
    val lastName: String
)

fun ContactDto.toContact(): Contact {
    return Contact(
        id = id,
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}
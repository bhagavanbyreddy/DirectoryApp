package com.example.directoryapp.contacts.data.remote

import com.example.directoryapp.contacts.data.remote.dto.ContactDto
import retrofit2.http.GET

interface ContactsApi {

    @GET("v1/people")
    suspend fun getContactList(): List<ContactDto>

}
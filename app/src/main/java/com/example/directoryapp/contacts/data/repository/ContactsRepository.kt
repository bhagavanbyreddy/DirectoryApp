package com.example.directoryapp.contacts.data.repository

import com.example.directoryapp.common.Resource
import com.example.directoryapp.contacts.data.remote.dto.ContactDto
import com.example.directoryapp.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

  fun getContactList(): Flow<Resource<List<Contact>>>

}
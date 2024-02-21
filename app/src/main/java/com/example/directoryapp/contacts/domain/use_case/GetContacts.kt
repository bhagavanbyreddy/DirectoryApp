package com.example.directoryapp.contacts.domain.use_case

import com.example.directoryapp.common.Resource
import com.example.directoryapp.contacts.data.repository.ContactsRepository
import com.example.directoryapp.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetContacts @Inject constructor(
    private val repository: ContactsRepository
) {

    operator fun invoke(): Flow<Resource<List<Contact>>> {
        return repository.getContactList()
    }
}
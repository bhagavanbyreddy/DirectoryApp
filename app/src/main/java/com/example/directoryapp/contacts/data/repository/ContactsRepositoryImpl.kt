package com.example.directoryapp.contacts.data.repository

import com.example.directoryapp.common.Resource
import com.example.directoryapp.contacts.data.remote.ContactsApi
import com.example.directoryapp.contacts.data.remote.dto.ContactDto
import com.example.directoryapp.contacts.data.remote.dto.toContact
import com.example.directoryapp.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val api: ContactsApi
) : ContactsRepository {

    override fun getContactList(): Flow<Resource<List<Contact>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val contactsList = api.getContactList().map { it.toContact() }
                emit(Resource.Success(data = contactsList))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = e.localizedMessage ?: "Something went wrong, Please try again"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error(message = "Couldn't reach server, Check your internet connection"))
            }
        }

    }
}
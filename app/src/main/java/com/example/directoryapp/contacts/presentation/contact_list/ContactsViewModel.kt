package com.example.directoryapp.contacts.presentation.contact_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.directoryapp.common.Resource
import com.example.directoryapp.contacts.domain.model.Contact
import com.example.directoryapp.contacts.domain.use_case.GetContactDetails
import com.example.directoryapp.contacts.domain.use_case.GetContacts
import com.example.directoryapp.contacts.presentation.contact_details.ContactDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContacts: GetContacts,
    private val getContactDetails: GetContactDetails,
) : ViewModel() {

    private val _contactsState = mutableStateOf(ContactsState())
    val contactsState: State<ContactsState> = _contactsState

    private val _contactDetailsState = mutableStateOf(ContactDetailsState())
    val contactDetailsState: State<ContactDetailsState> = _contactDetailsState

    private lateinit var contactsList: List<Contact>

    fun getContactList() {
        getContacts().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _contactsState.value = ContactsState(isLoading = true)
                }

                is Resource.Success -> {
                    result.data?.let {
                        contactsList = it
                    }
                    _contactsState.value = ContactsState(contactsList = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _contactsState.value =
                        ContactsState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getContactDetails(id: String) {
        if (
            ::contactsList.isInitialized
            && contactsList.isNotEmpty()
        ) {
            val contactDetails = getContactDetails(id, contactsList)
            if (contactDetails != null) {
                _contactDetailsState.value = ContactDetailsState(contactDetails = contactDetails)
            } else {
                _contactDetailsState.value =
                    ContactDetailsState(error = "No details found for selected contact")
            }
        }
    }

}
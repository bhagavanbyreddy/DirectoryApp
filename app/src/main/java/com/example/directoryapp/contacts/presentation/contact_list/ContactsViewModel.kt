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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContacts: GetContacts,
    private val getContactDetails: GetContactDetails,
) : ViewModel() {

    private val _contactsState = MutableStateFlow(ContactsState())
    val contactsState: StateFlow<ContactsState> = _contactsState.asStateFlow()

    private val _contactDetailsState = MutableStateFlow(ContactDetailsState())
    val contactDetailsState: StateFlow<ContactDetailsState> = _contactDetailsState.asStateFlow()

    fun getContactList() {
        getContacts().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _contactsState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Resource.Success -> {
                    _contactsState.update {
                        it.copy(
                            contactsList = result.data ?: emptyList(),
                        )
                    }
                }

                is Resource.Error -> {
                    _contactsState.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getContactDetails(id: String) {
        if (_contactsState.value.contactsList.isNotEmpty()) {
            val contactDetails = getContactDetails(id, _contactsState.value.contactsList)
            if (contactDetails != null) {
                _contactDetailsState.update {
                    it.copy(
                        contactDetails = contactDetails
                    )
                }
            } else {
                _contactDetailsState.update {
                    it.copy(
                        error = "No details found for selected contact"
                    )
                }
            }
        }
    }

}
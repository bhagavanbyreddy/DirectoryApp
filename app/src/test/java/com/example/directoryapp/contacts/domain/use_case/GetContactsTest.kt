package com.example.directoryapp.contacts.domain.use_case

import com.example.directoryapp.contacts.data.repository.FakeContactsRepositoryImpl
import com.example.directoryapp.contacts.domain.model.Contact
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetContactsTest {

    private lateinit var getContacts: GetContacts
    private lateinit var fakeContactsRepository: FakeContactsRepositoryImpl

    @Before
    fun setUp() {
        fakeContactsRepository = FakeContactsRepositoryImpl()
        getContacts = GetContacts(fakeContactsRepository)

        val contactsToInsert = mutableListOf<Contact>()
        ('a'..'z').forEachIndexed { index, c ->
            contactsToInsert.add(
                Contact(
                    id = index.toString(),
                    firstName = c.toString(),
                    lastName = c.toString(),
                    email = "$c@gmail.com"
                )
            )
        }
        runBlocking {
            contactsToInsert.forEach { fakeContactsRepository.insertContact(it) }
        }
    }

    /*assign
      act
      assert
    */
    @Test
    fun `contacts are not empty`() = runBlocking {
        var size = 0
        getContacts().collect { result ->
            size = result.data?.size ?: 0
        }
        assert(size > 0)
    }
}
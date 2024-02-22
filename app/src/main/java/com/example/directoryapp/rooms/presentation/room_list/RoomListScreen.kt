package com.example.directoryapp.rooms.presentation.room_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.directoryapp.common.Constants
import com.example.directoryapp.common.rememberLifecycleEvent
import com.example.directoryapp.contacts.presentation.contact_list.ContactsViewModel
import com.example.directoryapp.contacts.presentation.contact_list.components.ContactListItem
import com.example.directoryapp.rooms.presentation.room_list.components.RoomListItem

@Composable
fun RoomListScreen(
    navController: NavController,
    viewModel: RoomsViewModel = hiltViewModel()
) {
    val state by viewModel.roomsState.collectAsState()

    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.getRoomList()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.roomsList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.roomsList) { room ->
                    RoomListItem(
                        room = room
                    )
                }
            }
        } else if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.onError,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        } else if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
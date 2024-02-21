package com.example.directoryapp.rooms.presentation.room_list

import com.example.directoryapp.rooms.domain.model.Room

data class RoomsState(
    val roomsList: List<Room> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

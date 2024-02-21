package com.example.directoryapp.rooms.data.remote.dto

import com.example.directoryapp.rooms.domain.model.Room

data class RoomDto(
    val createdAt: String,
    val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int
)

fun RoomDto.toRoom(): Room {
    return Room(
        id = id,
        isOccupied = isOccupied
    )
}
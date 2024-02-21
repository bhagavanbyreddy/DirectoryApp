package com.example.directoryapp.rooms.data.remote

import com.example.directoryapp.rooms.data.remote.dto.RoomDto
import retrofit2.http.GET

interface RoomsApi {

    @GET("v1/rooms")
    suspend fun getRoomList(): List<RoomDto>

}
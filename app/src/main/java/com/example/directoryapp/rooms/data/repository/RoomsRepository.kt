package com.example.directoryapp.rooms.data.repository

import com.example.directoryapp.common.Resource
import com.example.directoryapp.rooms.domain.model.Room
import kotlinx.coroutines.flow.Flow

interface RoomsRepository {

    fun getRoomList(): Flow<Resource<List<Room>>>

}
package com.example.directoryapp.rooms.domain.use_case

import com.example.directoryapp.common.Resource
import com.example.directoryapp.rooms.data.repository.RoomsRepository
import com.example.directoryapp.rooms.domain.model.Room
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRooms @Inject constructor(
    private val roomsRepository: RoomsRepository
) {

    operator fun invoke(): Flow<Resource<List<Room>>> {
        return roomsRepository.getRoomList()
    }
}
package com.example.directoryapp.rooms.data.repository

import com.example.directoryapp.common.Resource
import com.example.directoryapp.rooms.data.remote.RoomsApi
import com.example.directoryapp.rooms.data.remote.dto.toRoom
import com.example.directoryapp.rooms.domain.model.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RoomsRepositoryImpl @Inject constructor(
    private val roomsApi: RoomsApi
) : RoomsRepository {
    override fun getRoomList(): Flow<Resource<List<Room>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val roomsList = roomsApi.getRoomList().map { it.toRoom() }
                emit(Resource.Success(data = roomsList))
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
package com.example.directoryapp.rooms.di

import com.example.directoryapp.contacts.di.ContactsModule
import com.example.directoryapp.rooms.data.remote.RoomsApi
import com.example.directoryapp.rooms.data.repository.RoomsRepository
import com.example.directoryapp.rooms.data.repository.RoomsRepositoryImpl
import com.example.directoryapp.rooms.domain.use_case.GetRooms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ContactsModule::class])
@InstallIn(SingletonComponent::class)
object RoomsModule {

    @Provides
    @Singleton
    fun provideRoomsApi(retrofit: Retrofit): RoomsApi {
        return retrofit.create(RoomsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomsRepository(roomsApi: RoomsApi): RoomsRepository {
        return RoomsRepositoryImpl(roomsApi)
    }

    @Provides
    @Singleton
    fun provideGetRoomsUseCase(roomsRepository: RoomsRepository): GetRooms {
        return GetRooms(roomsRepository)
    }
}
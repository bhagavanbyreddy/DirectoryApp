package com.example.directoryapp.rooms.presentation.room_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.directoryapp.common.Resource
import com.example.directoryapp.rooms.domain.use_case.GetRooms
import com.example.directoryapp.rooms.presentation.room_list.RoomsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val getRooms: GetRooms
) : ViewModel() {

    private val _roomsState = mutableStateOf(RoomsState())
    val roomsState: State<RoomsState> = _roomsState

    fun getRoomList() {
        getRooms().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _roomsState.value = RoomsState(isLoading = true)
                }

                is Resource.Success -> {
                    _roomsState.value = RoomsState(roomsList = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _roomsState.value =
                        RoomsState(error = result.message ?: "An unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }
}
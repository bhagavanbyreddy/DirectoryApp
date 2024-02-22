package com.example.directoryapp.rooms.presentation.room_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.directoryapp.common.Resource
import com.example.directoryapp.rooms.domain.use_case.GetRooms
import com.example.directoryapp.rooms.presentation.room_list.RoomsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val getRooms: GetRooms
) : ViewModel() {

    private val _roomsState = MutableStateFlow(RoomsState())
    val roomsState: StateFlow<RoomsState> = _roomsState.asStateFlow()

    fun getRoomList() {
        getRooms().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _roomsState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Resource.Success -> {
                    _roomsState.update {
                        it.copy(
                            roomsList = result.data ?: emptyList()
                        )
                    }
                }

                is Resource.Error -> {
                    _roomsState.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }

        }.launchIn(viewModelScope)
    }
}
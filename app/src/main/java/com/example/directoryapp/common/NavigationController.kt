package com.example.directoryapp.common

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.directoryapp.contacts.presentation.HomeScreen
import com.example.directoryapp.contacts.presentation.contact_details.ContactDetailsScreen
import com.example.directoryapp.contacts.presentation.contact_list.ContactListScreen
import com.example.directoryapp.contacts.presentation.contact_list.ContactsViewModel
import com.example.directoryapp.rooms.presentation.room_list.RoomListScreen
import com.example.directoryapp.rooms.presentation.room_list.RoomsViewModel

@Composable
fun NavigationController(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Constants.HOME_SCREEN
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Constants.HOME_SCREEN) {
            HomeScreen(
                navController = navController,
            )
        }

        composable(Constants.CONTACT_LIST_SCREEN) {
            val contactsViewModel = viewModel<ContactsViewModel>(viewModelStoreOwner = viewModelStoreOwner)
            ContactListScreen(
                navController = navController,
                viewModel = contactsViewModel
            )
        }

        composable(Constants.CONTACT_DETAILS_SCREEN + "/{${Constants.KEY_ID}}") { backStackEntry ->
            BackHandler(enabled = true) {
                navController.popBackStack()
            }
            backStackEntry.arguments?.getString("id", "0")?.let { _ ->
                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides viewModelStoreOwner
                ) {
                    val contactsViewModel = viewModel<ContactsViewModel>()
                    ContactDetailsScreen(
                        navController = navController,
                        viewModel = contactsViewModel
                    )
                }
            }
        }

        composable(Constants.ROOM_LIST_SCREEN) {
            val viewModel = hiltViewModel<RoomsViewModel>()
            RoomListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

    }
}
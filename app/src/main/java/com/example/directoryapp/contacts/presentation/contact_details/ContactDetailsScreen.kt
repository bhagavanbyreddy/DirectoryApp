package com.example.directoryapp.contacts.presentation.contact_details

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.directoryapp.R
import com.example.directoryapp.common.Constants
import com.example.directoryapp.common.rememberLifecycleEvent
import com.example.directoryapp.contacts.presentation.contact_list.ContactsViewModel

@Composable
fun ContactDetailsScreen(
    navController: NavController,
    viewModel: ContactsViewModel = hiltViewModel()
) {
    val state by viewModel.contactDetailsState.collectAsState()
    val id = navController.currentBackStackEntry?.arguments?.getString(Constants.KEY_ID)

    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            id?.let { viewModel.getContactDetails(it) }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (state.contactDetails.id.isNotBlank()) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .aspectRatio(16 / 9f)
                        .fillMaxWidth()
                        .shadow(elevation = 16.dp)
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        alignment = Alignment.Center,
                        model = "${state.contactDetails.avatar}",
                        contentDescription = state.contactDetails.firstName,
                        error = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                RoundedCornerShape(20.dp)
                            ),
                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Id: ${state.contactDetails.id}",
                            style = MaterialTheme.typography.bodyLarge,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,

                            )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "First Name: ${state.contactDetails.firstName}",
                            style = MaterialTheme.typography.bodyLarge,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Last Name: ${state.contactDetails.lastName}",
                            style = MaterialTheme.typography.bodyLarge,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Email: ${state.contactDetails.email}",
                            style = MaterialTheme.typography.bodyLarge,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        } else if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.onError,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        } else if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}
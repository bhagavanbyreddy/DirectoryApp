package com.example.directoryapp.contacts.presentation.contact_list.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.directoryapp.R
import com.example.directoryapp.contacts.domain.model.Contact

@Composable
fun ContactListItem(
    contact: Contact,
    onItemClick: (Contact) -> Unit
) {
    ElevatedCard(
        modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(contact) }
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                alignment = Alignment.Center,
                model = "${contact.avatar}",
                contentDescription = contact.firstName,
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(
                        RoundedCornerShape(30.dp)
                    ),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column {
                    Text(
                        text = "${contact.firstName}",
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "${contact.email}",
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
package com.example.theonaui2.ui.main.chat

import android.content.res.Configuration
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theonaui2.R

@Composable
fun ChatAppbar(title: String = "Sample", onBack: (() -> Unit)? = null) {
    TopAppBar(
        elevation = 4.dp,
        backgroundColor = Color(0xffffffff),
        contentColor = Color(0xff183651),
    )
    {

        Row(
            modifier = Modifier.weight(1f),
        ) {
            val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .clickable(
                        onClick = { onBackPressedDispatcher?.onBackPressed() }
                    ) ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    //imageVector = Icons.Filled.ArrowBack,
                    painter = painterResource(id = R.drawable.goback_icon),
                    contentDescription = null
                )

                Surface(
                    modifier = Modifier.padding(6.dp),
                    shape = CircleShape,
                    color = Color.LightGray
                ) {
                    Icon(
                        imageVector = Icons.Filled.Groups,
                        contentDescription = null,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp)
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { /*TODO: */ }
                    .padding(2.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color(0xff183651),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        "This is dynamic chat sample",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        ChatAppbarActions()
    }
}

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun ChatAppbarPreview() {
    ChatAppbar("Chat Bubble samples")
}

@Composable
private fun ChatAppbarActions(
    onCamClick: (() -> Unit)? = null,
    onCallClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IndicatingIconButton(
            onClick = { /* TODO */ },
            indication = rememberRipple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.Call,
                contentDescription = null,
                tint = Color(0xff5E81AC)
            )
        }

        var expanded by remember { mutableStateOf(false) }

        IndicatingIconButton(
            onClick = { expanded = true },
            indication = rememberRipple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = null,
                tint = Color(0xff5E81AC)
            )
        }
        openDropDownMenu(expanded = expanded)
    }
}

@Composable
fun openDropDownMenu(expanded: Boolean) {
    Box {
        var displayMenu by remember { androidx.compose.runtime.mutableStateOf(false) }

        DropdownMenu(
            expanded = displayMenu,
            onDismissRequest = { displayMenu = false }
        ) {

            // Creating dropdown menu item, on click
            // would create a Toast message
            DropdownMenuItem(onClick = { }) {
                Text(text = "See user on map")
            }

            // Creating dropdown menu item, on click
            // would create a Toast message
            DropdownMenuItem(onClick = { }) {
                Text(
                    text = "Block user",
                    color = Color.Red
                )
            }
        }
    }
}



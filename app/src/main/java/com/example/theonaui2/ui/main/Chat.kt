package com.example.theonaui2.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theonaui2.R
import com.example.theonaui2.ui.main.chat.*
import com.example.theonaui2.ui.main.speechbubble.ArrowAlignment
import com.example.theonaui2.ui.main.speechbubble.BubbleShadow
import com.example.theonaui2.ui.main.speechbubble.DefaultBubbleColor
import com.example.theonaui2.ui.main.speechbubble.rememberBubbleState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

@Composable
fun Chat() {
    val messages = remember { mutableStateListOf<ChatMessage>() }
    val sdf = remember { SimpleDateFormat("hh:mm", Locale.ROOT) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color(0xff81A1C1))
            .paint(
                painter = painterResource(id = R.drawable.chat_light),
                contentScale = ContentScale.Crop
            )
    ) {

        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        ChatAppbar("Theona")

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = scrollState,
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
        ) {
            items(messages) { message: ChatMessage ->

                // Remember random stats icon to not create at each recomposition
                val messageStatus = remember { MessageStatus.values()[Random.nextInt(3)] }

                // Toggle between sent and received message
                when (message.id.toInt() % 6) {
                    1 -> {
                        SentMessageRow(
                            drawArrow = true,
                            text = message.message,
                            messageTime = sdf.format(System.currentTimeMillis()),
                            messageStatus = messageStatus
                        )

                    }
                    2 -> {
                        SentMessageRow(
                            drawArrow = false,
                            text = message.message,
                            messageTime = sdf.format(System.currentTimeMillis()),
                            messageStatus = messageStatus
                        )

                    }
                    3 -> {
                        SentMessageRow(
                            drawArrow = false,
                            text = message.message,
                            messageTime = sdf.format(System.currentTimeMillis()),
                            messageStatus = messageStatus
                        )

                    }
                    4 -> {
                        ReceivedMessageRow(
                            drawArrow = true,
                            text = message.message,
                            messageTime = sdf.format(System.currentTimeMillis()),
                        )

                    }
                    5 -> {
                        ReceivedMessageRow(
                            drawArrow = false,
                            text = message.message,
                            messageTime = sdf.format(System.currentTimeMillis()),
                        )

                    }
                    else -> {
                        ReceivedMessageRow(
                            drawArrow = false,
                            text = message.message,
                            messageTime = sdf.format(System.currentTimeMillis()),
                        )
                    }
                }
            }
        }

        ChatInput(
            onMessageChange = { messageContent ->
                messages.add(
                    ChatMessage(
                        (messages.size + 1).toLong(),
                        messageContent,
                        System.currentTimeMillis()
                    )
                )

                coroutineScope.launch {
                    scrollState.animateScrollToItem(messages.size - 1)
                }

            }
        )
    }
}

@Composable
private fun SentMessageRow(
    drawArrow: Boolean = true,
    text: String,
    messageTime: String,
    messageStatus: MessageStatus
) {
    Column(
        modifier = Modifier
            .padding(start = 60.dp, end = 8.dp, top = if (drawArrow) 2.dp else 0.dp, bottom = 2.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.End
    ) {

        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = Color(0xffAAD6E2),//get accentColor from values/colors.xml
                alignment = ArrowAlignment.RightTop,
                cornerRadius = 16.dp,
                drawArrow = drawArrow,
                shadow = BubbleShadow(elevation = 1.dp),
                clickable = true
            )
        ) {
            ChatFlexBoxLayout(
                modifier = Modifier
                    .padding(start = 2.dp, top = 2.dp, end = 4.dp, bottom = 2.dp),
                text = text,
                messageStat = {
                    MessageTimeText(
                        modifier = Modifier.wrapContentSize(),
                        messageTime = messageTime,
                        messageStatus = messageStatus
                    )
                }
            )
        }
    }
}

@Composable
private fun ReceivedMessageRow(
    drawArrow: Boolean = true,
    text: String,
    messageTime: String
) {

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 60.dp, top = if (drawArrow) 2.dp else 0.dp, bottom = 2.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LeftTop,
                drawArrow = drawArrow,
                cornerRadius = 14.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                clickable = true
            )
        ) {
            ChatFlexBoxLayout(
                modifier = Modifier
                    .padding(start = 2.dp, top = 2.dp, end = 4.dp, bottom = 2.dp),
                text = text,
                messageStat = {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            modifier = Modifier.padding(top = 1.dp, bottom = 1.dp, end = 4.dp),
                            text = messageTime,
                            fontSize = 12.sp
                        )
                    }
                }
            )
        }
    }
}

enum class MessageStatus {
    PENDING, RECEIVED, READ
}

data class ChatMessage(
    val id: Long,
    var message: String,
    var date: Long
)


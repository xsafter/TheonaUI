package com.example.theonaui2.ui.main.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.theonaui2.ui.main.speechbubble.BubbleState
import com.example.theonaui2.ui.main.speechbubble.drawBubble
import com.example.theonaui2.ui.main.speechbubble.drawBubbleWithShape

@Composable
fun BubbleLayout(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {
    Column(
        modifier
            .drawBubble(bubbleState)
    ) {
        content()
    }
}

@Composable
fun BubbleLayoutWithShape(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {

    Column(
        modifier.drawBubbleWithShape(bubbleState)
    ) {
        content()
    }
}
package com.example.theonaui2.ui.main.chat

import android.content.Intent
import android.content.res.Configuration
import android.provider.MediaStore
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.theonaui2.ui.main.getPhotoFile
import java.util.Date

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ChatInput(modifier: Modifier = Modifier, onMessageChange: (String) -> Unit) {

    var input by remember { mutableStateOf(TextFieldValue("")) }
    val textEmpty: Boolean by remember {derivedStateOf { input.text.isEmpty() }}

    Row(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {

        ChatTextField(
            modifier = modifier.weight(1f),
            input = input,
            empty = textEmpty,
            onValueChange = {
                input = it
            }
        )

        Spacer(modifier = Modifier.width(6.dp))

        FloatingActionButton(
            modifier = Modifier.size(48.dp),
            backgroundColor = Color(0xff5E81AC),
            onClick = {
                if (!textEmpty) {
                    onMessageChange(input.text)
                    input = TextFieldValue("")
                }
            }
        ) {
            Icon(
                tint = Color.White,
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ChatTextField(
    modifier: Modifier = Modifier,
    input: TextFieldValue,
    empty: Boolean,
    onValueChange: (TextFieldValue) -> Unit
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colors.surface,
        elevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(2.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            val context = LocalContext.current
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                IndicatingIconButton(
//                    onClick = { /*TODO*/ },
//                    modifier = Modifier.then(Modifier.size(circleButtonSize)),
//                    indication = rememberRipple(bounded = false, radius = circleButtonSize / 2)
//                ) {
//                    Icon(imageVector = Icons.Default.Mood, contentDescription = "emoji")
//                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = circleButtonSize)
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {

                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        textStyle = TextStyle(
                            fontSize = 18.sp
                        ),
                        value = input,
                        onValueChange = onValueChange,
                        cursorBrush = SolidColor(Color(0xff5E81AC)),
                        decorationBox = { innerTextField ->
                            if (empty) {
                                Text("Message", fontSize = 18.sp)
                            }
                            innerTextField()
                        }
                    )
                }

                IndicatingIconButton(
                    onClick = {
                              //intent to open gallery
                              //result is still unhendled

                    },
                    modifier = Modifier.then(Modifier.size(circleButtonSize)),
                    indication = rememberRipple(bounded = false, radius = circleButtonSize / 2)
                ) {
                    Icon(
                        modifier = Modifier.rotate(-45f),
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = "attach"
                    )
                }
                AnimatedVisibility(visible = empty) {
                    IndicatingIconButton(
                        onClick = {
                            if (checkCameraPermission(context)) {
                                val FILE_NAME = "photo.jpg"
                                val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                val filePhoto = getPhotoFile(FILE_NAME, context)
                                val providerFile = FileProvider.getUriForFile(
                                    context,
                                    "com.example.theonaui2.ui.main",
                                    filePhoto
                                )
                                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
                            } else {
                                grantCameraPermission(context)
                            }
                        },
                        modifier = Modifier.then(Modifier.size(circleButtonSize)),
                        indication = rememberRipple(bounded = false, radius = circleButtonSize / 2)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = "camera"
                        )
                    }
                }
            }
        }
    }
}

val circleButtonSize = 44.dp

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun IndicatingIconButtonPreview() {
    IndicatingIconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Filled.CameraAlt,
            contentDescription = "camera"
        )
    }
}

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun ChatInputPreview() {
    ChatInput() {}
}

//private fun openCamera(context: Context) {
//    val values = ContentValues()
//    values.put(MediaStore.Images.Media.TITLE, "New Picture")
//    values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
//
//    //camera intent
//    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//    // set filename
//    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//    vFilename = "PHOTO_" + timeStamp + ".jpg"
//
//    // set direcory folder
//    val file = File("/sdcard/niabsen/", vFilename);
//    val image_uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
//
//    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
//    startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
//}

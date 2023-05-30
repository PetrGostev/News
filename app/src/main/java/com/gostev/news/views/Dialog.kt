package com.gostev.news.views

import android.view.Gravity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.gostev.news.R


@Composable
fun MyDialog(
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit,
    title: String,
    text: String,
    textButton: String,
) {
    var isShowDialog by rememberSaveable {
        mutableStateOf(true)
    }
    if (isShowDialog) {
        Dialog(
            onDismissRequest = {
                onDismiss.invoke()
                isShowDialog = false
            },
        ) {
            val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
            dialogWindowProvider.window.setGravity(Gravity.BOTTOM)

            Card(
                modifier = Modifier.padding(bottom = 80.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = text,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )

                    Divider(color = colorResource(id = R.color.teal_700), thickness = 1.dp)

                    TextButton(
                        onClick = {
                            onPositiveClick.invoke()
                            isShowDialog = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                    ) {
                        Text(
                            text = textButton,
                            color = colorResource(id = R.color.purple_200),
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}
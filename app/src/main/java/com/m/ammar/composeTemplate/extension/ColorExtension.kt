package com.m.ammar.composeTemplate.extension

import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

fun String.toClr() = Color(android.graphics.Color.parseColor(this))

@Composable
fun @receiver:ColorRes Int.toClr(): Color {
    return colorResource(id = this)
}

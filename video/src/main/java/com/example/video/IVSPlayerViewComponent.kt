package com.example.video

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.amazonaws.ivs.player.Player
import com.amazonaws.ivs.player.PlayerView

@Composable
fun IVSPlayerViewComponent(streamUrl: String) {
    val context = LocalContext.current
    val player = remember { Player.Factory.create(context)}

    AndroidView(factory = {
        val playerView = PlayerView(context)
        player.load(Uri.parse(streamUrl))
        player.play()
        playerView
    })
}

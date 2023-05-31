package com.example.video

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.amazonaws.ivs.player.Player
import com.amazonaws.ivs.player.PlayerView

@Composable
fun IVSPlayerViewComponent(streamUrl: String) {
    val context = LocalContext.current
    var player : Player

    AndroidView(modifier = Modifier.fillMaxSize(), factory = {

        // IVS Player örneğini oluşturun
        player = Player.Factory.create(context)

        // PlayerView'ı oluşturun
        val playerViewv = PlayerView(context).apply {
            player = this.player
        }

        // IVS canlı yayınını oynatmak için oynatma URL'sini set edin
        player.load(Uri.parse(streamUrl))

        // PlayerView'ı döndürün
        playerViewv
    })

}



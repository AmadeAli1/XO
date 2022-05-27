package com.example.xoplayer

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xoplayer.game.XO
import com.example.xoplayer.game.GameLogic
import com.example.xoplayer.game.GamePlayer
import com.example.xoplayer.game.GameType

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun GameTable() {
    val state = rememberLazyListState()
    val gameState = GameLogic()

    LazyVerticalGrid(cells = GridCells.Fixed(3),
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
            .background(color = Color.DarkGray),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy((-80).dp),
        state = state,
        contentPadding = PaddingValues(0.dp),
        content = {
            items(items = gameState.tableState.toSortedMap().toList()) { data ->
                Container(gameConfig = data, gameState)
            }
        })
}


@Composable
fun Container(
    gameConfig: Pair<String, GameType>, state: GameLogic
) {

    Button(
        onClick = {
            state.add(
                GamePlayer("Amade",GameType.X),
                row = gameConfig.first.substring(0, 1).toInt(),
                column = gameConfig.first.substring(1, 2).toInt()
            )
        },
        modifier = Modifier
            .height(80.dp)
            .widthIn(80.dp)
            .wrapContentWidth(align = Alignment.CenterHorizontally),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
    ) {
        Text(text = gameConfig.second.name)
    }
}

fun onClick(function: (Int, Int) -> Unit) {
    TODO("Not yet implemented")
}

package com.example.xoplayer.game

data class XO(
    var row: Int,
    var column: Int,
    var type: GameType = GameType.Empty
)

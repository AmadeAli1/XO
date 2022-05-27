package com.example.xoplayer.game

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap

class GameLogic {
    private val table = Array(3) {
        arrayOfNulls<GameType>(3)
    }
    private var size = 0
    var tableState: SnapshotStateMap<String, GameType>
    var gameState: MutableState<Boolean> = mutableStateOf(false)

    private fun startGame() = mutableStateMapOf<String, GameType>().apply {
        put("00", GameType.Empty)
        put("01", GameType.Empty)
        put("02", GameType.Empty)
        put("10", GameType.Empty)
        put("11", GameType.Empty)
        put("12", GameType.Empty)
        put("20", GameType.Empty)
        put("21", GameType.Empty)
        put("22", GameType.Empty)
    }

    private fun startTable() {
        for (i in 0..2) {
            for (j in 0..2) {
                table[i][j] = GameType.Empty
            }
        }
    }


    init {
        tableState = startGame()
        startTable()
    }

    fun add(player: GamePlayer, row: Int, column: Int) {
        require(table[row][column] == GameType.O || table[row][column] != GameType.X) {
            "Posicao ja tem um elemento"
        }

        table[row][column] = player.type
        val block: (SnapshotStateMap<String, GameType>) -> Unit = {
            it["${row}${column}"] = player.type
        }
        tableState = tableState.apply(block)

//        println(
//            "Player: " + player + "  Dado:" + player.type.name + " [%d][%d]".format(
//                row,
//                column
//            )
//        )
        val verify = verify(player.type, row, column)

        if (verify) {
            println("Vencedor:{" + player.username + "} Dado:{" + player.type.name + "}")
            resetGame()
            return
        }
        size++
        if (isFull()) {
            println("Nao existe um vencedor")
            resetGame()
        }

    }

    private fun resetGame(){
        tableState = startGame()
        size=0
        startTable()

    }

    private fun isFull(): Boolean {
        return size == 9
    }

    private fun verify(input: GameType, row: Int, column: Int): Boolean {
        var state = verifyRow(input, row)
        if (!state) {
            state = verifyColumn(input, column)
        }
        if (!state) {
            state = verifyDiagonalPrincipal(input)
        }
        if (!state) {
            state = verifyDiagonalSecundaria(input)
        }
        return state
    }

    private fun verifyDiagonalSecundaria(input: GameType): Boolean {
        var state = true
        for (i in 0..2) {
            for (j in 0..2) {
                if (i + j == 3 - 1) {
                    if (table[i][j] != input) {
                        state = false
                        break
                    }
                }
            }
        }
        return state
    }

    private fun verifyDiagonalPrincipal(input: GameType): Boolean {
        var state = true
        for (i in 0..2) {
            for (j in 0..2) {
                if (i == j) {
                    if (table[i][j] != input) {
                        state = false
                        break
                    }
                }
            }
        }
        return state
    }

    private fun verifyRow(input: GameType, row: Int): Boolean {
        var state = true
        for (j in 0..2) {
            val data = table[row][j]
            if (data != input) {
                state = false
                break
            }
        }
        return state
    }

    private fun verifyColumn(input: GameType, column: Int): Boolean {
        var state = true
        for (i in 0..2) {
            val data = table[i][column]
            if (data != input) {
                state = false
                break
            }
        }
        return state
    }


}

fun main() {
    val x = mutableStateMapOf<String, GameType>().apply {
        put("00", GameType.Empty)
        put("01", GameType.Empty)
        put("02", GameType.Empty)
        put("10", GameType.Empty)
        put("11", GameType.Empty)
        put("12", GameType.Empty)
        put("20", GameType.Empty)
        put("21", GameType.Empty)
        put("22", GameType.Empty)
    }

    for (xs in x.toSortedMap()) {
        println(xs.key)
    }
}
package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var board:Array<Array<Button>>
    var PLAYER = true
    var turn_count = 0

    var Board_Status= Array(3){ IntArray(3) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for (i in board){
            for(button1 in i){
                button1.setOnClickListener(this)
            }
        }
        InitializeBoardStatus()

        reset.setOnClickListener {
            turn_count = 0
            PLAYER = true
            updateDisplay("Player X turn")
            InitializeBoardStatus()
        }


    }

    private fun InitializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                Board_Status[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button1->{
            updateValue(row=0, col=0,player = PLAYER)
            }
            R.id.button2->{
                updateValue(row=0, col=1,player = PLAYER)
            }
            R.id.button3->{
                updateValue(row=0, col=2,player = PLAYER)
            }
            R.id.button4->{
                updateValue(row=1, col=0,player = PLAYER)
            }
            R.id.button5->{
                updateValue(row=1, col=1,player = PLAYER)
            }
            R.id.button6->{
                updateValue(row=1, col=2,player = PLAYER)
            }
            R.id.button7->{
                updateValue(row=2, col=0,player = PLAYER)
            }
            R.id.button8->{
                updateValue(row=2, col=1,player = PLAYER)
            }
            R.id.button9->{
                updateValue(row=2, col=2,player = PLAYER)
            }
        }
        PLAYER = !PLAYER
        turn_count++
        if(PLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O Turn")
        }

        if(turn_count==9){
            updateDisplay("Game Draw")
        }

        CheckWinner()
    }

    private fun CheckWinner() {
       for(i in 0..2){
           if(Board_Status[i][0] == Board_Status[i][1] && Board_Status[i][1] == Board_Status[i][2]){
               if(Board_Status[i][0] == 1){
                   updateDisplay("Player X is Winner")
                   break
               }
               else if (Board_Status[i][0] == 0){
                   updateDisplay("Player O is Winner")
                   break
               }
           }
       }
        for(i in 0..2){
            if(Board_Status[0][i] == Board_Status[1][i] && Board_Status[1][i] == Board_Status[2][i]){
                if(Board_Status[0][i] == 1){
                    updateDisplay("Player X is Winner")
                    break
                }
                else if (Board_Status[0][i] == 0){
                    updateDisplay("Player O is Winner")
                    break
                }
            }
        }

        if(Board_Status[0][0] == Board_Status[1][1] && Board_Status[1][1] == Board_Status[2][2]){
            if(Board_Status[0][0] == 1){
                updateDisplay("Player X is Winner")

            }
            else if (Board_Status[0][0] == 0){
                updateDisplay("Player O is Winner")

            }
        }
        if(Board_Status[0][2] == Board_Status[1][1] && Board_Status[1][1] == Board_Status[2][0]){
            if(Board_Status[0][2] == 1){
                updateDisplay("Player X is Winner")

            }
            else if (Board_Status[0][2] == 0){
                updateDisplay("Player O is Winner")

            }
        }

    }

    private fun updateDisplay(text: String) {
        DisplayTv.text = text
        if(text.contains("Winner")){
            disableButton()
        }
    }

    private fun disableButton() {
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled = false
            }
        }
    }


    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
            Board_Status[row][col] = value
        }
    }
}
package com.example.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView headerText;

    int PLAYER_o = 0;
    int PLAYER_x = 1;

    int activePlayer = PLAYER_o;

    int[] filledPas = {-1, -1, -1, -1, -1, -1, -1, -1, -1}; // To'g'ri massivni yaratish

    boolean isGameActive = true; // O'yin faolligini belgilash

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        headerText = findViewById(R.id.header_text);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isGameActive)
            return;

        Button clickedBtn = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if (filledPas[clickedTag] != -1)
            return;

        filledPas[clickedTag] = activePlayer;

        if (activePlayer == PLAYER_o) {
            clickedBtn.setText("o");
            clickedBtn.setBackground(ContextCompat.getDrawable(this, android.R.color.holo_blue_bright)); // getDrawable() o'rnida ContextCompat.getDrawable
            activePlayer = PLAYER_x;
            headerText.setText("x turn");
        } else {
            clickedBtn.setText("x");
            clickedBtn.setBackground(ContextCompat.getDrawable(this, android.R.color.holo_orange_light));
            activePlayer = PLAYER_o;
            headerText.setText("o turn");
        }

        checkForWin();
    }

    private void checkForWin() {
        int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {3, 4, 6}};

        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if (filledPas[val0] == filledPas[val1] && filledPas[val1] == filledPas[val2]) { // to'g'ri tekshirish
                if (filledPas[val0] != -1) {
                    isGameActive = false;

                    if (filledPas[val0] == PLAYER_o)
                        showDialog("o is the winner");
                    else
                        showDialog("x is the winner");
                }
            }
        }
    }

    private void showDialog(String winnerText) {
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();
    }

    private void restartGame() {
        activePlayer = PLAYER_o;
        filledPas = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn1.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn2.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn3.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn4.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn5.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn6.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn7.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        btn8.setBackground(ContextCompat.getDrawable(this, android.R.color.darker_gray));
        isGameActive = true;
    }
}
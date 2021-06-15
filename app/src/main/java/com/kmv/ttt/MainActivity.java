package com.kmv.ttt;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        boolean gameActive = true;
        // Player representation
        // 0 - X
        // 1 - O
        int activePlayer = 0;
        int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
        //    State meanings:
        //    0 - X
        //    1 - O
        //    2 - Null
        int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6}};
        //For counting no. of tap
         int count=0;
         public void playerTap(View view){

            ImageView img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());
            if(!gameActive){
                resetGame(view);
            }
            if(gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("O's Turn - Tap to play");
                    count++;
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("X's Turn - Tap to play");
                   count++;
                }
                if(count==9){
                    TextView status = findViewById(R.id.status);
                    //status.animate().translationYBy(1000f).setDuration(200);
                    status.setText("Match Drawn");
                    count=0;
                }
               // count=0;
                img.animate().translationYBy(1000f).setDuration(100);

            }
            // Check if any player has won
            for(int[] winPosition: winPositions){
                if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]]!=2){
                    // Somebody has won! - Find out who!
                    String winnerStr;
                    gameActive = false;
                    if(gameState[winPosition[0]] == 0){
                        winnerStr = "X has won";
                        count=0;
                    }
                    else {
                        winnerStr = "O has won";
                        count=0;
                    }
                    // Update the status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                   // gameReset(view);
                }
            }
        }

    /*    public void gameReset(View view) {
            gameActive = true;
            activePlayer = 0;
            for(int i=0; i<gameState.length; i++){
                gameState[i] = 2;
            }
            ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

            TextView status = findViewById(R.id.status);
            status.setText("X's Turn - Tap to play");
        }*/

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.title_bar);
        }

    @Override
    public void onClick(View v) {

    }

    public void resetGame(View view) {
            gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
}

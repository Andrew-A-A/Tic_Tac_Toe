package com.jack.sloto.tictactoe;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.jack.sloto.tictactoe.databinding.ActivityMainBinding;


public class GameActivity extends AppCompatActivity {

protected ActivityMainBinding binding;
Game game=new Game();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Add click listener for each cell in 3x3 game grid
        setCellsClickListener();

        //Set click event for the restart button
        binding.floatingActionButton.setOnClickListener(view ->{
            restartGame();
            setCellsClickListener();
        });
    }

    //Add click listener for each cell in 3x3 game grid
    private void setCellsClickListener() {
        binding.cell00.setOnClickListener(view -> cellClicked((ImageView) view,0));
        binding.cell01.setOnClickListener(view -> cellClicked((ImageView) view,1));
        binding.cell02.setOnClickListener(view -> cellClicked((ImageView) view,2));
        binding.cell10.setOnClickListener(view -> cellClicked((ImageView) view,3));
        binding.cell11.setOnClickListener(view -> cellClicked((ImageView) view,4));
        binding.cell12.setOnClickListener(view -> cellClicked((ImageView) view,5));
        binding.cell20.setOnClickListener(view -> cellClicked((ImageView) view,6));
        binding.cell21.setOnClickListener(view -> cellClicked((ImageView) view,7));
        binding.cell22.setOnClickListener(view ->cellClicked((ImageView) view,8));
    }

    //Adds player symbol (X/O) to the grid if it's empty
    void cellClicked( ImageView cell,int cellIndex){
        //Check if cell is not empty
        if (cell.getDrawable()!=null) return;
        //Set icon according to current player turn
        cell.setImageDrawable(
                game.getIsCircle()? AppCompatResources.getDrawable(this,R.drawable.o_icon)
                :AppCompatResources.getDrawable(this,R.drawable.x_icon)
        );
        //Set the added symbol in the 2D array of the game
       game.playDone(cellIndex);

       //Check if current player win of the game is draw
       char winnerSymbol= game.isWin();
       if (winnerSymbol!=Game.empty||game.isDraw())
        endGame(winnerSymbol);
    }

    //Create alert dialog tells the player if it's a win or draw
    private void endGame(char winner) {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            SetDialogBuilder(winner, builder);
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
    }

    //Set alert dialog message depending on player win or draw
    private void SetDialogBuilder(char winner, AlertDialog.Builder builder) {
        builder.setMessage("Do you like to restart the game ?");
        if (winner != Game.empty) {
            builder.setIcon(winner == 'X' ? R.drawable.x_icon : R.drawable.o_icon);
            builder.setTitle("Player win");
        }
        else{
            builder.setIcon(R.mipmap.ic_launcher_round);
            builder.setTitle("Game draw");
        }
        builder.setCancelable(false);
        //Set alert box buttons
        builder.setPositiveButton("Restart", (dialog, which) -> restartGame());
        builder.setNegativeButton("No", (dialog, which) -> {
            binding.cell00.setOnClickListener(null);
            binding.cell01.setOnClickListener(null);
            binding.cell02.setOnClickListener(null);
            binding.cell10.setOnClickListener(null);
            binding.cell11.setOnClickListener(null);
            binding.cell12.setOnClickListener(null);
            binding.cell20.setOnClickListener(null);
            binding.cell21.setOnClickListener(null);
            binding.cell22.setOnClickListener(null);
            dialog.cancel();
        });
    }

    //Reset the 3x3 grid and 2D array
    void restartGame(){
        binding.cell00.setImageResource(0);
        binding.cell01.setImageResource(0);
        binding.cell02.setImageResource(0);
        binding.cell10.setImageResource(0);
        binding.cell11.setImageResource(0);
        binding.cell12.setImageResource(0);
        binding.cell20.setImageResource(0);
        binding.cell21.setImageResource(0);
        binding.cell22.setImageResource(0);
        game=new Game();
    }
}
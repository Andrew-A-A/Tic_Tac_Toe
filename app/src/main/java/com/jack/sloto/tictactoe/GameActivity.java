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
        setCellsClicklistner();
        binding.floatingActionButton.setOnClickListener(view ->{
            restartGame();
            setCellsClicklistner();
        });
    }

    private void setCellsClicklistner() {
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

    void cellClicked( ImageView cell,int cellIndex){
        if (cell.getDrawable()!=null) return;
        cell.setImageDrawable(
                game.getIsCircle()? AppCompatResources.getDrawable(this,R.drawable.outline_circle_24)
                :AppCompatResources.getDrawable(this,R.drawable.outline_close_24)
        );
       game.playDone(cellIndex);
       char winnedSymbol= game.isWin();
       if (winnedSymbol!=Game.empty||game.isDraw())
        endGame(winnedSymbol);
    }

    private void endGame(char winnedSymbol) {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Do you like to restart the game ?");
        if (winnedSymbol != Game.empty) {
            builder.setIcon(winnedSymbol == 'X' ? R.drawable.outline_close_24 : R.drawable.outline_circle_24);
            builder.setTitle("Player win");
        }
        else{
            builder.setIcon(R.drawable.ic_launcher_foreground);
            builder.setTitle("Game draw");
        }
            builder.setCancelable(false);
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
                // If user click no then dialog box is canceled.
                dialog.cancel();
            });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
    }

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
package com.jack.sloto.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.os.Bundle;
import android.widget.ImageView;

import com.jack.sloto.tictactoe.databinding.ActivityMainBinding;


public class GameActivity extends AppCompatActivity {
protected ActivityMainBinding binding;
private Boolean isCircle=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cell00.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell01.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell02.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell10.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell11.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell12.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell20.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell21.setOnClickListener(view -> cellClicked((ImageView) view));
        binding.cell22.setOnClickListener(view ->cellClicked((ImageView) view));
    }

    void cellClicked( ImageView cell){
        if (cell.getDrawable()!=null) return;
        cell.setImageDrawable(
                isCircle? AppCompatResources.getDrawable(this,R.drawable.outline_circle_24)
                :AppCompatResources.getDrawable(this,R.drawable.outline_close_24)
        );
        isCircle= !isCircle;
    }
}
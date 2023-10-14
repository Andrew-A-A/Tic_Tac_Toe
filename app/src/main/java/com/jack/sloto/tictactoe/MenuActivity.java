package com.jack.sloto.tictactoe;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jack.sloto.tictactoe.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    ActivityMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.playButton.setOnClickListener(view ->{
            Intent intent=new Intent(MenuActivity.this,GameActivity.class);
            startActivity(intent);
        });
        binding.quitButton.setOnClickListener(view -> finish());
    }
}
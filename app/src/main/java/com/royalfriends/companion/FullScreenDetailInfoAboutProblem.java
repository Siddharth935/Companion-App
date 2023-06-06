package com.royalfriends.companion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.royalfriends.companion.databinding.ActivityFullScreenDetailInfoAboutProblemBinding;

public class FullScreenDetailInfoAboutProblem extends AppCompatActivity {

    private ActivityFullScreenDetailInfoAboutProblemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullScreenDetailInfoAboutProblemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarHead.setText(getIntent().getStringExtra("problemName").toString());
        binding.problem.setText(getIntent().getStringExtra("problem").toString());
        binding.solution.setText(getIntent().getStringExtra("solution").toString());
        binding.Defination.setText(getIntent().getStringExtra("Defination").toString());
    }
}
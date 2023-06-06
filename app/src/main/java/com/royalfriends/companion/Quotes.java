package com.royalfriends.companion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.royalfriends.companion.QuotesActivity.Angry;
import com.royalfriends.companion.QuotesActivity.Happy;
import com.royalfriends.companion.QuotesActivity.laughing;
import com.royalfriends.companion.QuotesActivity.sad;
import com.royalfriends.companion.databinding.ActivityQuotesBinding;

public class Quotes extends AppCompatActivity {

    private ActivityQuotesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quotes.this, Angry.class);
                startActivity(intent);
            }
        });binding.sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quotes.this, sad.class);
                startActivity(intent);
            }
        });binding.happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quotes.this, Happy.class);
                startActivity(intent);
            }
        });binding.lauging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quotes.this, laughing.class);
                startActivity(intent);
            }
        });
    }
}
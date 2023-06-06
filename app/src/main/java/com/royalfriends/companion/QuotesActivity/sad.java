package com.royalfriends.companion.QuotesActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.royalfriends.companion.Adaptors.Adaptor;
import com.royalfriends.companion.QuotesModel;
import com.royalfriends.companion.R;
import com.royalfriends.companion.databinding.ActivitySadBinding;

import java.util.ArrayList;

public class sad extends AppCompatActivity {
    private DatabaseReference reference;
    private Adaptor adaptor;
    private ArrayList<QuotesModel> list;
    private ActivitySadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference("Sad");
        binding.sad.setLayoutManager(new LinearLayoutManager(sad.this));
        binding.shimmer.startShimmer();

        list = new ArrayList<>();
        adaptor = new Adaptor(sad.this, list);
        binding.sad.setAdapter(adaptor);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.shimmer.stopShimmer();
                binding.shimmer.setVisibility(View.GONE);
                binding.sad.setVisibility(View.VISIBLE);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    QuotesModel newsModel = dataSnapshot.getValue(QuotesModel.class);
                    list.add(newsModel);
                }
                adaptor.notifyItemInserted(list.size() - 1);
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
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
import com.royalfriends.companion.databinding.ActivityLaughingBinding;

import java.util.ArrayList;

public class laughing extends AppCompatActivity {
    private DatabaseReference reference;
    private Adaptor adaptor;
    private ArrayList<QuotesModel> list;
    private ActivityLaughingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaughingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        reference = FirebaseDatabase.getInstance().getReference("Laughing");
        binding.laughing.setLayoutManager(new LinearLayoutManager(laughing.this));
        binding.shimmer.startShimmer();

        list = new ArrayList<>();
        adaptor = new Adaptor(laughing.this, list);
        binding.laughing.setAdapter(adaptor);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.shimmer.stopShimmer();
                binding.shimmer.setVisibility(View.GONE);
                binding.laughing.setVisibility(View.VISIBLE);
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
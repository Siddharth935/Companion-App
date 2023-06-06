package com.royalfriends.companion.Mainfrgament;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.royalfriends.companion.LoginActivity;
import com.royalfriends.companion.ProfileActivity;
import com.royalfriends.companion.Quotes;
import com.royalfriends.companion.R;
import com.royalfriends.companion.databinding.FragmentHomeBinding;

import java.util.Objects;

public class Home extends Fragment {

    private FragmentHomeBinding binding;
    private DatabaseReference reference;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        getProfileData();

        binding.Hii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isvisible = binding.showLayout.getVisibility();
                if (isvisible == View.VISIBLE) {
                    binding.showLayout.setVisibility(View.GONE);
                } else {
                    binding.showLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isvisible = binding.showLayout.getVisibility();
                if (isvisible == View.VISIBLE) {
                    binding.showLayout.setVisibility(View.GONE);
//                    HI.setText("Hiiii");
                } else {
//                    HI.setText("Bye");
                    binding.showLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        binding.quetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Quotes.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }

    private void logout() {
        new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.baseline_warning_24)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout this app")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void getProfileData() {
        reference = FirebaseDatabase.getInstance().getReference("UserQuotes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String getfarmerName = snapshot.child("name").getValue().toString();
                    binding.name.setText(getfarmerName);
                    String profileImage = snapshot.child("image").getValue().toString();
                    Glide.with(getApplicationContext()).load(profileImage).into(binding.profileImageView);
                    String getfarmerMobileNumber = snapshot.child("mobileNumber").getValue().toString();
                    binding.mobilenumber.setText(getfarmerMobileNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
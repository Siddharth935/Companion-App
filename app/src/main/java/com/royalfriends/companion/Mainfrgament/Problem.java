package com.royalfriends.companion.Mainfrgament;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.royalfriends.companion.Adaptors.Problem_solutionadapter;
import com.royalfriends.companion.ProblemSolutionModel;
import com.royalfriends.companion.R;

import java.util.ArrayList;

public class Problem extends Fragment {
    private DatabaseReference reference;
    private Problem_solutionadapter problem_solutionadapter;
    private ArrayList<ProblemSolutionModel> list;
    private ProgressBar progressBar;
    public Problem() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_profile,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.upcomingRecycler);
        progressBar = view.findViewById(R.id.upcomingProgress);

        reference = FirebaseDatabase.getInstance().getReference("ProblemSolution");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        problem_solutionadapter = new Problem_solutionadapter(getContext(), list);
        recyclerView.setAdapter(problem_solutionadapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProblemSolutionModel problemSolutionModel = dataSnapshot.getValue(ProblemSolutionModel.class);
                    list.add(problemSolutionModel);
                }
                problem_solutionadapter.notifyItemInserted(list.size() - 1);
                problem_solutionadapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
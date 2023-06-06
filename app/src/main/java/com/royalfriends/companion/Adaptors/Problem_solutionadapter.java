package com.royalfriends.companion.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.royalfriends.companion.FullScreenDetailInfoAboutProblem;
import com.royalfriends.companion.ProblemSolutionModel;
import com.royalfriends.companion.R;

import java.util.ArrayList;

public class Problem_solutionadapter extends RecyclerView.Adapter<Problem_solutionadapter.MyViewHolder>{
    Context context;
    ArrayList<ProblemSolutionModel> problemSolutionModels;

    public Problem_solutionadapter(Context context, ArrayList<ProblemSolutionModel> problemSolutionModels) {
        this.context = context;
        this.problemSolutionModels = problemSolutionModels;
    }

    @NonNull
    @Override
    public Problem_solutionadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.problem_solution, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Problem_solutionadapter.MyViewHolder holder, int position) {
        ProblemSolutionModel problemSolutionModel = problemSolutionModels.get(position);
        holder.problem.setText(problemSolutionModel.getProblem());
        holder.solution.setText(problemSolutionModel.getSolution());
        holder.problemName.setText(problemSolutionModel.getProblemName());
        holder.defination.setText(problemSolutionModel.getDefination());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullScreenDetailInfoAboutProblem.class);
                intent.putExtra("problem",problemSolutionModel.getProblem());
                intent.putExtra("problemName",problemSolutionModel.getProblemName());
                intent.putExtra("solution",problemSolutionModel.getSolution());
                intent.putExtra("Defination",problemSolutionModel.getDefination());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return problemSolutionModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView problem, solution, problemName,defination;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            problem = itemView.findViewById(R.id.problem);
            solution = itemView.findViewById(R.id.solution);
            problemName = itemView.findViewById(R.id.problem_name);
            defination = itemView.findViewById(R.id.Defination);
            imageView = itemView.findViewById(R.id.clickToView);

        }
    }
}

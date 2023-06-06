package com.royalfriends.companion.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.royalfriends.companion.QuotesModel;
import com.royalfriends.companion.R;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Adaptor.MyViewHolder> {
    Context context;
    ArrayList<QuotesModel> quotesModelslist;

    public Adaptor(Context context, ArrayList<QuotesModel> quotesModelslist) {
        this.context = context;
        this.quotesModelslist = quotesModelslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_quotes_templete, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuotesModel quotesModel = quotesModelslist.get(position);
        holder.Quotes.setText(quotesModel.getQuotes());
    }

    @Override
    public int getItemCount() {
        return quotesModelslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Quotes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Quotes = itemView.findViewById(R.id.quotes);

        }
    }

}

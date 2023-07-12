package com.example.testapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.Listeners.RecipeClickListener;
import com.example.testapp.Models.Recipe;
import com.example.testapp.R;
import com.example.testapp.Models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PopularRecipeAdapter extends RecyclerView.Adapter<PopularRecipeViewHolder> {
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;
    private OnClickListener onClickListener;


    public PopularRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PopularRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_recipes, parent, false);
        return new PopularRecipeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PopularRecipeViewHolder holder, int position) {
        Recipe recipe = list.get(position);
        holder.food_name_popular.setText(list.get(position).title);
        holder.food_name_popular.setSelected(true);
        Picasso.get().load(list.get(position).image).into(holder.food_image_popular);
        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, recipe);
            }
        });
        holder.popular_Recipe_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Recipe recipe);
    }

}

class PopularRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView popular_Recipe_Card;
    ImageView food_image_popular;
    TextView food_name_popular;

    public PopularRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        popular_Recipe_Card = itemView.findViewById(R.id.popular_Recipe_Card);
        food_image_popular = itemView.findViewById(R.id.food_image_popular);
        food_name_popular = itemView.findViewById(R.id.food_name_popular);

    }


}
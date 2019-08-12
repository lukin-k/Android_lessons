package com.example.pizzarecipes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PizzaRecipesAdapter extends RecyclerView.Adapter<PizzaRecipesAdapter.PizzaRecipesViewHolder> {
    Context context;

    public PizzaRecipesAdapter(Context context) {
        this.context = context;
    }

    class PizzaRecipesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageViewPizza;
        public TextView textViewTitle;
        public TextView textViewDiscription;

        public PizzaRecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imageViewPizza=itemView.findViewById(R.id.ImageViewPizza);
            textViewTitle=itemView.findViewById(R.id.TextViewTitle);
            textViewDiscription=itemView.findViewById(R.id.TextViewDescription);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, RecipeActivity.class);
            intent.putExtra("position", getAdapterPosition());
//            Toast.makeText(context,getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public PizzaRecipesAdapter.PizzaRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pizza_recipes_item, viewGroup, false);
        PizzaRecipesViewHolder pizzaRecipesViewHolder=new PizzaRecipesViewHolder(view);
        return pizzaRecipesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaRecipesViewHolder pizzaRecipesViewHolder, int i) {
        pizzaRecipesViewHolder.imageViewPizza.setImageResource(Constants.IMAGE_RESOURSES[i]);
        pizzaRecipesViewHolder.textViewTitle.setText(Constants.TITLES[i]);
        pizzaRecipesViewHolder.textViewDiscription.setText(Constants.DESCRIPTOINS[i]);
    }


    @Override
    public int getItemCount() {
        return Constants.IMAGE_RESOURSES.length;
    }
}

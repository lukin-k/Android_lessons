package com.example.pizzarecipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {
    TextView textViewTitle;
    TextView textViewRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        textViewTitle=findViewById(R.id.TextViewTitle2);
        textViewRecipe=findViewById(R.id.TextViewRecipe);

        int pos=getIntent().getIntExtra("position", 0);
        textViewTitle.setText(Constants.TITLES[pos]);

        String tmp="";
        for (int i=0; i<Constants.RECIPES[pos].length; ++i){
            if(i!=0)
                tmp+="\n\n";
            tmp+=(i+1)+". "+Constants.RECIPES[pos][i];
        }
        textViewRecipe.setText(tmp);
//        TextView textView=findViewById(R.id.KOKOK);
//        Intent intent=getIntent();
//        Toast.makeText(getApplicationContext(),getIntent().getIntExtra("position", 0)+"", Toast.LENGTH_SHORT).show();

//        textView.setText(getIntent().getIntExtra("position", 0)+"");

    }
}

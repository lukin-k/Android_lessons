package com.example.musicshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

	int current_quantity=0;
	int current_prise=0;
	String GoodName="";
	TextView text_current_quantity;
	TextView text_current_price;
	TextView edit_text_your_email;
	Spinner spinner_Select_item;
//	ArrayList items;
	int[] prices={500, 1500, 1000};
//	ArrayAdapter spiner_adapter;

	ImageView image;
//	HashMap images;
	int[] images={R.drawable.pokemon1, R.drawable.pokemon2, R.drawable.pokemon3};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit_text_your_email = findViewById(R.id.editText_Your_Email);
		text_current_quantity = findViewById(R.id.textView_Current_quantity);
		text_current_price = findViewById(R.id.textView_Current_price);

        image = findViewById(R.id.imageView);
        spinner_Select_item = findViewById(R.id.spinner_Select_item);
//		spinner_Select_item.setOnItemSelectedListener(this);
		/*items=new ArrayList();
		items.add("1");
		items.add("2");
		items.add("3");*/

		/*prices = new HashMap();
		prices.put("1", 500);
		prices.put("2", 1500);
		prices.put("3", 1000);*/

        ArrayAdapter  spiner_adapter=ArrayAdapter.createFromResource(this, R.array.list_instruments, android.R.layout.simple_spinner_item);
        spiner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spiner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Select_item.setAdapter(spiner_adapter);
        spinner_Select_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                GoodName = spinner_Select_item.getSelectedItem().toString();
//                int tmp_index=spinner_Select_item.getSelectedItemPosition();
//                current_prise = prices[tmp_index];
                current_prise = prices[selectedItemPosition];
                text_current_price.setText(current_prise*current_quantity+" $");

//                image.setImageResource(images[tmp_index]);
                image.setImageResource(images[selectedItemPosition]);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

		/*images = new HashMap();
		images.put("1", R.drawable.pokemon1);
		images.put("2", R.drawable.pokemon2);
		images.put("3", R.drawable.pokemon3);*/
	}

	public void inc_quantity(View view) {
		++current_quantity;
		text_current_quantity.setText(""+current_quantity);
		text_current_price.setText(current_prise*current_quantity+" $");
	}

	public void dec_quantity(View view) {
		if(current_quantity>0)
			--current_quantity;
		text_current_quantity.setText(""+current_quantity);
		text_current_price.setText(current_prise*current_quantity+" $");
	}

	/*@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		GoodName = spinner_Select_item.getSelectedItem().toString();
//		spinner_Select_item.getSelectedItemPosition();
		int tmp_index=spinner_Select_item.getSelectedItemPosition();
		current_prise = prices[tmp_index];
		text_current_price.setText(current_prise*current_quantity+" $");

		image.setImageResource(images[tmp_index]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}*/

	public void Check_order(View view) {
		Order order = new Order(edit_text_your_email.getText().toString(), GoodName, current_quantity, current_prise);
		if(order.UserEmail==""||order.UserEmail.indexOf('@')==-1) {
//            Toast toast = Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_SHORT);
//            toast.show();
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
        }else {
//        Log.d("test class Order", order.UserEmail+" "+order.GoodName+" "+order.quantity+" "+order.price);
            Intent orderIntent = new Intent(this, OrderActivity.class);
            orderIntent.putExtra("data", order);
            startActivity(orderIntent);
        }
	}
}

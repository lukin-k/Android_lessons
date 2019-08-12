package com.example.musicshop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
//import android.net.Uri;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
//import android.content.Intent;
import android.widget.Toast;


public class OrderActivity extends AppCompatActivity {
	TextView text_check;
//	Order order;
	String email;
	String data;
    Context mainContext;
    Boolean isError=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);

		mainContext = this;
		text_check = findViewById(R.id.textView_Check_order);
		Intent orderIntent = getIntent();
		Order order = (Order)orderIntent.getSerializableExtra("data");
		email=order.UserEmail;
		data="Your email: "+order.UserEmail+"\n"+"Good name: "+order.GoodName+"\n"+"Quantity: "+order.quantity+"\n"+"Price of good: "+order.price+"\n"+"Order price: "+order.quantity*order.price;
		text_check.setText(data);

		order=null;
//        Log.d("TestClassOrder", order.UserEmail+" "+order.GoodName+" "+order.quantity+" "+order.price);
	}

	public void Send_order(View view) {
        sender_mail_async async_sending = new sender_mail_async();
        async_sending.execute();

	    /*String email_sender = "some.shop2077@gmail.com";
        MailSenderClass sender = new MailSenderClass(email_sender, "2077superShoop");
        try {
			sender.sendMail("Order", data, email_sender, email, "");
		}catch (Exception e){
			Toast.makeText(this, "Send error", Toast.LENGTH_SHORT).show();
		}
        Toast.makeText(this, "Send ok", Toast.LENGTH_SHORT).show();*/
		/*Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("mailto:")); // only email apps should handle this
		intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
		intent.putExtra(Intent.EXTRA_SUBJECT, "Order");
		intent.putExtra(Intent.EXTRA_TEXT   , data);
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		}*/
//        finish();
	}
    private class sender_mail_async extends AsyncTask<Object, String, Boolean> {
        ProgressDialog WaitingDialog;

        @Override
        protected void onPreExecute() {
            WaitingDialog = ProgressDialog.show(OrderActivity.this, "Отправка данных", "Отправляем сообщение...", true);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            WaitingDialog.dismiss();
            if(isError)
                Toast.makeText(mainContext, "error", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mainContext, "ok", Toast.LENGTH_LONG).show();
            ((Activity)mainContext).finish();
        }

        @Override
        protected Boolean doInBackground(Object... params) {

            try {
//                title = ((EditText)findViewById(R.id.screen_sendnews_et_title)).getText().toString();
//                text = ((EditText)findViewById(R.id.screen_sendnews_et_text)).getText().toString();

//                from = "from_post_msg@gmail.com";
//                where = "where_post_msg@yandex.ru";
                String email_sender = "Your email";
                MailSenderClass sender = new MailSenderClass(email_sender, "Your password");
//                MailSenderClass sender = new MailSenderClass("mypostmail@gmail.com", "XXXXXX");

//                sender.sendMail(, text, from, where, attach);
//                String path_image = Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +/*R.drawable.*/"drawable//pokemon1.png").toString();
                sender.sendMail("Order", data, "Shop", email, "");
            } catch (Exception e) {
                isError=true;
                Log.e("sendMail",e+"");
            }

            return false;
        }
    }
}

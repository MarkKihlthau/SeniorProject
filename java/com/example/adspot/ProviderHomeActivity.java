package com.example.adspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProviderHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.blankimg);

        String username = message.substring(0, message.indexOf('.'));

        String master = "Welcome, " + username + "!";
        // Capture the layout's TextView and set the string as its text
        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText(master);

        TextView textView7 = findViewById(R.id.textView7);
        textView7.setText("Upload Product Image");

        TextView textView8 = findViewById(R.id.textView8);
        textView8.setText("Enter Product Name");

        TextView textView9 = findViewById(R.id.textView9);
        textView9.setText("Enter Breif Product Description");

    }

    public void openChatbot(View view)
    {
        Intent intent = new Intent(this, ChatBotActivity.class);
        startActivity(intent);
    }
}
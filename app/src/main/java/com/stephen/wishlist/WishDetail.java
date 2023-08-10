package com.stephen.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WishDetail extends AppCompatActivity {

    TextView title, date, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_detail);

        title = findViewById(R.id.detailTitle);
        date = findViewById(R.id.DetailsDate);
        content = findViewById(R.id.detailsTextView);

        //retrieve data
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            title.setText(extras.getString("title"));
            date.setText("Created " + extras.getString("date"));
            content.setText(" \" " + extras.getString("content") + " \"");

        }

    }
}
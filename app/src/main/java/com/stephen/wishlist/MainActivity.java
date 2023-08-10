package com.stephen.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import data.DatabaseHandler;
import model.MyWish;

public class MainActivity extends AppCompatActivity {

    EditText title, wishContent;
    Button button;
    DatabaseHandler dbH;
    String Title, WishContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbH = new DatabaseHandler(MainActivity.this);

        title = findViewById(R.id.titleEditText);
        wishContent = findViewById(R.id.wishEditText);
        button = findViewById(R.id.saveButton);
        Title = title.getText().toString().trim();
        WishContent = wishContent.getText().toString().trim();

        button.setOnClickListener(view -> {
            //call the save to db method
            saveToDb();
        });

    }

    public void saveToDb() {
         //call MyWish class to get the elements to push to database
//        MyWish wish = new MyWish();
//        wish.setTitle(Title);
//        wish.setContent(WishContent);
//
//        //execute the code
//        dbH.addWishes(wish);
//        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
//        dbH.close();
//
//        //clear the editText for new stuffs
//        title.setText("");
//        wishContent.setText("");

        //go to the listview activity

        MyWish myWish;
        java.text.DateFormat mainDateFormat = DateFormat.getDateInstance();
        String theDate = mainDateFormat.format(new Date());
        try {
            myWish = new MyWish(title.getText().toString(), wishContent.getText().toString(), theDate);
            Toast.makeText(this, myWish.toString(), Toast.LENGTH_LONG).show();
        } catch ( Exception e) {
            myWish = new MyWish("error", "error", "error");
        }

        DatabaseHandler dbHandler = new DatabaseHandler(MainActivity.this);
        boolean success = dbHandler.addWishes(myWish);
        Toast.makeText(this, "Added Successfully " + success, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayWish.class);
        startActivity(intent);

    }
}
package com.stephen.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import Adapter.WishAdapter;
import data.DatabaseHandler;
import model.MyWish;

public class DisplayWish extends AppCompatActivity {

    DatabaseHandler dbA;
    //this variable will hold all our wishes
    ArrayList<MyWish> dbWishes = new ArrayList<>();
    ListView listView;
    WishAdapter wishAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wish);

        listView = findViewById(R.id.listWish);

        refreshData();
    }

    private void refreshData() {
        dbWishes.clear();
        dbA = new DatabaseHandler(getApplicationContext());
//        ArrayList<MyWish> wishesFromDB = dbA.getWishes();
//
//        ArrayAdapter customWishAdapter = new ArrayAdapter(DisplayWish.this, R.layout.wish_row, wishesFromDB);
//        listView.setAdapter(customWishAdapter);

        //so we get the saved wishes and put it in the arrayList we are gonna use
        ArrayList<MyWish> wishesFromDB = dbA.getWishes();
        //loop through and fetch all the data
        for (int i = 0; i < wishesFromDB.size(); i++) {
            //this is to get the contents
            String title = wishesFromDB.get(i).getTitle();
            String dateText = wishesFromDB.get(i).getRecorddate();
            String content = wishesFromDB.get(i).getContent();

            MyWish myWish = new MyWish();
            //instantiate the myWish object of the Wish class
            myWish.setTitle(title);
            myWish.setContent(content);
            myWish.setRecorddate(dateText);

            //call the array
            dbWishes.add(myWish);
        }
        dbA.close();

        //setup adapter
        //tie up the stuffs, pass in the activity, the layout, and the data
        wishAdapter = new WishAdapter(DisplayWish.this, R.layout.wish_row, dbWishes);
        //set the listView
        listView.setAdapter(wishAdapter);
        wishAdapter.notifyDataSetChanged();
    }

}
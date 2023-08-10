package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.MyWish;


//extends sqliteOpenHelper
public class DatabaseHandler extends SQLiteOpenHelper {

    //make an arraylist of the model
    private final ArrayList<MyWish> wishList = new ArrayList<>();

    public DatabaseHandler(Context context) {
        //constant is from the constants class in data package
        super(context, constants.DATABASE_NAME, null, constants.DATABASE_VERSION);
    }

    //create constructor
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create the tables
        //create a string with sql code to tell the sqlite to create a table and its columns for us
        String CREATE_WISHES_TABLE = " CREATE TABLE " + constants.TABLE_NAME
                //pass in the columns of the table
                + "(  " + constants.KEY_ID + " INTEGER PRIMARY KEY, "
                + constants.TITLE_NAME + " TEXT, "
                + constants.CONTENT_NAME + " TEXT, "
                + constants.DATE_NAME + " LONG );";

        sqLiteDatabase.execSQL(CREATE_WISHES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + constants.TABLE_NAME);
        Log.v("ONUPGRADE", "DROPPING THE TABLE AND CREATING A NEW ONE");
        onCreate(sqLiteDatabase);
    }

    //to add contents to table
    public boolean addWishes(MyWish wish){
        //instantiate the sqlite database
        //ready to write in the database
        SQLiteDatabase db = this.getWritableDatabase();

        //create a database structure type called content
        //this structure is the same as a hashMap, like a dictionary or a key-value structure
        //where we can add info to it and reuse later
        ContentValues values = new ContentValues();
        //let the key be the title from constants class and the value from MyWish class
        values.put(constants.TITLE_NAME, wish.getTitle());
        values.put(constants.CONTENT_NAME, wish.getContent());
        //this will help to get the current date
        values.put(constants.DATE_NAME,java.lang.System.currentTimeMillis());

        long insert = db.insert(constants.TABLE_NAME, null, values);
        if (insert == -1){
            return false;
        }
        else {
            Log.v("Wish Saved Successfully ","Yeah");
            db.close();
            return  true;
        }
    }

    //get all wishes
    //return the array of wishes to be able to populate the listView
    public ArrayList<MyWish> getWishes(){

        wishList.clear();
        //we are getting/returning the wishList array
        //create what we need to the wishes
        String selectQuery = "SELECT * FROM " + constants.TABLE_NAME;
        //to read from the database
        SQLiteDatabase db = this.getReadableDatabase();

        //then create a cursor class, which helps point to the table content or rows
        //it takes in the table name and the list of columns we want to get info from
        //the last arg which is orderBy indicates how the items will be arranged and "DESC" signify descending order
        Cursor cursor = db.query(constants.TABLE_NAME, new String[]{constants.KEY_ID,
            constants.TITLE_NAME, constants.CONTENT_NAME, constants.DATE_NAME},null,null,null,null,constants.DATE_NAME + " DESC");

        //loop through cursor
        if (cursor.moveToFirst()) {
            do {
                //if cursor has something
                //we say do and retrieve everything while the cursor has items  to loop through
                //and populate the wish class
                MyWish wish = new MyWish();

                int index = cursor.getColumnIndex(constants.TITLE_NAME);
                if (index >  -1) {
                    wish.setTitle(cursor.getString(index));
                }
                //wish.setTitle(cursor.getString(Integer.parseInt(constants.TITLE_NAME)));

                int Cindex = cursor.getColumnIndex(constants.CONTENT_NAME);
                if (Cindex >  -1) {
                    wish.setTitle(cursor.getString(Cindex));
                }
                //wish.setContent(cursor.getString(Integer.parseInt(constants.CONTENT_NAME)));

                //for the date
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                int Dindex = cursor.getColumnIndex(constants.CONTENT_NAME);
                if (Dindex >  -1) {
                    String dateData = dateFormat.format(new Date(cursor.getLong(Dindex)));
                    wish.setRecorddate(dateData);
                }
                //we use the date format to take the date from our table and convert to a time
//                String datedate = dateFormat.format(new Date(cursor.getLong(Integer.parseInt(constants.DATE_NAME))).getTime());
//                wish.setRecorddate(datedate);

                //wish object contains everything from our database
                wishList.add(wish);

            } while(cursor.moveToNext());
        } else {
            //do not nothing
        }

        //close both the cursor and database
        cursor.close();
        db.close();

        return wishList;
    }
}

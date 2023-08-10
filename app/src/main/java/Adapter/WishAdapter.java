package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stephen.wishlist.DisplayWish;
import com.stephen.wishlist.MainActivity;
import com.stephen.wishlist.R;
import com.stephen.wishlist.WishDetail;

import java.util.ArrayList;
import java.util.List;

import model.MyWish;

public class WishAdapter extends ArrayAdapter<MyWish> {
    //instance variables
    Activity mcontext;
    int layoutResource;
    MyWish wish;
    ArrayList<MyWish> mdata = new ArrayList<>();

    //constructor
    public WishAdapter(Activity context, int resource, ArrayList<MyWish> data) {
        super(context, resource, data);

        //initialize
        mcontext = context;
        layoutResource = resource;
        mdata = data;
        notifyDataSetChanged();

    }

    //override methods

    @Override
    public int getCount() {
        //get the size of the arrayList
        return mdata.size();
    }

    @Override
    public MyWish getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public int getPosition(MyWish item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        //if no row has been created, we have to create the row
        if (row == null || (row.getTag() == null)) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            row = inflater.inflate(layoutResource, null);
            holder = new ViewHolder();
            holder.mTitle = row.findViewById(R.id.rowName);
            holder.mDate = row.findViewById(R.id.rowDate);

            //pass in the holder in the row
            row.setTag(holder);
        }
        //if a row has been created, just recycle the listview
        else {
            holder = (ViewHolder) row.getTag();
        }
        holder.myWish = getItem(position);
        //set text
        holder.mTitle.setText(holder.myWish.getTitle());
        holder.mDate.setText(holder.myWish.getRecorddate());


        ViewHolder finalHolder = holder;
        holder.mTitle.setOnClickListener(view -> {
            String text = finalHolder.myWish.getContent().toString();
            String dateText = finalHolder.myWish.getRecorddate();
            String title = finalHolder.myWish.getTitle().toString();

            Intent intent = new Intent(mcontext, WishDetail.class);
            intent.putExtra("content", text);
            intent.putExtra("date", dateText);
            intent.putExtra("title", title);

            mcontext.startActivity(intent);

        });

        //return the row
        return row;
    }

    class ViewHolder{
        MyWish myWish ;
        TextView mTitle;
        TextView mId;
        TextView mContent;
        TextView mDate;

    }
}

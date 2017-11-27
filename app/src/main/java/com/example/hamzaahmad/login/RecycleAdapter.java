package com.example.hamzaahmad.login;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmad on 11/22/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyHolder> {


    List<UserInformation> list;
    public RecycleAdapter(List<UserInformation> list){
          this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card, parent, false);
        MyHolder holder = new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        UserInformation r = list.get(position);
        holder.textView.setText(r.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.image);
            textView = (TextView)itemView.findViewById(R.id.user);
        }
    }
}

package com.example.hassan.tutorialfirebase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Noteadapter extends  RecyclerView.Adapter<Noteadapter.viewHolder>
{

    ArrayList<Data>data;
    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public Noteadapter(ArrayList<Data> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, final int i) {
        final Data item=data.get(i);
        viewHolder.txt1.setText(item.getN_Title());
       viewHolder.txt2.setText(item.getN_note());
      viewHolder.h_data.setText( item.getN_data());
      viewHolder.txt3.setText(item.getId());
      if(viewHolder!=null)
      {
          viewHolder.parent.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  onItemClick.onitemclick(item,i);
              }
          });
      }


    }

    @Override
    public int getItemCount() {
        return data.size();



    }

    class viewHolder  extends RecyclerView.ViewHolder
    {
        TextView txt1,txt2,h_data,txt3;
        View parent;
        public viewHolder(View view)
        {
            super(view);
            txt1=view.findViewById(R.id.txt1);
            txt2=view.findViewById(R.id.txt2);
            h_data=view.findViewById(R.id.h_data);
            txt3=view.findViewById(R.id.item_id);
            parent=view;

        }


    }
    interface  OnItemClick
    {
        public void onitemclick(Data data,int postion);


    }
}

package com.example.smartdrive;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    public void setDetails(Context ctx,String name,String location,String number,String age,String vehicle)
    {

        TextView NameTextView = (TextView) itemView.findViewById(R.id.ShowStudentNameTextView);

        TextView NumberTextView = (TextView) itemView.findViewById(R.id.ShowStudentNumberTextView);

        TextView LocationTextView = (TextView) itemView.findViewById(R.id.ShowStudentLocationTextView);

        TextView AgeTextView = (TextView) itemView.findViewById(R.id.ShowAgeTextView);

        TextView VehicleTextView = (TextView) itemView.findViewById(R.id.ShowVehicleTextView);

        NameTextView.setText(name);
        NumberTextView.setText(number);
        LocationTextView.setText(location);
        AgeTextView.setText(age);
        VehicleTextView.setText(vehicle);
    }

    private ViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}

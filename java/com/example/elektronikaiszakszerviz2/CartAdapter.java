package com.example.elektronikaiszakszerviz2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Appointment> ItemsData;
    private ArrayList<Appointment> ItemsDataAll;
    private Context context;
    CartAdapter(Context context, ArrayList<Appointment> ItemsData) {
        this.ItemsDataAll = ItemsData;
        this.ItemsData = ItemsData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Appointment currAppointment = ItemsData.get(position);
        holder.bindTo(currAppointment);
    }

    @Override
    public int getItemCount() {
        return ItemsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private CalendarView calendar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar = itemView.findViewById(R.id.calender);
            title = itemView.findViewById(R.id.editTextText);

        }

        public void bindTo(Appointment appointment){

            title.setText(appointment.getTitle());
            calendar.setDate(appointment.getDate().getTime());

        }
    }

}



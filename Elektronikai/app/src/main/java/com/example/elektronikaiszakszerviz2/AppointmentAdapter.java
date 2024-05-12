package com.example.elektronikaiszakszerviz2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private ArrayList<Appointment> ItemsData;
    private ArrayList<Appointment> ItemsDataAll;

    private Context context;


    AppointmentAdapter(Context context, ArrayList<Appointment> ItemsData) {
        this.ItemsDataAll = ItemsData;
        this.ItemsData = ItemsData;
        this.context = context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
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

        private CalendarView calendarView;
        private CardView cardView;
        private Button sendButton;
        private Button buttonViewCalender;

        private Date date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.editTextText);
            image = itemView.findViewById(R.id.item);
            cardView = itemView.findViewById(R.id.cardView);
            calendarView = itemView.findViewById(R.id.calender);
            date = Date.from(Calendar.getInstance().toInstant());
            buttonViewCalender =  itemView.findViewById(R.id.button);
            sendButton =  itemView.findViewById(R.id.button2);


            calendarView.setVisibility(View.GONE);

            buttonViewCalender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(calendarView.getVisibility() == View.VISIBLE) {

                        calendarView.setVisibility(View.GONE);
                        buttonViewCalender.setText(R.string.dates);
                        sendButton.setVisibility(View.GONE);

                    }else{

                        calendarView.setVisibility(View.VISIBLE);
                        buttonViewCalender.setText(R.string.close);
                        sendButton.setVisibility(View.VISIBLE);

                    }


                }
            });

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    // Selected date available here
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);
                    date = selectedDate.getTime();
                }
            });


            sendButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ((AppointmentActivity)context).pressButton(title.getText().toString(),date);
                }

            });


   }





        public void bindTo(Appointment appointment){

            title.setText(appointment.getTitle());
            image.setImageResource(appointment.getImage());

            Glide.with(context).load(appointment.getImage()).into(image);

        }

    }


}



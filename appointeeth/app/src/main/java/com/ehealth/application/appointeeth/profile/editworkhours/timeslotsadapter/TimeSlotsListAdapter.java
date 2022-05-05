package com.ehealth.application.appointeeth.profile.editworkhours.timeslotsadapter;


import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.TimeSlot;

import java.sql.Time;
import java.util.ArrayList;


public class TimeSlotsListAdapter extends RecyclerView.Adapter<TimeSlotsListAdapter.TimeSlotViewHolder> {

    ArrayList<TimeSlot> timeSlotsList;
    String cliniqueId, userId;

    int color1 = Color.parseColor("#80deea");
    int color2 = Color.parseColor("#FFFFFF");

    public TimeSlotsListAdapter(ArrayList<TimeSlot> timeSlotsList){
        this.timeSlotsList = timeSlotsList;
    }

    @NonNull
    @Override
    public TimeSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_timeslots, parent, false);
        return new TimeSlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotViewHolder holder, int position) {
        int color = timeSlotsList.get(position).getSelected()? color1 : color2;

        holder.textViewName.setText(timeSlotsList.get(position).getTimeSlot());
        holder.timeslotView.setCardBackgroundColor(color);

        final String timeSlot = timeSlotsList.get(position).getTimeSlot();

        holder.timeslotView.setOnClickListener(v -> {

            Boolean newSelected = !timeSlotsList.get(position).getSelected();

            timeSlotsList.set(position, new TimeSlot(timeSlot, newSelected));
            holder.timeslotView.setCardBackgroundColor(newSelected? color1 : color2);
        });
    }

    @Override
    public int getItemCount() {
        return timeSlotsList.size();
    }

    static class TimeSlotViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public CardView timeslotView;

        public TimeSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.timeslot);
            this.timeslotView = itemView.findViewById(R.id.timeslot_view);
        }
    }

    public ArrayList<TimeSlot> getSelectedTimeSlots() {
        ArrayList<TimeSlot> selected = new ArrayList<>();
        for(int i = 0; i < getItemCount(); i++) {
            if(timeSlotsList.get(i).getSelected()) {
                selected.add(timeSlotsList.get(i));
            }
         }
        return selected;
     }
}


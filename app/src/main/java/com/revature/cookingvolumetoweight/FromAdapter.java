package com.revature.cookingvolumetoweight;

import android.annotation.SuppressLint;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FromAdapter extends RecyclerView.Adapter<FromAdapter.FromViewHolder>{

    public class FromViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private TextView name;

        public FromViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.tv_units_entry);
            this.card = view.findViewById(R.id.card_entry);
        }
    }

    public class FromGestureListener implements GestureDetector.OnGestureListener {

        private MainActivity activity;
        private int position;
        private RecyclerView.Adapter adapter;

        public FromGestureListener(MainActivity activity, int position, RecyclerView.Adapter adapter) {
            this.activity = activity;
            this.position = position;
            this.adapter = adapter;
        }

        @Override
        public boolean onDown(MotionEvent event){ return false; }

        @Override
        public void onShowPress(MotionEvent e) { activity.updateFrom(data.get(position)); }

        @Override
        public boolean onSingleTapUp(MotionEvent e) { return true; }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }

        @Override
        public void onLongPress(MotionEvent e) {}

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { return false; }
    }

    private ArrayList<String> data;
    private ViewGroup parent;
    private View view;
    private MainActivity activity;

    public FromAdapter(MainActivity activity, ArrayList<String> data){
        this.data = data;
        this.activity = activity;
    }

    @Override
    public FromAdapter.FromViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        this.view = layoutInflater.inflate(R.layout.item_units_entry, parent, false);

        return new FromAdapter.FromViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(FromAdapter.FromViewHolder viewHolder, int position){
        viewHolder.name.setText(data.get(position));

        final GestureDetectorCompat gestureDetectorCompat =
                new GestureDetectorCompat(parent.getContext(), new FromAdapter.FromGestureListener(activity, position, this));
        viewHolder.card.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

}

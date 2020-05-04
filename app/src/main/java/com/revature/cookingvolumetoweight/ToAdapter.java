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

public class ToAdapter extends RecyclerView.Adapter<ToAdapter.ToViewHolder> {

    public class ToViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private TextView name;

        public ToViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.tv_units_answer);
            this.card = view.findViewById(R.id.card_answer);
        }
    }

    public class ToGestureListener implements GestureDetector.OnGestureListener {

        private MainActivity activity;
        private int position;
        private RecyclerView.Adapter adapter;

        public ToGestureListener(MainActivity activity, int position, RecyclerView.Adapter adapter){
            this.activity = activity;
            this.position = position;
            this.adapter = adapter;
        }

        @Override
        public boolean onDown(MotionEvent event){ return false; }

        @Override
        public void onShowPress(MotionEvent e) { activity.updateToName(data.get(position)); }

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

    public ToAdapter(MainActivity activity, ArrayList<String> data){
        this.data = data;
        this.activity = activity;
    }

    @Override
    public ToAdapter.ToViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.parent = parent;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        this.view = layoutInflater.inflate(R.layout.item_units_answer, parent, false);

        return new ToAdapter.ToViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ToAdapter.ToViewHolder viewHolder, int position){
        viewHolder.name.setText(data.get(position));

        final GestureDetectorCompat gestureDetectorCompat =
                new GestureDetectorCompat(parent.getContext(), new ToAdapter.ToGestureListener(activity, position, this));
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

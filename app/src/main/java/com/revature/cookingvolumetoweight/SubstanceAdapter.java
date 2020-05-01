package com.revature.cookingvolumetoweight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Pair;
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

public class SubstanceAdapter extends RecyclerView.Adapter<SubstanceAdapter.SubstanceViewHolder> {

    public class SubstanceViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private TextView name;
        private TextView density;

        public SubstanceViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.tv_sub_name);
            this.density = view.findViewById(R.id.tv_sub_density);
            this.card = view.findViewById(R.id.card_sub);
        }
    }

    public class SubstanceGestureListener implements GestureDetector.OnGestureListener {

        private MainActivity activity;
        private int position;
        private RecyclerView.Adapter adapter;

        public SubstanceGestureListener(MainActivity activity, int position, RecyclerView.Adapter adapter){
            this.activity = activity;
            this.position = position;
            this.adapter = adapter;
        }

        @Override
        public boolean onDown(MotionEvent event){ return false; }

        @Override
        public void onShowPress(MotionEvent e) { activity.updateSubstance(data.get(position)); }

        @Override
        public boolean onSingleTapUp(MotionEvent e) { return true; }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }

        @Override
        public void onLongPress(MotionEvent e) {}

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { return false; }
    }

    private ArrayList<Pair<String, Float>> data;
    private ViewGroup parent;
    private View view;
    private MainActivity activity;

    public SubstanceAdapter(MainActivity activity, ArrayList<Pair<String, Float>> data){
        this.data = data;
        this.activity = activity;
    }

    @Override
    public SubstanceAdapter.SubstanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.parent = parent;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        this.view = layoutInflater.inflate(R.layout.item_substance, parent, false);

        return new SubstanceViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(SubstanceAdapter.SubstanceViewHolder viewHolder, int position){
        Pair<String, Float> item = data.get(position);
        viewHolder.name.setText(item.first);
        viewHolder.density.setText(item.second.toString());

        final GestureDetectorCompat gestureDetectorCompat =
                new GestureDetectorCompat(parent.getContext(), new SubstanceGestureListener(activity, position, this));
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

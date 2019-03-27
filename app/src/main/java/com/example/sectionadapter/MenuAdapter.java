package com.example.sectionadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    interface Listener{
        void onMenuClicked(String title);
    }

    private Listener menuListener;
    private List<String> stringList;
    public MenuAdapter(List<String> list, Listener listener){
        stringList = list;
        menuListener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu, viewGroup, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuHolder holder = (MenuHolder)viewHolder;
        holder.menuTv.setText(stringList.get(i));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


    private String getItem(int pos){
        return stringList.get(pos);
    }
    private class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView menuTv;
        public MenuHolder(@NonNull View itemView) {
            super(itemView);
            menuTv = itemView.findViewById(R.id.menu_tv);
            menuTv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            menuListener.onMenuClicked(getItem(getAdapterPosition()));
        }
    }
}

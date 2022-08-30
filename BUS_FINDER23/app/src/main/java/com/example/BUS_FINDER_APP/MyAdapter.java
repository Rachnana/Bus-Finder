package com.example.BUS_FINDER_APP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user=list.get(position);
        holder.Bus_id.setText(user.getBus_id());
        holder.Bus_name.setText(user.getBus_name());
        holder.Destination.setText(user.getDestination());
        holder.Source.setText(user.getSource());
        holder.Timing.setText(user.getTiming());
        holder.St_tkt.setText(user.getStd_ticket());
        //holder.St_tkt.setText(user.getTiming());
        holder.Eld_tkt.setText(user.getEld_ticket());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Bus_id,Bus_name,Destination,Source,Timing,St_tkt,Eld_tkt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Bus_id=itemView.findViewById(R.id.rtv_id);
            Bus_name=itemView.findViewById(R.id.rtv_name);
            Destination=itemView.findViewById(R.id.rtv_dest);
            Source=itemView.findViewById(R.id.rtv_source);
            Timing=itemView.findViewById(R.id.rtv_timing);
            St_tkt=itemView.findViewById(R.id.rtv_stk);
            Eld_tkt=itemView.findViewById(R.id.rtv_ekt);
        }
    }
}

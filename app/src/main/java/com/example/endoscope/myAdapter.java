package com.example.endoscope;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {

    ArrayList<model>dataholder = new ArrayList<>();

    public myAdapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datacard,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.patName.setText(dataholder.get(position).getPatientName());
        holder.media.setImageBitmap(dataholder.get(position).getMediaPath());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView patName;
        ImageView  media;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            patName=(TextView)itemView.findViewById(R.id.patName);
            media=(ImageView) itemView.findViewById(R.id.imgThumb);
        }
    }
}

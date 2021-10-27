package com.example.tinytecnology.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinytecnology.R;

import java.util.List;

public class ProyectoListAdapter extends RecyclerView.Adapter<ProyectoListAdapter.MyViewHolder> {

    private Context context;
    private List<Proyecto> proyectoList;
    private HandleProyectoClick clickListener;

    public ProyectoListAdapter(Context ctx, HandleProyectoClick listener) {
        this.context = ctx;
        this.clickListener = listener;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProyectoListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvtitulo.setText(this.proyectoList.get(position).titulo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(proyectoList.get(position));
            }
        });

        holder.editProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(proyectoList.get(position));
            }
        });

        holder.removeProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(proyectoList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(proyectoList == null || proyectoList.size() == 0)
            return 0;
        else
            return proyectoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvtitulo;
        ImageView removeProyecto;
        ImageView editProyecto;

        public MyViewHolder(View view) {
            super(view);
            tvtitulo = view.findViewById(R.id.titulo);
            removeProyecto = view.findViewById(R.id.removeProyecto);
            editProyecto = view.findViewById(R.id.editProyecto);
        }


    }

    public interface HandleProyectoClick {
        void itemClick(Proyecto proyecto);
        void removeItem(Proyecto proyecto);
        void editItem(Proyecto proyecto);
    }
}

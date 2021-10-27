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

public class TareaListAdapter extends RecyclerView.Adapter<TareaListAdapter.MyViewHolder> {

    private Context context;
    private List<Tarea> tareaList;
    private TareaListAdapter.HandleTareaClick clickListener;

    public TareaListAdapter(Context ctx, TareaListAdapter.HandleTareaClick listener) {
        this.context = ctx;
        this.clickListener = listener;
    }

    public void setTareaList(List<Tarea> tareaList) {
        this.tareaList = tareaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TareaListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new TareaListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaListAdapter.MyViewHolder holder, int position) {
        holder.tvtitulo.setText(this.tareaList.get(position).titulo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(tareaList.get(position));
            }
        });

        holder.editTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(tareaList.get(position));
            }
        });

        holder.removeTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(tareaList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(tareaList == null || tareaList.size() == 0)
            return 0;
        else
            return tareaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvtitulo;
        ImageView removeTarea;
        ImageView editTarea;

        public MyViewHolder(View view) {
            super(view);
            tvtitulo = view.findViewById(R.id.titulo);
            removeTarea = view.findViewById(R.id.removeProyecto);
            editTarea = view.findViewById(R.id.editProyecto);
        }


    }

    public interface HandleTareaClick {
        void itemClick(Tarea tarea);
        void removeItem(Tarea tarea);
        void editItem(Tarea tarea);
    }
}

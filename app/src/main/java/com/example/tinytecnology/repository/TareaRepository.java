package com.example.tinytecnology.repository;

import android.content.Context;


import androidx.room.Room;

import com.example.tinytecnology.database.TareaDatabase;
import com.example.tinytecnology.model.Proyecto;
import com.example.tinytecnology.model.Tarea;

import java.util.List;

public class TareaRepository {
    private String DB_NAME = "tinytecnology";
    private TareaDatabase tareaDatabase;

    Context context;

    public TareaRepository(Context context) {
        this.context = context;
        tareaDatabase = Room.databaseBuilder(context, TareaDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();

    }

    public List<Tarea> getAllTareas(int proyectoId) {
        return tareaDatabase.tareaDAO().getAllTarea(proyectoId);
    }
    public void insertTarea(Tarea tarea){
        tareaDatabase.tareaDAO().insertTarea(tarea);
    }
}

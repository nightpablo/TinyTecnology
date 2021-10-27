package com.example.tinytecnology.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.example.tinytecnology.database.ProyectoDatabase;
import com.example.tinytecnology.model.Proyecto;

import java.util.Date;
import java.util.List;

public class ProyectoRepository {
    private String DB_NAME = "tinytecnology";
    private ProyectoDatabase proyectoDatabase;

    Context context;

    public ProyectoRepository(Context context) {
        this.context = context;
        proyectoDatabase = Room.databaseBuilder(context, ProyectoDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public List<Proyecto> getAllProyectos() {
        return proyectoDatabase.proyectoDAO().getAllProyecto();
    }
    public void insertProyecto(Proyecto proyecto){
        proyectoDatabase.proyectoDAO().insertProyecto(proyecto);
    }


    //public void updateTask(Proyecto proyecto){}

    //public void deleteTask(Proyecto proyecto){}
}

package com.example.tinytecnology.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tinytecnology.dao.ProyectoDAO;
import com.example.tinytecnology.dao.TareaDAO;
import com.example.tinytecnology.model.Proyecto;
import com.example.tinytecnology.model.Tarea;

@Database(entities = {Proyecto.class, Tarea.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TareaDAO tareaDAO();

    public abstract ProyectoDAO proyectoDAO();

    public static AppDatabase INSTANCE;

    public static AppDatabase getDBinstance(Context context) {
        if(INSTANCE == null ) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDB")
                    .allowMainThreadQueries()
                    .build();

        }

        return INSTANCE;
    }
}

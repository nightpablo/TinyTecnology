package com.example.tinytecnology.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.tinytecnology.dao.TareaDAO;
import com.example.tinytecnology.model.Tarea;

@Database(entities = {Tarea.class}, version = 1, exportSchema = false)
public abstract class TareaDatabase extends RoomDatabase {
    public abstract TareaDAO tareaDAO();

}

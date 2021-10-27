package com.example.tinytecnology.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.tinytecnology.DateConverter;
import com.example.tinytecnology.dao.ProyectoDAO;
import com.example.tinytecnology.model.Proyecto;

@Database(entities = {Proyecto.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class ProyectoDatabase extends RoomDatabase {
    public abstract ProyectoDAO proyectoDAO();

}

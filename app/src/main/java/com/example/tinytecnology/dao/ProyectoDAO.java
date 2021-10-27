package com.example.tinytecnology.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tinytecnology.model.Proyecto;

import java.util.List;

@Dao
public interface ProyectoDAO {

    @Insert
    Long insertProyecto(Proyecto proyecto);

    @Update
    void updateProyecto(Proyecto proyecto);

    @Delete
    void deleteProyecto(Proyecto proyecto);

    @Query("Select * from Proyecto")
    List<Proyecto> getAllProyecto();
}

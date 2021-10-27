package com.example.tinytecnology.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.tinytecnology.model.Proyecto;
import com.example.tinytecnology.model.Tarea;

import java.util.List;

@Dao
public interface TareaDAO {
    @Insert
    Long insertTarea(Tarea tarea);

    @Update
    void updateTarea(Tarea tarea);

    @Delete
    void deleteTarea(Tarea tarea);

    @Query("Select * from Tarea where proyecto_id = :proyectoId")
    List<Tarea> getAllTarea(int proyectoId);
}

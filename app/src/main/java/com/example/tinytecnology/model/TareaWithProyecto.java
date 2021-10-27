package com.example.tinytecnology.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TareaWithProyecto {

    @Embedded
    public Proyecto proyecto;

    @Relation
    (
            parentColumn = "id",
            entityColumn = "proyecto_id",
            entity = Tarea.class
    )
    public List<Proyecto> proyectos;
}

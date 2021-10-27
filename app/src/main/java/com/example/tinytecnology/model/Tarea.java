package com.example.tinytecnology.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Date;
import java.util.List;

@Entity(tableName = "tarea")
public class Tarea {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "titulo")
    public String titulo;

    @ColumnInfo(name = "hora_estimada")
    public int horaEstimada;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "proyecto_id")
    public int proyectoId;

    public Tarea(String titulo, int horaEstimada, String descripcion, int proyectoId){
        this.titulo = titulo;
        this.horaEstimada = horaEstimada;
        this.descripcion = descripcion;
        this.proyectoId = proyectoId;
    }
}

package com.example.tinytecnology.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.tinytecnology.DateConverter;

import java.util.Date;

@Entity(tableName = "proyecto")
public class Proyecto {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "titulo")
    public String titulo;

    @ColumnInfo(name = "responsable")
    public String responsable;

    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "fecha_finalizacion")
    public Date fechaFinalizacion;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    public Proyecto(String titulo, String responsable, Date fechaFinalizacion, String descripcion) {
        this.titulo = titulo;
        this.responsable = responsable;
        this.fechaFinalizacion = fechaFinalizacion;
        this.descripcion = descripcion;
    }

}

package com.example.tinytecnology.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tinytecnology.database.AppDatabase;
import com.example.tinytecnology.model.Proyecto;
import com.example.tinytecnology.repository.ProyectoRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<List<Proyecto>> proyectosList;
    private AppDatabase proyectoRepository;

    public MainActivityViewModel(Application app) {
        super(app);
        proyectosList = new MutableLiveData<List<Proyecto>>();
        proyectoRepository = AppDatabase.getDBinstance(getApplication().getApplicationContext());
    }

    public LiveData<List<Proyecto>> getProyectosListObserver() {
        return proyectosList;
    }

    public void getAllProyectos(){
        List<Proyecto> lista = proyectoRepository.proyectoDAO().getAllProyecto();
        if(lista.size() > 0) {
            proyectosList.postValue(lista);
        } else {
            proyectosList.postValue(null);
        }
    }

    public void insertProyecto(String titulo, String responsable, String descripcion){
        Proyecto proyecto = new Proyecto(titulo, responsable, null, descripcion);
        proyectoRepository.proyectoDAO().insertProyecto(proyecto);
        getAllProyectos();
    }


}

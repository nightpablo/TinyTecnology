package com.example.tinytecnology.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tinytecnology.database.AppDatabase;
import com.example.tinytecnology.model.Tarea;

import java.util.List;

public class TareasMainActivityViewModel  extends AndroidViewModel {
    private MutableLiveData<List<Tarea>> tareasList;
    private AppDatabase tareaRepository;

    public TareasMainActivityViewModel(Application app) {
        super(app);
        tareasList = new MutableLiveData<List<Tarea>>();
        tareaRepository = AppDatabase.getDBinstance(getApplication().getApplicationContext());
    }

    public LiveData<List<Tarea>> getTareasListObserver() {
        return tareasList;
    }

    public void getAllTareas(int proyectoId){
        List<Tarea> lista = tareaRepository.tareaDAO().getAllTarea(proyectoId);
        if(lista.size() > 0) {
            tareasList.postValue(lista);
        } else {
            tareasList.postValue(null);
        }
    }

    public void insertTarea(String titulo, int horaEstimada, String descripcion, int proyectoId){
        Tarea tarea = new Tarea(titulo, horaEstimada, descripcion, proyectoId);
        tareaRepository.tareaDAO().insertTarea(tarea);
        getAllTareas(tarea.proyectoId);
    }


}

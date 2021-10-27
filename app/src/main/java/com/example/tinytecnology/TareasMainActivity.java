package com.example.tinytecnology;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinytecnology.model.Proyecto;
import com.example.tinytecnology.model.ProyectoListAdapter;
import com.example.tinytecnology.model.Tarea;
import com.example.tinytecnology.model.TareaListAdapter;
import com.example.tinytecnology.viewmodel.MainActivityViewModel;
import com.example.tinytecnology.viewmodel.TareasMainActivityViewModel;

import java.util.List;

public class TareasMainActivity extends AppCompatActivity implements TareaListAdapter.HandleTareaClick  {

    private int proyecto_id;
    private TareaListAdapter tareaListAdapter;
    private TareasMainActivityViewModel viewModel;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_main);

        proyecto_id = getIntent().getIntExtra("proyecto_id", 0);
        String proyectoTitulo = getIntent().getStringExtra("proyecto_titulo");
        recyclerView = findViewById(R.id.recyclerViewTarea);

        ImageView addNew = findViewById(R.id.addNewTareaImageView);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTareaDialog();
            }
        });
        inicializar();

    }

    private void inicializar(){
        viewModel = new ViewModelProvider(this).get(TareasMainActivityViewModel.class);
        viewModel.getTareasListObserver().observe(this, new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tareas) {
                if(tareas == null) {
//                    noResulttextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    //show in the recyclerview
                    tareaListAdapter.setTareaList(tareas);
                    recyclerView.setVisibility(View.VISIBLE);
//                    noResulttextView.setVisibility(View.GONE);
                }
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tareaListAdapter = new TareaListAdapter(this, this);
        recyclerView.setAdapter(tareaListAdapter);
        viewModel.getAllTareas(proyecto_id);
    }

    private void showAddTareaDialog() {
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_tarea, null);
        EditText enterTituloInput = dialogView.findViewById(R.id.enterTituloInput);
        EditText enterHoraEstimadaInput = dialogView.findViewById(R.id.horaEstimadaInput);
        EditText enterDescripcionInput = dialogView.findViewById(R.id.enterDescripcionInput);
        TextView createButton = dialogView.findViewById(R.id.createButton);
        TextView cancelButton = dialogView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = enterTituloInput.getText().toString();
                String horaEstimada = enterHoraEstimadaInput.getText().toString();
                String descripcion = enterDescripcionInput.getText().toString();
                if(TextUtils.isEmpty(titulo)) {
                    Toast.makeText(TareasMainActivity.this, "Ingrese el titulo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(horaEstimada)) {
                    Toast.makeText(TareasMainActivity.this, "Ingrese la hora estimada", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!TextUtils.isDigitsOnly(horaEstimada)) {
                    Toast.makeText(TareasMainActivity.this, "La hora estimada admite sólo números", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(descripcion)) {
                    Toast.makeText(TareasMainActivity.this, "Ingrese la descripcion", Toast.LENGTH_SHORT).show();
                    return;
                }

                viewModel.insertTarea(titulo, Integer.parseInt(horaEstimada), descripcion, proyecto_id);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }

    @Override
    public void itemClick(Tarea tarea) {

    }

    @Override
    public void removeItem(Tarea tarea) {

    }

    @Override
    public void editItem(Tarea tarea) {

    }
}
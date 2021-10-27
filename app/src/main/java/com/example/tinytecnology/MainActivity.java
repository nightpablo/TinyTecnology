package com.example.tinytecnology;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinytecnology.model.Proyecto;
import com.example.tinytecnology.model.ProyectoListAdapter;
import com.example.tinytecnology.repository.ProyectoRepository;
import com.example.tinytecnology.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProyectoListAdapter.HandleProyectoClick {

    private MainActivityViewModel viewModel;

    private ProyectoListAdapter proyectoListAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewProyecto);
        ImageView addNew = findViewById(R.id.addNewProyectoImageView);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProyectoDialog();
            }
        });
        inicializar();
    }

    private void inicializar(){
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getProyectosListObserver().observe(this, new Observer<List<Proyecto>>() {
            @Override
            public void onChanged(List<Proyecto> proyectos) {
                if(proyectos == null) {
//                    noResulttextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    //show in the recyclerview
                    proyectoListAdapter.setProyectoList(proyectos);
                    recyclerView.setVisibility(View.VISIBLE);
//                    noResulttextView.setVisibility(View.GONE);
                }
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        proyectoListAdapter = new ProyectoListAdapter(this, this);
        recyclerView.setAdapter(proyectoListAdapter);
        viewModel.getAllProyectos();
    }

    private void showAddProyectoDialog() {
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_proyecto, null);
        EditText enterTituloInput = dialogView.findViewById(R.id.enterTituloInput);
        EditText enterResponsableInput = dialogView.findViewById(R.id.enterResponsableInput);
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
                String responsable = enterResponsableInput.getText().toString();
                String descripcino = enterDescripcionInput.getText().toString();
                if(TextUtils.isEmpty(titulo)) {
                    Toast.makeText(MainActivity.this, "Ingrese el titulo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(responsable)) {
                    Toast.makeText(MainActivity.this, "Ingrese el responsable", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(descripcino)) {
                    Toast.makeText(MainActivity.this, "Ingrese la descripcion", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.insertProyecto(titulo, responsable, descripcino);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }

    @Override
    public void itemClick(Proyecto proyecto) {

        Intent intent = new Intent(MainActivity.this, TareasMainActivity.class);
        intent.putExtra("proyecto_id", proyecto.id);
        intent.putExtra("proyecto_titulo", proyecto.titulo);

        startActivity(intent);
    }

    @Override
    public void removeItem(Proyecto proyecto) {
        //suspendido
    }

    @Override
    public void editItem(Proyecto proyecto) {
        //suspendido
    }
}
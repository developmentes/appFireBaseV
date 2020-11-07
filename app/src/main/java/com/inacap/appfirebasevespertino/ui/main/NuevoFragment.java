package com.inacap.appfirebasevespertino.ui.main;

import android.app.Activity;
import android.os.Bundle;

import androidx.core.text.util.LinkifyCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inacap.appfirebasevespertino.R;
import com.inacap.appfirebasevespertino.model.Tarea;

public class NuevoFragment extends Fragment {

EditText txt_titulo,txt_descripcion,txt_prioridad;
Button bt_guardar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nuevo, container, false);

        txt_titulo = view.findViewById(R.id.txt_nuevo_titulo);
        txt_descripcion = view.findViewById(R.id.txt_nuevo_descripcion);
        txt_prioridad = view.findViewById(R.id.txt_nuevo_prioridad);
        bt_guardar = view.findViewById(R.id.btn_nuevo_guardar);

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titulo = txt_titulo.getText().toString();
                String descripcion = txt_descripcion.getText().toString();
                String prioridad = txt_prioridad.getText().toString();

                if (titulo.isEmpty() || descripcion.isEmpty() || prioridad.isEmpty()){

                    Toast.makeText(getActivity(),"Complete todos los campos",Toast.LENGTH_LONG).show();

                }else{
                    //Guardar informacion en fireBase
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Tareas");


                    FirebaseAuth auth  ;//declaramos un auth para traer el uid del user
                    auth  = FirebaseAuth.getInstance();//inicializamos auth

                    Tarea tareaNueva = new Tarea();
                    tareaNueva.setTitulo(titulo);
                    tareaNueva.setDescripcion(descripcion);
                    tareaNueva.setPrioridad(prioridad);

                   String uid =  auth.getCurrentUser().getUid();//traemos el uid del user desde firebase
                    myRef.child(uid).push().setValue(tareaNueva);//no olvidaR EL PUSH SI NO QUEDA LA K .Tambien agramos ellc child el cual crea un hijo dentro del objeto tarea

                    Toast.makeText(getActivity(),"Tarea creada",Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;

    }
}
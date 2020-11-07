package com.inacap.appfirebasevespertino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.inacap.appfirebasevespertino.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    EditText txtnombre,txtemail,txtclave;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtnombre = findViewById(R.id.txt_registro_nombre);
        txtemail = findViewById(R.id.txt_registro_email);
        txtclave = findViewById(R.id.txt_registro_pass);
        auth = FirebaseAuth.getInstance();
    }

    public void registrar(View view) {
       final String nombre = txtnombre.getText().toString();
       final String email = txtemail.getText().toString();
        String pass = txtclave.getText().toString();


auth.createUserWithEmailAndPassword(email,pass)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //se crea el usuario en firebase
                    //si  Inicia sesión correctamente, actualiza la interfaz de usuario con la información del usuario que inició sesión
                    // Write a message to the database


                    Usuario user = new Usuario();
                    user.setEmail(email);
                    user.setNombre(nombre);
                    user.setUid(auth.getCurrentUser().getUid());
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("usuarios");

                    myRef.push().setValue(user);
                    Toast.makeText(RegistroActivity.this, "Usuario Creado", Toast.LENGTH_LONG
                    ).show();


                }else{
                    Toast.makeText(RegistroActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
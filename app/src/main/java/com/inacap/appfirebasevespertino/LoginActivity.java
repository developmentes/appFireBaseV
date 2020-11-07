package com.inacap.appfirebasevespertino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
EditText txtemail,txtclave;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        txtemail = findViewById(R.id.txt_login_email);
        txtclave = findViewById(R.id.txt_login_pass);
    }

    public void cargarRegistro(View view) {

        Intent intent = new Intent(this,RegistroActivity.class);
            startActivity(intent);

    }

    public void acceder(View view) {

        String email =txtemail.getText().toString();
       String pass = txtclave.getText().toString();


       auth.signInWithEmailAndPassword(email,pass)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                           finish();//destruye la actividad


                       }else {
                           Toast.makeText(LoginActivity.this,"Error de acceso revise las credenciales",Toast.LENGTH_LONG).show();

                       }
                   }
               });

    }
}
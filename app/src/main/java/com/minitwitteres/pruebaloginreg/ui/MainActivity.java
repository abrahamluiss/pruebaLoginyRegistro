package com.minitwitteres.pruebaloginreg.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.minitwitteres.pruebaloginreg.R;
import com.minitwitteres.pruebaloginreg.retrofit.pruebaClient;
import com.minitwitteres.pruebaloginreg.retrofit.pruebaServicio;
import com.minitwitteres.pruebaloginreg.retrofit.respuesta.RespuestaLogin;
import com.minitwitteres.pruebaloginreg.retrofit.solicitar.SolicitarLogin;
import com.minitwitteres.pruebaloginreg.retrofit.solicitar.SolicitudCambiarPass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail, edtPass;
    Button btnLogin, btnIrRegistro, txtIrCambiarPass;
    pruebaClient _pruebaClient;
    pruebaServicio _pruebaServicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitInit();
        findViews();
        events();
    }


    private void retrofitInit() {
        _pruebaClient = pruebaClient.getInstance();
        _pruebaServicio = _pruebaClient.getPruebaServicio();
    }
    private void findViews() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnIrRegistro = findViewById(R.id.btnIrRegistro);
        txtIrCambiarPass= findViewById(R.id.txtIrCambioPass);
    }


    private void events() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                irALogin();
            }
        });



        btnIrRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irARegistro();
            }
        });
        txtIrCambiarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CambiasPassActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    private void irARegistro() {
        Intent i = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(i);
        finish();
    }

    private void irALogin() {
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        if(email.isEmpty()){
            edtEmail.setError("El email es requerido");
        } else if(pass.isEmpty()){
            edtPass.setError("Contraseña requerida");
        } else {
            SolicitarLogin solicitarLogin =new SolicitarLogin(email, pass);
            Call<RespuestaLogin> call = _pruebaServicio.doLogin(solicitarLogin);
            call.enqueue(new Callback<RespuestaLogin>() {
                @Override
                public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
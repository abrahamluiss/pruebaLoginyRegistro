package com.minitwitteres.pruebaloginreg.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.minitwitteres.pruebaloginreg.R;
import com.minitwitteres.pruebaloginreg.retrofit.pruebaClient;
import com.minitwitteres.pruebaloginreg.retrofit.pruebaServicio;
import com.minitwitteres.pruebaloginreg.retrofit.respuesta.RespuestaLogin;
import com.minitwitteres.pruebaloginreg.retrofit.respuesta.RespuestaRegistro;
import com.minitwitteres.pruebaloginreg.retrofit.solicitar.SolicitarLogin;
import com.minitwitteres.pruebaloginreg.retrofit.solicitar.SolicitarRegistro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {
    EditText edtNombre, edtEmail, edtContrasena, edtTelefono;
    Button btnRegistro;
    pruebaClient _pruebaClient;
    pruebaServicio _pruebaServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        retrofitInit();
        findViews();
        events();

    }

    private void retrofitInit() {
        _pruebaClient = pruebaClient.getInstance();
        _pruebaServicio = _pruebaClient.getPruebaServicio();
    }
    private void findViews() {
        edtNombre = findViewById(R.id.edtRegNombre);
        edtTelefono = findViewById(R.id.edtRegPhone);
        edtEmail = findViewById(R.id.edtRegEmail);
        edtContrasena = findViewById(R.id.edtRegPass);
        btnRegistro = findViewById(R.id.btnRegistro);
    }


    private void events() {
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                irALogin();
            }
        });
    }

    private void irALogin() {
        String name = edtNombre.getText().toString();
        String telefono = edtTelefono.getText().toString();
        String email = edtEmail.getText().toString();
        String apellido = "null";
        String direccion = "null";
        String nacimiento = "null";
        String pass = edtContrasena.getText().toString();
        String userTipo = "Cliente";
        String nacionalidad = "PERU";
        if(name.isEmpty()){
            edtNombre.setError("El nombre es requerido");
        }else if(telefono.isEmpty()) {
            edtTelefono.setError("El telefono es requerido");
        }else if(email.isEmpty()){
                edtEmail.setError("El email es requerido");
            } else if(pass.isEmpty()){
                edtContrasena.setError("Contraseña requerida");
            } else {
                SolicitarRegistro solicitarRegistro =new SolicitarRegistro(name,apellido,direccion,nacimiento,nacionalidad,telefono, email, pass, userTipo);
                Call<RespuestaRegistro> call = _pruebaServicio.doSignUp(solicitarRegistro);
                call.enqueue(new Callback<RespuestaRegistro>() {
                    @Override
                    public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(RegistroActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegistroActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(RegistroActivity.this, "Revise sus datos de registro", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                        Toast.makeText(RegistroActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

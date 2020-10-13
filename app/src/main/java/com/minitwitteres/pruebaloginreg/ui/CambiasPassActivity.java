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
        import com.minitwitteres.pruebaloginreg.retrofit.respuesta.RespuestaRegistro;
        import com.minitwitteres.pruebaloginreg.retrofit.solicitar.SolicitudCambiarPass;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class CambiasPassActivity extends AppCompatActivity {

    EditText edtID, edtPass;
    Button btnCambiarPass;
    TextView txtIrALogin;
    pruebaClient _pruebaClient;
    pruebaServicio _pruebaServicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambias_pass);
        retrofit();
        findView();
        events();


    }

    private void findView() {
        edtID = findViewById(R.id.edtChangeID);
        edtPass = findViewById(R.id.edtChangePass);
        btnCambiarPass = findViewById(R.id.btnConfirmarCambioPass);
        txtIrALogin = findViewById(R.id.txtIrLogin);

    }

    private void retrofit() {
        _pruebaClient = pruebaClient.getInstance();
        _pruebaServicio = _pruebaClient.getPruebaServicio();

    }
    private void events() {
        btnCambiarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarPass();
            }
        });

    }

    private void cambiarPass() {
        Integer id = Integer.valueOf(edtID.getText().toString());
        String pass = edtPass.getText().toString();
       if(pass.isEmpty()){
            edtPass.setError("Contraseña requerida");
        } else {
            SolicitudCambiarPass solicitudCambiarPass =new SolicitudCambiarPass(id, pass);
            Call<RespuestaRegistro> call = _pruebaServicio.doChangePass(solicitudCambiarPass);
            call.enqueue(new Callback<RespuestaRegistro>() {
                @Override
                public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(CambiasPassActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CambiasPassActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(CambiasPassActivity.this, "Revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                    Toast.makeText(CambiasPassActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}
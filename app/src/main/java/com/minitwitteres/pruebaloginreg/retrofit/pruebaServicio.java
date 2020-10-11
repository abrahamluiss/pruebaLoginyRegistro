package com.minitwitteres.pruebaloginreg.retrofit;

import com.minitwitteres.pruebaloginreg.retrofit.respuesta.RespuestaLogin;
import com.minitwitteres.pruebaloginreg.retrofit.respuesta.RespuestaRegistro;
import com.minitwitteres.pruebaloginreg.retrofit.solicitar.SolicitarLogin;
import com.minitwitteres.pruebaloginreg.retrofit.solicitar.SolicitarRegistro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface pruebaServicio {


    @POST("Usuario/login.php")
    Call<RespuestaLogin> doLogin(@Body SolicitarLogin solicitarLogin);

    @POST("Usuario/Insert_Usuario.php")
    Call<RespuestaRegistro> doSignUp(@Body SolicitarRegistro solicitarRegistro);

}

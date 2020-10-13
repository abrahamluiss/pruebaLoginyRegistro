
package com.minitwitteres.pruebaloginreg.retrofit.solicitar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudCambiarPass {

    @SerializedName("ID_Usuario")
    @Expose
    private Integer iDUsuario;
    @SerializedName("US_Contrasena")
    @Expose
    private String uSContrasena;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SolicitudCambiarPass() {
    }

    /**
     * 
     * @param uSContrasena
     * @param iDUsuario
     */
    public SolicitudCambiarPass(Integer iDUsuario, String uSContrasena) {
        super();
        this.iDUsuario = iDUsuario;
        this.uSContrasena = uSContrasena;
    }

    public Integer getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getUSContrasena() {
        return uSContrasena;
    }

    public void setUSContrasena(String uSContrasena) {
        this.uSContrasena = uSContrasena;
    }

}

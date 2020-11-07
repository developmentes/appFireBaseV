package com.inacap.appfirebasevespertino.model;

public class Usuario {

    private String email;
    private String nombre;
    private String uid;

    public Usuario(){};

    public Usuario(String email, String nombre, String uid) {
        this.email = email;
        this.nombre = nombre;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

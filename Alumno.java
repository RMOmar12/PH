package com.plataforamasHeterogeneas.proyectoAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize
public class Alumno implements Serializable {
    @JsonProperty("id")
    private long id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("email")
    private String email;

    public Alumno(){

    }
    public Alumno(long id, String nombre, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
    }


    public long getId() {
        return this.id;
    }

    public String getDireccion(){return this.direccion;}

    public String getNombre(){return this.nombre;}
    public String getCorreo(){return this.email;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setCorreo(String correo){
        this.email = correo;
    }


}

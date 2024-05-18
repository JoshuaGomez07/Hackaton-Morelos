package com.example;

public class Donante {

    private static String nombre;
    private int edad;
    private String tipoSangre;
    private double peso;
    private double nivelHierro;
    private String alergias;

    public Donante(String nombre, int edad, String tipoSangre, double peso, double nivelHierro, String alergias) {
        Donante.nombre = nombre;
        this.edad = edad;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.nivelHierro = nivelHierro;
        this.alergias = alergias;
    }

    public Donante(int idDonante, String nombre2, int edad2, String tipoSangre2, double peso2, double nivelHierro2,
            String alergias2) {
     
    }

    public static String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Donante.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getNivelHierro() {
        return nivelHierro;
    }

    public void setNivelHierro(double nivelHierro) {
        this.nivelHierro = nivelHierro;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public int getIdDonante() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getIdDonante'");
    }

    public String getCorreoElectronico() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getCorreoElectronico'");
    }

    public String getGenero() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getGenero'");
    }

}


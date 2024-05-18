package com.example;

public class Paciente {

    private String nombre;
    private int edad;
    private String tipoSangreNecesaria;
    private String estadoDeSalud;

    public Paciente(String nombre, int edad, String tipoSangreNecesaria, String estadoDeSalud) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipoSangreNecesaria = tipoSangreNecesaria;
        this.estadoDeSalud = estadoDeSalud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoSangreNecesaria() {
        return tipoSangreNecesaria;
    }

    public void setTipoSangreNecesaria(String tipoSangreNecesaria) {
        this.tipoSangreNecesaria = tipoSangreNecesaria;
    }

    public String getEstadoDeSalud() {
        return estadoDeSalud;
    }

    public void setEstadoDeSalud(String estadoDeSalud) {
        this.estadoDeSalud = estadoDeSalud;
    }

    public String getTipoSangre() {

        throw new UnsupportedOperationException("Unimplemented method 'getTipoSangre'");
    }

    public double getPesoMinimoDonante() {
 
        throw new UnsupportedOperationException("Unimplemented method 'getPesoMinimoDonante'");
    }

    public double getNivelHierroMinimoDonante() {
   
        throw new UnsupportedOperationException("Unimplemented method 'getNivelHierroMinimoDonante'");
    }

    public String getAlergias() {

        throw new UnsupportedOperationException("Unimplemented method 'getAlergias'");
    }

    public int getIdPaciente() {

        throw new UnsupportedOperationException("Unimplemented method 'getIdPaciente'");
    }

}


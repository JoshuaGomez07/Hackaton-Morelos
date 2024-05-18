package com.example;

import java.util.ArrayList;
import java.util.List;

public class ChatbotAlrgotimo {
public List<Donante> identificarDonantesIdoneos(Paciente paciente, BaseDatosDonantes db) {
    // 1. Consultar la base de datos para obtener todos los donantes
    List<Donante> donantes = db.obtenerTodosLosDonantes();

    // 2. Aplicar el algoritmo de Machine Learning para filtrar donantes
    List<Donante> donantesIdoneos = new ArrayList<>();
    for (Donante donante : donantes) {
        if (esDonanteIdoneo(paciente, donante)) {
            donantesIdoneos.add(donante);
        }
    }

    // 3. Devolver la lista de donantes idóneos
    return donantesIdoneos;
}

private boolean esDonanteIdoneo(Paciente paciente, Donante donante) {
    // 1. Verificar el tipo de sangre
    if (!esTipoSangreCompatible(paciente.getTipoSangre(), donante.getTipoSangre())) {
        return false;
    }

    // 2. Verificar el peso
    if (donante.getPeso() < getPesoMinimoDonante(donante.getGenero())) {
        return false;
    }

    // 3. Verificar el nivel de hierro
    if (donante.getNivelHierro() < getNivelHierroMinimoDonante(donante.getGenero())) {
        return false;
    }

    // 4. Verificar las alergias
    if (donante.getAlergias().contains("alergia que contraindica la donación")) {
        return false;
    }

    return true;
}

private boolean esTipoSangreCompatible(String tipoSangrePaciente, String tipoSangreDonante) {
    switch (tipoSangrePaciente) {
        case "A+":
            return tipoSangreDonante.equals("A+") || tipoSangreDonante.equals("A-") ||
                    tipoSangreDonante.equals("O+") || tipoSangreDonante.equals("O-");
        case "A-":
            return tipoSangreDonante.equals("A-") || tipoSangreDonante.equals("O-");
        case "B+":
            return tipoSangreDonante.equals("B+") || tipoSangreDonante.equals("B-") ||
                    tipoSangreDonante.equals("O+") || tipoSangreDonante.equals("O-");
        case "B-":
            return tipoSangreDonante.equals("B-") || tipoSangreDonante.equals("O-");
        case "AB+":
            return true; // Todos los tipos de sangre son compatibles
        case "AB-":
            return tipoSangreDonante.equals("A-") || tipoSangreDonante.equals("B-") || tipoSangreDonante.equals("O-");
        case "O+":
            return tipoSangreDonante.equals("O+") || tipoSangreDonante.equals("O-");
        case "O-":
            return tipoSangreDonante.equals("O-");
        default:
            return false; // Tipo de sangre del paciente no reconocido
    }
}


    private double getPesoMinimoDonante(String genero) {
    if (genero.equals("M")) {
        return 55.0; // Peso mínimo para hombres
    } else {
        return 50.0; // Peso mínimo para mujeres
    }
}

    private double getNivelHierroMinimoDonante(String genero) {
    if (genero.equals("M")) {
        return 13.0; // Nivel mínimo de hierro para hombres
    } else {
        return 12.5; // Nivel mínimo de hierro para mujeres
    }
}

    @Override
    public String toString() {
        return "MLAlgorithm []";
    }


}

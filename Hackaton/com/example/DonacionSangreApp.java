package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonacionSangreApp {

    private static final String URL = "jdbc:mysql://localhost:3306/DonaSangre";
    private static final String USER = "id22181966_root";
    private static final String PASSWORD = "HackatonM135*";

    private Connection connect() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    public void agregarNuevoDonante(String nombre, int edad, String tipoSangre, double peso, double nivelHierro, String alergias) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Donantes (nombre, edad, tipo_sangre, peso, nivel_hierro, alergias) VALUES (?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.setString(3, tipoSangre);
            pstmt.setDouble(4, peso);
            pstmt.setDouble(5, nivelHierro);
            pstmt.setString(6, alergias);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nuevo donante agregado exitosamente!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar nuevo donante", e);
        }
    }

    public List<Donante> buscarDonantesPorTipoSangre(String tipoSangre) {
        List<Donante> donantes = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("SELECT nombre, edad, peso, nivel_hierro, alergias FROM Donantes WHERE tipo_sangre = ?")) {
            pstmt.setString(1, tipoSangre);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                double peso = rs.getDouble("peso");
                double nivelHierro = rs.getDouble("nivel_hierro");
                String alergias = rs.getString("alergias");

                Donante donante = new Donante(nombre, edad, tipoSangre, peso, nivelHierro, alergias);
                donantes.add(donante);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar donantes por tipo de sangre", e);
        }
        return donantes;
    }

    public List<Donante> identificarDonantesIdoneos(Paciente paciente, BaseDatosDonantes db) throws SQLException {
        List<Donante> candidatos = db.obtenerDonantesCandidatos(paciente);
        List<Donante> idoneos = new ArrayList<>();

        for (Donante candidato : candidatos) {
            if (algoritmo.esCompatible(paciente, candidato)) {
                idoneos.add(candidato);
            }
        }

        return idoneos;
    }

    public static void notificarDonantes(List<Donante> donantes) {
        for (Donante donante : donantes) {
            notificarDonante(donante);
        }
    }

    private static void notificarDonante(Donante donante) {
        // Implementar la lógica para enviar la notificación al donante
        // (por correo electrónico, SMS o llamada telefónica)
        System.out.println("Notificando al donante: " + donante.getNombre());
    }

    public static void main(String[] args) throws Exception {
        BaseDatosDonantes db = new BaseDatosDonantes();
        try (Connection conn = db.connect()) {
            if (conn != null) {
                System.out.println("Conexión a la base de datos establecida correctamente.");
            } else {
                System.err.println("Error al conectar a la base de datos.");
            }
        }
    }
}
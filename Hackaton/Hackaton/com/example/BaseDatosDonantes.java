package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDatosDonantes {

    private final Connection conn;

    public BaseDatosDonantes() throws SQLException {
        this.conn = connect();
    }

    Connection connect() throws SQLException {
        return DriverManager.getConnection("https://redvital.000webhostapp.com/", "id22181966_root", "HackatonM135*");
    }

    public List<Donante> obtenerDonantesCandidatos(Paciente paciente) throws SQLException {
        String sql = "SELECT * FROM Donantes WHERE tipo_sangre = ? AND peso >= ? AND nivel_hierro >= ? AND NOT alergias LIKE ? AND id_donante != ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, paciente.getTipoSangre());
            pstmt.setDouble(2, paciente.getPesoMinimoDonante());
            pstmt.setDouble(3, paciente.getNivelHierroMinimoDonante());
            pstmt.setString(4, "%" + paciente.getAlergias() + "%");
            pstmt.setInt(5, paciente.getIdPaciente());

            ResultSet rs = pstmt.executeQuery();
            List<Donante> donantesCandidatos = new ArrayList<>();
            while (rs.next()) {
                donantesCandidatos.add(new Donante(
                        rs.getInt("id_donante"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("tipo_sangre"),
                        rs.getDouble("peso"),
                        rs.getDouble("nivel_hierro"),
                        rs.getString("alergias")
                ));
            }
            return donantesCandidatos;
        }
    }

    public void guardarDonante(Donante donante) throws SQLException {
        String sql = "INSERT INTO Donantes (nombre, edad, tipo_sangre, peso, nivel_hierro, alergias) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Donante.getNombre());
            pstmt.setInt(2, donante.getEdad());
            pstmt.setString(3, donante.getTipoSangre());
            pstmt.setDouble(4, donante.getPeso());
            pstmt.setDouble(5, donante.getNivelHierro());
            pstmt.setString(6, donante.getAlergias());

            pstmt.executeUpdate();
            System.out.println("Donante guardado exitosamente!");
        }
    }

    public void eliminarDonante(int idDonante) throws SQLException {
        String sql = "DELETE FROM Donantes WHERE id_donante = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDonante);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Donante con ID " + idDonante + " eliminado exitosamente!");
            } else {
                System.err.println("No se encontró ningún donante con ID " + idDonante);
            }
        }
    }

    public void actualizarDonante(Donante donante) throws SQLException {
        String sql = "UPDATE Donantes SET nombre = ?, edad = ?, tipo_sangre = ?, peso = ?, nivel_hierro = ?, alergias = ? WHERE id_donante = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Donante.getNombre());
            pstmt.setInt(2, donante.getEdad());
            pstmt.setString(3, donante.getTipoSangre());
            pstmt.setDouble(4, donante.getPeso());
            pstmt.setDouble(5, donante.getNivelHierro());
            pstmt.setString(6, donante.getAlergias());
            pstmt.setInt(7, donante.getIdDonante());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Donante con ID " + donante.getIdDonante() + " actualizado exitosamente!");
            } else {
                System.err.println("No se encontró ningún donante con ID " + donante);
 }
        }
    }

    public List<Donante> obtenerTodosLosDonantes() {
        
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodosLosDonantes'");
    }
}
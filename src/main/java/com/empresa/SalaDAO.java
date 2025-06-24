package com.empresa;

import com.empresa.modelo.sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
    public void insertarSala(sala sala) {
        String sql = "INSERT INTO sala (nombre, capacidad, recursos) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sala.getNombre());
            stmt.setInt(2, sala.getCapacidad());
            stmt.setString(3, sala.getRecursos());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<sala> obtenerSalas() {
        List<sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM sala";

        try (Connection conn = ConexionBd.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sala sala = new sala(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("capacidad"),
                        rs.getString("recursos")
                );
                salas.add(sala);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salas;
    }

    public void actualizarSala(sala sala) {
        String sql = "UPDATE sala SET nombre = ?, capacidad = ?, recursos = ? WHERE id = ?";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sala.getNombre());
            stmt.setInt(2, sala.getCapacidad());
            stmt.setString(3, sala.getRecursos());
            stmt.setInt(4, sala.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarSala(int id) {
        String sql = "DELETE FROM sala WHERE id = ?";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package com.empresa;

import com.empresa.modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public void insertarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (nombre, email, departamento) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getEmail());
            stmt.setString(3, empleado.getDepartamento());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (Connection conn = ConexionBd.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("departamento")
                );
                empleados.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    public void actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleado SET nombre = ?, email = ?, departamento = ? WHERE id = ?";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getEmail());
            stmt.setString(3, empleado.getDepartamento());
            stmt.setInt(4, empleado.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE id = ?";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package com.empresa;

import com.empresa.modelo.Reserva;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public boolean hayConflicto(Reserva reserva) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE id_sala = ? AND fecha = ? " +
                "AND ((hora_inicio < ? AND hora_fin > ?) OR (hora_inicio < ? AND hora_fin > ?))";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdSala());
            stmt.setDate(2, Date.valueOf(reserva.getFecha()));
            stmt.setTime(3, Time.valueOf(reserva.getHoraFin()));
            stmt.setTime(4, Time.valueOf(reserva.getHoraInicio()));
            stmt.setTime(5, Time.valueOf(reserva.getHoraInicio()));
            stmt.setTime(6, Time.valueOf(reserva.getHoraFin()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertarReserva(Reserva reserva) {
        if (hayConflicto(reserva)) {
            System.out.println("Conflicto detectado: la sala ya esta reservada en ese horario.");
            return false;
        }

        String sql = "INSERT INTO reserva (id_empleado, id_sala, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdEmpleado());
            stmt.setInt(2, reserva.getIdSala());
            stmt.setDate(3, Date.valueOf(reserva.getFecha()));
            stmt.setTime(4, Time.valueOf(reserva.getHoraInicio()));
            stmt.setTime(5, Time.valueOf(reserva.getHoraFin()));
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection conn = ConexionBd.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reserva r = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("id_empleado"),
                        rs.getInt("id_sala"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime()
                );
                reservas.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    public void eliminarReserva(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

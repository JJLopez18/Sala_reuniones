package com.empresa;

import com.empresa.modelo.Reserva;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaDAOTest {
    ReservaDAO reservaDAO = new ReservaDAO();

    @Test
    void testInsertarYObtenerReserva() {
        Reserva reserva = new Reserva(0, 1, 1, LocalDate.now(), LocalTime.of(10,0), LocalTime.of(11,0));
        boolean insertado = reservaDAO.insertarReserva(reserva);
        List<Reserva> reservas = reservaDAO.obtenerReservas();
        assertTrue(insertado && !reservas.isEmpty());
    }
}

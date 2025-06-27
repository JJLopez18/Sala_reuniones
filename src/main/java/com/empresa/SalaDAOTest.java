package com.empresa;

import com.empresa.modelo.sala;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SalaDAOTest {
    SalaDAO salaDAO = new SalaDAO();

    @Test
    void testInsertarYObtenerSala() {
        sala sala = new sala(0, "Sala Test", 15, "Proyector");
        salaDAO.insertarSala(sala);
        List<sala> salas = salaDAO.obtenerSalas();
        assertTrue(salas.stream().anyMatch(s -> s.getNombre().equals("Sala Test")));
    }
}

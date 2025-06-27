package com.empresa;

import com.empresa.modelo.Empleado;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoDAOTest {
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Test
    void testInsertarYObtenerEmpleado() {
        Empleado empleado = new Empleado(0, "Test Empleado", "test@correo.com", "IT");
        empleadoDAO.insertarEmpleado(empleado);
        List<Empleado> empleados = empleadoDAO.obtenerEmpleados();
        assertTrue(empleados.stream().anyMatch(e -> e.getNombre().equals("Test Empleado")));
    }
}

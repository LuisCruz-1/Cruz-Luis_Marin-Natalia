package test;

import dao.impl.DaoH2Odontologo;
import model.Odontologo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTestH2 {
    private static OdontologoService odontologoService;

    @BeforeAll
    static void setUp() {
        odontologoService = new OdontologoService(new DaoH2Odontologo());
    }

    @Test
    void testGuardarYListarOdontologos() {
        Odontologo odontologo1 = new Odontologo("M001", "Juan", "Pérez");
        Odontologo odontologo2 = new Odontologo("M002", "María", "Gómez");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertFalse(odontologos.isEmpty());
        assertTrue(odontologos.size() >= 2);
    }
}
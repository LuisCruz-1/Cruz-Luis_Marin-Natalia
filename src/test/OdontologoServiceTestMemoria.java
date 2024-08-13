package test;

import dao.impl.DaoMemoriaOdontologo;
import model.Odontologo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTestMemoria {
    private OdontologoService odontologoService;

    @BeforeEach
    void setUp() {
        odontologoService = new OdontologoService(new DaoMemoriaOdontologo());
    }

    @Test
    void testGuardarYListarOdontologos() {
        Odontologo odontologo1 = new Odontologo("M001", "Juan", "Pérez");
        Odontologo odontologo2 = new Odontologo("M002", "María", "Gómez");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertEquals(2, odontologos.size());
    }
}
package dao.impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DaoMemoriaOdontologo implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(DaoMemoriaOdontologo.class);
    private List<Odontologo> odontologos = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(nextId++);
        odontologos.add(odontologo);
        logger.info("Odontólogo guardado en memoria: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("Odontólogos listados de memoria: " + odontologos.size());
        return new ArrayList<>(odontologos);
    }
}

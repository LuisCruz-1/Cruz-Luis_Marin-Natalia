package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoH2Odontologo implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(DaoH2Odontologo.class);
    private static final String INSERT = "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                odontologo.setId(keys.getInt(1));
            logger.info("Odontólogo guardado: " + odontologo);
            return odontologo;
        } catch (Exception e) {
            logger.error("Error al guardar odontólogo: " + e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(
                        resultSet.getInt("ID"),
                        resultSet.getString("NUMERO_MATRICULA"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("APELLIDO")
                );
                odontologos.add(odontologo);
            }
            logger.info("Odontólogos listados: " + odontologos.size());
            return odontologos;
        } catch (Exception e) {
            logger.error("Error al listar odontólogos: " + e.getMessage());
            return odontologos;
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

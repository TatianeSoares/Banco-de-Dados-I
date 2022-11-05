package DAO.Sinalizacao;

import model.Ultrapassagem;
import org.omnifaces.util.Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PgUltrapassagemDAO implements UltrapassagemDAO{


    @Override
    public void create(Ultrapassagem ultrapassagem) throws SQLException {
    }

    @Override
    public Ultrapassagem read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(Ultrapassagem ultrapassagem) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public List<Ultrapassagem> all() throws SQLException {
        return null;
    }
}

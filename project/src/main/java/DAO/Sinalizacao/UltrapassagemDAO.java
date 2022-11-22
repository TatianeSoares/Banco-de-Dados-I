package DAO.Sinalizacao;

import model.Acidente;
import model.Ultrapassagem;
import DAO.DAO;

import java.sql.SQLException;
import java.util.List;

public interface UltrapassagemDAO extends DAO<Ultrapassagem> {

    public static void adicionarUltrapassagem(Ultrapassagem ultrapassagem) throws SQLException{

    }
    public Ultrapassagem getUltrapassagem(int id) throws SQLException;
    public List<Ultrapassagem> getTodasUltrapassagens() throws SQLException;
}

package DAO.Sinalizacao;


import DAO.DAO;
import model.Ultrapassagem;
import model.VelocidadeMaxima;

import java.sql.SQLException;
import java.util.List;

public interface VelocidadeMaximaDAO extends DAO<VelocidadeMaxima> {

    public static void adicionarVelocidadeMaxima(VelocidadeMaxima velocidadeMaxima) throws SQLException{

    }
    public VelocidadeMaxima getVelocidadeMaxima(int id) throws SQLException;

    public List<VelocidadeMaxima> getTodasVelocidadeMaximas() throws SQLException;

}

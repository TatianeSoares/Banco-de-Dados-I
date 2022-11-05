package DAO.Sinalizacao;


import model.Ultrapassagem;
import model.VelocidadeMaxima;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PgVelocidadeMaximaDAO implements VelocidadeMaximaDAO{

    private final Connection connection;

    private static final String INSERT_VELOCIDADE_MAXIMA =
            "INSERT INTO rodovia.acidente(idVelocidadeMaxima," +
                    "situacao, uf, anoPnvSnc, veloVeicPesado, veloVeicLeve, latitude" +
                    "longitude, municipio, km)" +
                    "VALUES();";

    private static final String BUSCA_TODAS_VELOCIDADES =
            "SELECT situacao, uf, anoPnvSnc, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km" +
                    "FROM rodovia.velocidadeMaxima" +
                    "ORDER BY km;";

    private static final String BUSCA_VELOCIDADE_MAXIMA =
            "SELECT situacao, uf, anoPnvSnc, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km" +
                    "FROM rodovia.velocidadeMaxima" +
                    "WHERE id = ?;";

    private static final String UPDATE_VELOCIDADE_MAXIMA =
            "UPDATE FROM rodovia.velocidadeMaxima" +
                    "SET " +
                    "situacao = " +
                    "uf = " +
                    "anoPnvSnc =" +
                    "veloVeicPesado =" +
                    "veloVeicLeve =" +
                    "latitude =" +
                    "longitude =" +
                    "municipio =" +
                    "km =" +
                    "WHERE id = ;";

    private static final String DELETE_VELOCIDADE_MAXIMA =
            "DELETE FROM rodovia.velocidadeMaxima" +
                    "WHERE id = ;";

    public PgVelocidadeMaximaDAO(Connection connection) {
        this.connection = connection;
    }

    // TODO n sei se ta certo essas duas abaixo
    public VelocidadeMaxima getVelocidadeMaxima(int id) {
        return null;
    }


    public List<VelocidadeMaxima> getTodasVelocidadesMaximas() {
        return null;
    }
    // TODO get especificos

    @Override
    public void create(VelocidadeMaxima velocidadeMaxima) throws SQLException {

    }

    @Override
    public VelocidadeMaxima read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(VelocidadeMaxima velocidadeMaxima) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public List<VelocidadeMaxima> all() throws SQLException {
        return null;
    }
}

package DAO.Sinalizacao;


import model.Ultrapassagem;
import model.VelocidadeMaxima;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgVelocidadeMaximaDAO implements VelocidadeMaximaDAO{

    private final Connection connection;

    private static final String INSERT_VELOCIDADE_MAXIMA =
            "INSERT INTO rodovia.acidente(idVelocidadeMaxima," +
                    "situacao, uf, anoPnvSnc, veloVeicPesado, veloVeicLeve, latitude" +
                    "longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)" +
                    "VALUES();";

    private static final String BUSCA_TODAS_VELOCIDADES =
            "SELECT situacao, uf, anoPnvSnc, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista" +
                    "FROM rodovia.velocidadeMaxima" +
                    "ORDER BY km;";

    private static final String BUSCA_VELOCIDADE_MAXIMA =
            "SELECT situacao, uf, anoPnvSnc, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista" +
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
                    "idTrechoRodovia =" +
                    "idSentidoRodovia =" +
                    "idTipoPista =" +
                    "WHERE id = ;";

    private static final String DELETE_VELOCIDADE_MAXIMA =
            "DELETE FROM rodovia.velocidadeMaxima" +
                    "WHERE id = ;";

    public PgVelocidadeMaximaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarVelocidadeMaxima(VelocidadeMaxima velocidadeMaxima) throws SQLException {
        create(velocidadeMaxima);
    }
    public VelocidadeMaxima getVelocidadeMaxima(int id) throws SQLException {
        return read(id);

    }
    public List<VelocidadeMaxima> getTodasVelocidadeMaximas() throws SQLException {
        return all();
    }

    @Override
    public void create(VelocidadeMaxima velocidadeMaxima) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_VELOCIDADE_MAXIMA)) {
            statement.setString(1, velocidadeMaxima.getSituacao());
            statement.setString(2, velocidadeMaxima.getUf());
            statement.setInt(3, velocidadeMaxima.getAnoPnvSnc());
            statement.setFloat(4, velocidadeMaxima.getVeloVeicPesado());
            statement.setFloat(5, velocidadeMaxima.getVeloVeicLeve());
            statement.setFloat(6, velocidadeMaxima.getLatitude());
            statement.setFloat(7, velocidadeMaxima.getLongitude());
            statement.setString(8, velocidadeMaxima.getMunicipio());
            statement.setFloat(9, velocidadeMaxima.getKm());
            statement.setInt(10, velocidadeMaxima.getIdTrechoRodovia());
            statement.setInt(11, velocidadeMaxima.getIdSentidoRodovia());
            statement.setInt(12, velocidadeMaxima.getIdTipoPista());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Messages.addGlobalError("Erro ao inserir placa de velocidade máxima");
        }
    }

    @Override
    public VelocidadeMaxima read(Integer id) throws SQLException {
        VelocidadeMaxima velocidadeMaxima = new VelocidadeMaxima();

        try (PreparedStatement statement = connection.prepareStatement(BUSCA_VELOCIDADE_MAXIMA)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    velocidadeMaxima.setIdVelocidadeMaxima(result.getInt("idVelocidadeMaxima"));
                    velocidadeMaxima.setSituacao(result.getString("situacao"));
                    velocidadeMaxima.setUf(result.getString("uf"));
                    velocidadeMaxima.setAnoPnvSnc(result.getInt("anoPnvSnc"));
                    velocidadeMaxima.setVeloVeicPesado(result.getFloat("veloVeicPesado"));
                    velocidadeMaxima.setVeloVeicLeve(result.getFloat("veloVeicLeve"));
                    velocidadeMaxima.setLatitude(result.getFloat("latitude"));
                    velocidadeMaxima.setLongitude(result.getFloat("longitude"));
                    velocidadeMaxima.setMunicipio(result.getString("municipio"));
                    velocidadeMaxima.setKm(result.getFloat("km"));
                    velocidadeMaxima.setIdTrechoRodovia(result.getInt("idTrechoRodovia"));
                    velocidadeMaxima.setIdSentidoRodovia(result.getInt("idSentidoRodovia"));
                    velocidadeMaxima.setIdTipoPista(result.getInt("idTipoPista"));
                } else {
                    throw new SQLException("Erro ao visualizar: placa de velocidade máxima não pode ser encontrada.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgVelocidadeMaximaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
        }
        return velocidadeMaxima;
    }

    @Override
    public void update(VelocidadeMaxima velocidadeMaxima) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_VELOCIDADE_MAXIMA)) {
            statement.setInt(1, velocidadeMaxima.getIdVelocidadeMaxima());
            statement.setString(2, velocidadeMaxima.getSituacao());
            statement.setString(3, velocidadeMaxima.getUf());
            statement.setInt(4, velocidadeMaxima.getAnoPnvSnc());
            statement.setFloat(5, velocidadeMaxima.getVeloVeicPesado());
            statement.setFloat(6, velocidadeMaxima.getVeloVeicLeve());
            statement.setFloat(7, velocidadeMaxima.getLatitude());
            statement.setFloat(8, velocidadeMaxima.getLongitude());
            statement.setString(9, velocidadeMaxima.getMunicipio());
            statement.setFloat(10, velocidadeMaxima.getKm());
            statement.setInt(11, velocidadeMaxima.getIdTrechoRodovia());
            statement.setInt(12, velocidadeMaxima.getIdSentidoRodovia());
            statement.setInt(13, velocidadeMaxima.getIdTipoPista());

        } catch (SQLException ex) {
            Logger.getLogger(PgVelocidadeMaximaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_VELOCIDADE_MAXIMA)) {
            statement.setInt(1, id);

        } catch (SQLException ex) {
            Logger.getLogger(PgVelocidadeMaximaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
        }
    }

    @Override
    public List<VelocidadeMaxima> all() throws SQLException {
        List<VelocidadeMaxima> velocidadeMaximaList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODAS_VELOCIDADES);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                VelocidadeMaxima velocidadeMaxima = new VelocidadeMaxima();
                velocidadeMaxima.setIdVelocidadeMaxima(result.getInt("idVelocidadeMaxima"));
                velocidadeMaxima.setSituacao(result.getString("situacao"));
                velocidadeMaxima.setUf(result.getString("uf"));
                velocidadeMaxima.setAnoPnvSnc(result.getInt("anoPnvSnc"));
                velocidadeMaxima.setVeloVeicPesado(result.getFloat("veloVeicPesado"));
                velocidadeMaxima.setVeloVeicLeve(result.getFloat("veloVeicLeve"));
                velocidadeMaxima.setLatitude(result.getFloat("latitude"));
                velocidadeMaxima.setLongitude(result.getFloat("longitude"));
                velocidadeMaxima.setMunicipio(result.getString("municipio"));
                velocidadeMaxima.setKm(result.getFloat("km"));
                velocidadeMaxima.setIdTrechoRodovia(result.getInt("idTrechoRodovia"));
                velocidadeMaxima.setIdSentidoRodovia(result.getInt("idSentidoRodovia"));
                velocidadeMaxima.setIdTipoPista(result.getInt("idTipoPista"));

                velocidadeMaximaList.add(velocidadeMaxima);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgVelocidadeMaximaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
        }
        return velocidadeMaximaList;
    }
}

package DAO.Sinalizacao;

import DAO.Acidente.PgAcidenteDAO;
import model.Acidente;
import model.Ultrapassagem;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgUltrapassagemDAO implements UltrapassagemDAO{

    private final Connection connection;

    private static final String INSERT_ULTRAPASSAGEM =
            "INSERT INTO rodovia.ultrapassagem(situacao, uf, anoPnvSnv, kmInicial, kmFinal, longitudeFinal, " +
                    "longitudeInicial, latitudeFinal, latitudeInicial, idTrechoRodovia, idSentidoRodovia, idTipoPista) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String BUSCA_TODAS_ULTRAPASSAGENS =
            "SELECT situacao, uf, anoPnvSnv, kmInicial, kmFinal, longitudeFinal,  longitudeInical, latitudeFinal, latitudeInicial, idTrechoRodovia, idSentidoRodovia, idTipoPista " +
                    "FROM rodovia.ultrapassagem " +
                    "ORDER BY idTrechoRodovia;";

    private static final String BUSCA_ULTRAPASSAGEM =
            "SELECT situacao, uf, anoPnvSnv, kmInicial, kmFinal, longitudeFinal,  longitudeInical, latitudeFinal, latitudeInicial, idTrechoRodovia, idSentidoRodovia, idTipoPista " +
                    "FROM rodovia.ultrapassagem " +
                    "WHERE idTrechoRodovia = ?;";

    private static final String UPDATE_ULTRAPASSAGEM =
            "UPDATE FROM rodovia.ultrapassagem " +
                    "SET " +
                    "situacao = ?," +
                    "uf = ?," +
                    "anoPnvSnv = ?," +
                    "kmInicial = ?," +
                    "kmFinal = ?," +
                    "longitudeFinal = ?," +
                    "longitudeInicial = ?," +
                    "latitudeFinal = ?," +
                    "latitudeInical = ?," +
                    "idTrechoRodovia = ?," +
                    "idSentidoRodovia = ?," +
                    "idTipoPista = ?," +
                    "WHERE idTrechoRodovia = ?;";

    private static final String DELETE_ULTRAPASSAGEM =
            "DELETE FROM rodovia.ultrapassagem " +
                    "WHERE id = ;";
    public PgUltrapassagemDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Ultrapassagem ultrapassagem) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ULTRAPASSAGEM)) {
            statement.setString(1, ultrapassagem.getSituacao());
            statement.setString(2, ultrapassagem.getUf());
            statement.setInt(3, ultrapassagem.getAnoPnvSnc());
            statement.setFloat(4, ultrapassagem.getKmInicial());
            statement.setFloat(5, ultrapassagem.getKmFinal());
            statement.setFloat(6, ultrapassagem.getLongitudeFinal());
            statement.setFloat(7, ultrapassagem.getLongitudeInicial());
            statement.setFloat(8, ultrapassagem.getLatitudeFinal());
            statement.setFloat(9, ultrapassagem.getLatitudeInicial());
            statement.setString(10, ultrapassagem.getIdTrechoRodovia());
            statement.setString(11, ultrapassagem.getIdSentidoRodovia());
            statement.setString(12, ultrapassagem.getIdTipoPista());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Messages.addGlobalError("Erro ao inserir placa de ultrapassagem");
        }
    }

    @Override
    public Ultrapassagem read(String id) throws SQLException {
        Ultrapassagem ultrapassagem = new Ultrapassagem();
//
//        try (PreparedStatement statement = connection.prepareStatement(BUSCA_ULTRAPASSAGEM)) {
//            statement.setInt(1, id);
//            try (ResultSet result = statement.executeQuery()) {
//                if (result.next()) {
//                    ultrapassagem.setIdUltrapassagem(result.getInt("idUltrapassagem"));
//                    ultrapassagem.setSituacao(result.getString("situacao"));
//                    ultrapassagem.setUf(result.getString("uf"));
//                    ultrapassagem.setAnoPnvSnc(result.getInt("anoPnvSnc"));
//                    ultrapassagem.setKmInicial(result.getFloat("kmInicial"));
//                    ultrapassagem.setKmFinal(result.getFloat("kmFinal"));
//                    ultrapassagem.setLongitudeFinal(result.getFloat("longitudeFinal"));
//                    ultrapassagem.setLongitudeInicial(result.getFloat("longitudeInicial"));
//                    ultrapassagem.setLatitudeFinal(result.getFloat("latitudeFinal"));
//                    ultrapassagem.setLatitudeInicial(result.getFloat("latitudeInicial"));
//                    ultrapassagem.setIdTrechoRodovia(result.getString("idTrechoRodovia"));
//                    ultrapassagem.setIdSentidoRodovia(result.getString("idSentidoRodovia"));
//                    ultrapassagem.setIdTipoPista(result.getString("idTipoPista"));
//                } else {
//                    throw new SQLException("Erro ao visualizar: placa de ultrapassagem não pode ser encontrada.");
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(PgUltrapassagemDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
//        }
        return ultrapassagem;
    }

    @Override
    public void update(Ultrapassagem ultrapassagem) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ULTRAPASSAGEM)) {
            statement.setString(2, ultrapassagem.getSituacao());
            statement.setString(3, ultrapassagem.getUf());
            statement.setInt(4, ultrapassagem.getAnoPnvSnc());
            statement.setFloat(5, ultrapassagem.getKmInicial());
            statement.setFloat(6, ultrapassagem.getKmFinal());
            statement.setFloat(7, ultrapassagem.getLongitudeFinal());
            statement.setFloat(8, ultrapassagem.getLongitudeInicial());
            statement.setFloat(9, ultrapassagem.getLatitudeFinal());
            statement.setFloat(10, ultrapassagem.getLatitudeInicial());
            statement.setString(11, ultrapassagem.getIdTrechoRodovia());
            statement.setString(12, ultrapassagem.getIdSentidoRodovia());
            statement.setString(13, ultrapassagem.getIdTipoPista());

        } catch (SQLException ex) {
            Logger.getLogger(PgUltrapassagemDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ULTRAPASSAGEM)) {
            statement.setInt(1, id);

        } catch (SQLException ex) {
            Logger.getLogger(PgUltrapassagemDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
        }
    }

    @Override
    public List<Ultrapassagem> all() throws SQLException {
        List<Ultrapassagem> ultrapassagemList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODAS_ULTRAPASSAGENS);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Ultrapassagem ultrapassagem = new Ultrapassagem();
                ultrapassagem.setIdUltrapassagem(result.getInt("idUltrapassagem"));
                ultrapassagem.setSituacao(result.getString("situacao"));
                ultrapassagem.setUf(result.getString("uf"));
                ultrapassagem.setAnoPnvSnc(result.getInt("anoPnvSnc"));
                ultrapassagem.setKmFinal(result.getFloat("kmInicial"));
                ultrapassagem.setKmFinal(result.getFloat("kmFinal"));
                ultrapassagem.setLongitudeFinal(result.getFloat("longitudeFinal"));
                ultrapassagem.setLongitudeInicial(result.getFloat("longitudeInicial"));
                ultrapassagem.setLatitudeFinal(result.getFloat("latitudeFinal"));
                ultrapassagem.setLatitudeInicial(result.getFloat("latitudeInicial"));
                ultrapassagem.setIdTrechoRodovia(result.getString("idTrechoRodovia"));
                ultrapassagem.setIdSentidoRodovia(result.getString("idSentidoRodovia"));
                ultrapassagem.setIdTipoPista(result.getString("idTipoPista"));

                ultrapassagemList.add(ultrapassagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgUltrapassagemDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
        }
        return ultrapassagemList;
    }
}

package DAO.Acidente;

import model.Acidente;

import java.util.List;

public interface AcidenteDAO {

    public void adicionarAcidente(Acidente acidente);

    public Acidente getAcidente(int id);

    public void removerAcidente(int id);

    public void atualizarAcidente(Acidente acidente);

    public List<Acidente> getTodosAcidentes();

}
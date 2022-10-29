package business;

import DAO.Rodovia.RodoviaDAO;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class RodoviaBusiness implements Serializable {
  @Inject private RodoviaDAO rodoviaDAO;
}

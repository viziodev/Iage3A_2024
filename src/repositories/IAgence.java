package repositories;

import java.util.List;
import entities.Agence;

public interface IAgence {
  List<Agence> selectAll() ; 
  Agence selectByNumero(String numero);
  void insert(Agence agence);
}

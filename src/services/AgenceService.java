package services;

import java.util.List;

import entities.Agence;
import repositories.IAgence;

public class AgenceService {
     private IAgence agenceRepository;
     //Injection Dependance
     public AgenceService(IAgence agenceRepository) {
        this.agenceRepository = agenceRepository;
     }
   public List<Agence> listerAgence(){
      return agenceRepository.selectAll();
   }
   public Agence listerAgence(String numero){
    return agenceRepository.selectByNumero(numero);
   }
   public void ajouterAgence(Agence agence){
    agenceRepository.insert(agence);
   }

}

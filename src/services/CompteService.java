package services;

import java.util.List;

import entities.Compte;
import repositories.ClientRepository;
import repositories.CompteRepository;

public class CompteService {
   
    //Dependances
   private CompteRepository compteRepository;
   private ClientRepository clientRepository;

    public CompteService(CompteRepository compteRepository) {
       this.compteRepository = compteRepository;
     }

     

    public CompteService(CompteRepository compteRepository, ClientRepository clientRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
    }



    public void ajouterCompte(Compte compte){
        compteRepository.insert(compte);
    }

    public List<Compte> listerCompte(){
        return compteRepository.selectAll();
    }
}

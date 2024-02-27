package repositories;

import java.util.ArrayList;
import java.util.List;

import entities.Agence;
//SOLID
//Single Responsability
public class AgenceRepositoryV2 extends Database implements IAgence {
    List<Agence> agences=new ArrayList<>();
    //select
    public  List<Agence> selectAll(){
        return  agences;
    }
    public  Agence selectByNumero(String numero){
     
      for (Agence ag: agences) {
          if (ag.getNumero().compareTo(numero)==0) {
              return ag;
          }
      }
      return  null;
     }
   
    public  void insert(Agence agence){
            agences.add(agence);
           
     }
}
